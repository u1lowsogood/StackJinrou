package com.github.u1lowsogood.stackjinrou.game.game;

import org.bukkit.Location;

public class GameManager {

    private static final GameManager serviceLocator = new GameManager();
    private Game game;

    private GameManager(){
    }

    public void startGame(Game game, Location center){
        game.startGame(center);
    }

    public void shutdownGame(){
        game.shutdownGame();
    }

    public static GameManager getInstance(){
        return serviceLocator;
    }
}
