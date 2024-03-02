package com.github.u1lowsogood.stackjinrou.game.phase;

import com.github.u1lowsogood.stackjinrou.game.Team;
import com.github.u1lowsogood.stackjinrou.game.Tower;
import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.SJPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EndPhase extends GamePhase{

    Team won;

    public EndPhase(GameVariables gv) {
        super(gv);
    }

    @Override
    public void start() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.won = gv.towers.stream()
                .max(Comparator.comparingInt(tower -> tower.getHighestLocation().getBlockY())).get().getTeam();

        for(SJPlayer sjPlayer : gv.sjPlayerDB.getAllPlayers()){

            sjPlayer.getPlayer().setBedSpawnLocation(gv.startPosition,true);
            sjPlayer.spawn();

            sjPlayer.getPlayer().setGameMode(GameMode.CREATIVE);
            sjPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,7*20,1));
            sjPlayer.getPlayer().sendTitle(
                    ChatColor.YELLOW + "そこまで！","試合終了",1*20,2*20,1*20);

            sjPlayer.getPlayer().setFlying(true);

            showResult.runTaskLater(plugin, 5*20);
            destroyAllAltimeter.runTaskLater(plugin, 20*20);

            gv.startPosition.getWorld().getWorldBorder().reset();

        }
    }

    BukkitRunnable showResult = new BukkitRunnable() {
        @Override
        public void run() {

            generateEndMessage().forEach(em -> Bukkit.broadcastMessage(em));

            for (Tower tower : gv.towers) {
                List<SJPlayer> sjPlayers = gv.sjPlayerDB.getPlayers(tower.getTeam().getOpponent());

                for(SJPlayer sjp : sjPlayers){

                    sjp.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
                    sjp.getPlayer().sendTitle(won.getChatColor() + won.getTeamName() + "チームの勝利！",
                            "よかったね！",1*20,3*20,1*20);
                }
            }
        }
    };

    BukkitRunnable destroyAllAltimeter = new BukkitRunnable() {
        @Override
        public void run() {
            gv.towers.forEach(tower -> tower.getAltimeter().destroy());
            next();
        }
    };


    private List<String> generateEndMessage(){
        List<String> endMessage = new ArrayList<>();
        List<Tower> sorted = gv.towers.stream()
                .sorted(Comparator.comparingInt((Tower t) -> t.getHighestLocation().getBlockY()).reversed())
                .collect(Collectors.toList());

        endMessage.add(ChatColor.GOLD + "■■■■■■■結果■■■■■■■");
        endMessage.add("");
        int i = 0;
        for(Tower tower : sorted){
            i++;
            endMessage.add(ChatColor.BOLD+""+ChatColor.GOLD + "" + i + "位： " +
                    tower.getTeam().getChatColor() +
                    tower.getTeam().getTeamName() + "チーム (" +
                    tower.getHighestLocation().getBlockY() + "m)");
        }
        endMessage.add("");
        endMessage.add(ChatColor.GOLD + "■■■■■■■■■■■■■■■■");

        return endMessage;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }
}
