package com.github.u1lowsogood.stackjinrou.game.game;

import com.github.u1lowsogood.stackjinrou.game.Team;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.SJPlayer;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.Villager;
import com.github.u1lowsogood.stackjinrou.singleton.ServiceLocator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class GameRunner {

    Plugin plugin = ServiceLocator.getInstance().getPlugin();
    GameVariables gv;

    public GameRunner(GameVariables gv) {
        this.gv = gv;
    }

    public void start(){
        gv.gamePhaseIterator.next().start();
    }

    public void shutdown(){
    }
}
