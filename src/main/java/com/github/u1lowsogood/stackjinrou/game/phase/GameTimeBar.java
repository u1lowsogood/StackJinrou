package com.github.u1lowsogood.stackjinrou.game.phase;

import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.SJPlayer;
import com.github.u1lowsogood.stackjinrou.singleton.ServiceLocator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimeBar extends BukkitRunnable {

    Plugin plugin = ServiceLocator.getInstance().getPlugin();
    GameVariables gv;

    BossBar bossBar;
    final String BAR_TITLE;

    int currentTimeTick = 0;
    final int GAME_TIME_LENGTH;

    public GameTimeBar(String title, int endTimeSecond, GameVariables gv){
        this.GAME_TIME_LENGTH = endTimeSecond * 20;
        this.BAR_TITLE = title;
        this.bossBar = Bukkit.createBossBar(title, BarColor.BLUE, BarStyle.SEGMENTED_12);
        this.gv = gv;
    }

    public void start(){
        bossBar.setProgress(1);
        bossBar.setVisible(true);

        for(SJPlayer sjp : gv.sjPlayerDB.getAllPlayers()){
            bossBar.addPlayer(sjp.getPlayer());
        }

        this.runTaskTimer(plugin,0,1);
    }

    @Override
    public void run() {
        currentTimeTick++;
        if(currentTimeTick > GAME_TIME_LENGTH){
            bossBar.removeAll();
            this.cancel();
            return;
        }
        updateBarInfo();
    }

    private void updateBarInfo(){

            float timeRemainingTick = GAME_TIME_LENGTH - currentTimeTick;
            float ratio = timeRemainingTick / GAME_TIME_LENGTH;

            bossBar.setProgress(ratio);

            int timeRemainingSecond = (int)timeRemainingTick/20;

            String title = ChatColor.AQUA + BAR_TITLE + " - <残り" + timeRemainingSecond + "秒>";

            bossBar.setTitle(title);
        }
}
