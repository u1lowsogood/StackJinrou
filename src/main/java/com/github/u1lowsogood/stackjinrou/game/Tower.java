package com.github.u1lowsogood.stackjinrou.game;

import com.github.u1lowsogood.stackjinrou.singleton.ServiceLocator;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Tower implements Listener {

    Plugin plugin = ServiceLocator.getInstance().getPlugin();

    Location center;
    
    Team team;
    ArrayList<Location> area = new ArrayList<>();
    Location highestLocation;

    Hologram altimeter;

    final int size = 5;

    public Tower(Location center,Team team) {
        this.center = center;
        this.team = team;
        setArea();
        altimeter = DHAPI.createHologram(UUID.randomUUID().toString(), center);
        updateHighestLocation();
        updateAltimeter();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void setArea(){
        for(int x = -size; x < size; x++){
            for(int z = -size; z < size; z++) {
                Location loc = center.clone().add(x,0, z);
                area.add(loc);

                //いずれ分割
                if(x == -size || x == size-1 || z == -size || z == size-1){

                    loc.clone().add(0, -1, 0).getBlock().setType(team.getColorBlock());

                    for(int y=-3; y < 4; y++) {
                        Block replaceTo = loc.clone().add(0, y, 0).getBlock();
                        if(replaceTo.getType() != Material.AIR) {
                            replaceTo.setType(team.getColorBlock());
                        }
                    }
                }
            }
        }
    }

    public void drawBorder(){

    }

    public ArrayList<Location> getArea(){
        return area;
    }

    public void updateAltimeter(){
        List<String> show = new ArrayList<>();
        show.add(team.getChatColor() + "タワー標高： " + ChatColor.BOLD + highestLocation.getBlockY() + "m");

        DHAPI.setHologramLines(altimeter, show);
        DHAPI.moveHologram(altimeter, highestLocation.clone().add(0.5,2,0.5));
    }

    private void updateHighestLocation(){
        this.highestLocation = surveyHighestLocation();
        updateAltimeter();
    }

    private void updateHighestLocation(Block blockPlaced){
        if(blockPlaced.getLocation().getBlockY() == surveyHighestLocation().getBlockY()){
            this.highestLocation = blockPlaced.getLocation();
        }else{
            this.highestLocation = surveyHighestLocation();
        }
        updateAltimeter();
    }
    
    private Location surveyHighestLocation(){
        ArrayList<Location> highestList = new ArrayList<>();

        for(Location loc : area){
            Location highest = loc.getWorld().getHighestBlockAt(loc).getLocation();
            highestList.add(highest);
        }

        return highestList.stream()
                .max(Comparator.comparingInt(Location::getBlockY))
                .get();
    }

    public boolean isOverlapping(Location loc){
        for(Location area : this.getArea()){
            if((area.getBlockX()==loc.getBlockX())&&(area.getBlockZ()==loc.getBlockZ())){
                return true;
            }
        }
        return false;
    }

    public Team getTeam(){
        return this.team;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(isOverlapping(e.getBlockPlaced().getLocation())){
            updateHighestLocation(e.getBlockPlaced());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(isOverlapping(e.getBlock().getLocation())){
            new BukkitRunnable() {
                @Override
                public void run() {
                    updateHighestLocation();
                }
            }.runTaskLater(plugin, 1);
        }
    }

    public Hologram getAltimeter() {
        return altimeter;
    }

    public Location getHighestLocation(){
        return highestLocation;
    }
}
