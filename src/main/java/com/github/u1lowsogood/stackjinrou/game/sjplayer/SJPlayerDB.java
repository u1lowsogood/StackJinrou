package com.github.u1lowsogood.stackjinrou.game.sjplayer;

import com.github.u1lowsogood.stackjinrou.game.Team;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SJPlayerDB {
    private HashMap<Player, SJPlayer> playerMap = new HashMap<Player, SJPlayer>();

    public List<SJPlayer> getAllPlayers(){
        return new ArrayList<>(playerMap.values());
    }

    public List<SJPlayer> getPlayers(Team team){
        List<SJPlayer> playerList = new ArrayList<>();
        for(SJPlayer sjPlayer : playerMap.values()){
            if(sjPlayer.getTeam().equals(team)){
                playerList.add(sjPlayer);
            }
        }
        return playerList;
    }

    public SJPlayer get(Player player){
        return playerMap.get(player);
    }

    public void put(Player player,SJPlayer sjPlayer){
        this.playerMap.put(player,sjPlayer);
    }
}
