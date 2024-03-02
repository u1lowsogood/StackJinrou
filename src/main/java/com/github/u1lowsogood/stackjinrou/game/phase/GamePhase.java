package com.github.u1lowsogood.stackjinrou.game.phase;

import com.github.u1lowsogood.stackjinrou.game.game.GameManager;
import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import com.github.u1lowsogood.stackjinrou.singleton.ServiceLocator;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class GamePhase implements Listener {

    Plugin plugin = ServiceLocator.getInstance().getPlugin();
    GameVariables gv;

    GamePhase(GameVariables gv){
        this.gv = gv;
    }

    public abstract void start();

    public void next(){
        HandlerList.unregisterAll(this);
        if(gv.gamePhaseIterator.hasNext()){
            gv.gamePhaseIterator.next().start();
        }
    }
}
