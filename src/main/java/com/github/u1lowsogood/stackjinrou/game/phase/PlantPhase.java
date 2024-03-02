package com.github.u1lowsogood.stackjinrou.game.phase;

import com.github.u1lowsogood.stackjinrou.game.Team;
import com.github.u1lowsogood.stackjinrou.game.Tower;
import com.github.u1lowsogood.stackjinrou.game.SJItems;
import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.SJPlayer;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class PlantPhase extends GamePhase {

    public PlantPhase(GameVariables gv) {
        super(gv);
    }

    @Override
    public void start() {

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        WorldBorder wb = gv.startPosition.getWorld().getWorldBorder();
        wb.setCenter(gv.startPosition);
        wb.setSize(50);
        wb.setDamageAmount(5);
        wb.setDamageBuffer(3);

        for(SJPlayer sjPlayer : gv.sjPlayerDB.getAllPlayers()) {

            sjPlayer.getPlayer().setBedSpawnLocation(gv.startPosition,true);
            sjPlayer.spawn();

            sjPlayer.getPlayer().setGameMode(GameMode.SURVIVAL);
            sjPlayer.getPlayer().sendTitle(ChatColor.YELLOW + "建設予定地決定フェーズ",
                    ChatColor.AQUA + "タワーの建設に有利な場所を決めろ",1*20,3*20,1*20);
            //sjPlayer.giveFlag();

            //debug
            sjPlayer.getPlayer().getInventory().addItem(SJItems.PLANT_TOUGUN.getItemStack());
            sjPlayer.getPlayer().getInventory().addItem(SJItems.PLANT_SEIGUN.getItemStack());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        if(e.getEntityType().equals(EntityType.PLAYER)){
            e.setDamage(0);
        }
    }

    @EventHandler
    public void onBannerPlace(BlockPlaceEvent e){

        Player player = e.getPlayer();
        SJPlayer sjPlayer = gv.sjPlayerDB.get(player);
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        Location center = e.getBlockPlaced().getLocation();
        Team team = null;

        if(itemInHand.isSimilar(SJItems.PLANT_SEIGUN.getItemStack())){
            team = Team.SEIGUN;
        }else if(itemInHand.isSimilar(SJItems.PLANT_TOUGUN.getItemStack())){
            team = Team.TOUGUN;
        }else{
            e.setCancelled(true);
            return;
        }

        if(!canBuildTower(center)){
            player.sendMessage(ChatColor.RED + "他の拠点と領域が被っているようだ……");
            e.setCancelled(true);
            return;
        }

        Tower tower = new Tower(center, team);

        gv.towers.add(tower);
        Bukkit.broadcastMessage(team.getChatColor() + player.getDisplayName() + "が" + team.getTeamName()
                + ChatColor.AQUA + "チームのタワー建設地を決定しました！");
        e.setCancelled(true);

        for(SJPlayer sjTeamMate : gv.sjPlayerDB.getPlayers(sjPlayer.getTeam())) {
            sjTeamMate.getPlayer().setBedSpawnLocation(center.clone().add(0.5,0,0.5),true);
            sjTeamMate.removeFlag();
        }

        //確実な処理を……♡
        if(gv.towers.size() == 2){
            next();
        }
    }

    private boolean canBuildTower(Location center){
        int size = 5;
        for(Tower opponentTower : gv.towers){
            Bukkit.broadcastMessage("BUILT: " +opponentTower.getTeam().toString());
            for(int x = -size; x < size; x++){
                for(int z = -size; z < size; z++) {
                    Location loc = center.clone().add(x,0, z);
                    if(opponentTower.isOverlapping(loc)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
