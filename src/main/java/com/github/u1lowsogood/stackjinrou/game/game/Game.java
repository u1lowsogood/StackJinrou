package com.github.u1lowsogood.stackjinrou.game.game;

import com.github.u1lowsogood.stackjinrou.game.GameStatus;
import com.github.u1lowsogood.stackjinrou.game.TeamPlanner;
import org.bukkit.Location;

public class Game {

    GameVariables gv;
    GameRunner gameRunner;
    TeamPlanner teamPlanner;
    GameStatus gameStatus = GameStatus.WAITING;

    public void startGame(Location location){
        this.gv = new GameVariables(location);
        this.gameRunner = new GameRunner(gv);
        this.teamPlanner = new TeamPlanner(gv);

        teamPlanner.makeTeam();
        gameRunner.start();
    }

    public void shutdownGame(){
        gameRunner.shutdown();
    }

    public GameVariables getGameVariables(){
        return this.gv;
    }
}
