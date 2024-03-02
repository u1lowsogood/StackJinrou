package com.github.u1lowsogood.stackjinrou.game.game;

import com.github.u1lowsogood.stackjinrou.game.phase.BuildPhase;
import com.github.u1lowsogood.stackjinrou.game.phase.EndPhase;
import com.github.u1lowsogood.stackjinrou.game.phase.GamePhase;
import com.github.u1lowsogood.stackjinrou.game.phase.PlantPhase;
import com.github.u1lowsogood.stackjinrou.game.Tower;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.SJPlayerDB;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameVariables {

    public Location startPosition;
    public SJPlayerDB sjPlayerDB = new SJPlayerDB();
    public List<Tower> towers = new ArrayList<>();
    public Iterator<GamePhase> gamePhaseIterator;

    public int teamSize = 3;

    public GameVariables(Location startPosition){
        this.startPosition = startPosition;
        setIterator();
    }

    private final void setIterator(){
        List<GamePhase> gamePhaseList = new ArrayList<>();
        gamePhaseList.add(new PlantPhase(this));
        gamePhaseList.add(new BuildPhase(this));
        gamePhaseList.add(new EndPhase(this));

        this.gamePhaseIterator = gamePhaseList.iterator();
    }

}
