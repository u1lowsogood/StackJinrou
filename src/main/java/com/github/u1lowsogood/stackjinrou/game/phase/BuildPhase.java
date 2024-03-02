package com.github.u1lowsogood.stackjinrou.game.phase;

import com.github.u1lowsogood.stackjinrou.game.Tower;
import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.Role;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.SJPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class BuildPhase extends GamePhase{

    final int endTime = 20;

    GameTimeBar gameTimeBar = new GameTimeBar(ChatColor.AQUA + "積み上げフェーズ",endTime, gv);

    public BuildPhase(GameVariables gv) {
        super(gv);
    }

    @Override
    public void start() {

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        WorldBorder wb = gv.startPosition.getWorld().getWorldBorder();
        wb.setCenter(gv.startPosition);
        wb.setSize(70,2);

        for(SJPlayer sjPlayer : gv.sjPlayerDB.getAllPlayers()) {
            sjPlayer.spawn();
            sjPlayer.getPlayer().sendTitle(ChatColor.YELLOW + "積み上げフェーズ",
                    ChatColor.AQUA + "相手陣地よりも高く積み上げろ",1*20,3*20,1*20);
            sjPlayer.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
        gameTimeBar.start();
        phaseEnder.runTaskLater(plugin,endTime*20);
    }

    BukkitRunnable phaseEnder = new BukkitRunnable() {
        @Override
        public void run() {
            next();
        }
    };

    @EventHandler
    public void sendAllyTeamBlockWhenWolfBreakEnemyTower(BlockBreakEvent e){
        Player player = e.getPlayer();
        SJPlayer sjPlayer = gv.sjPlayerDB.get(player);
        Block block = e.getBlock();

        if(sjPlayer.getRole() != Role.WOLF){
            return;
        }

        for(Tower tower : gv.towers){
            if(tower.isOverlapping(block.getLocation()) && tower.getTeam() != sjPlayer.getTeam()){
                for(SJPlayer teammate : gv.sjPlayerDB.getPlayers(sjPlayer.getTeam())){
                    teammate.getPlayer().getInventory().addItem(new ItemStack(block.getType(),10));
                }
            }
        }

    }
}
