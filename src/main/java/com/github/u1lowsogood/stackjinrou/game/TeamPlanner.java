package com.github.u1lowsogood.stackjinrou.game;

import com.github.u1lowsogood.stackjinrou.game.game.GameManager;
import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.SJPlayer;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.Villager;
import com.github.u1lowsogood.stackjinrou.game.sjplayer.Wolf;
import com.github.u1lowsogood.stackjinrou.singleton.ServiceLocator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TeamPlanner {

    GameVariables gv;
    public TeamPlanner(GameVariables gv){
        this.gv = gv;
    }
    Plugin plugin = ServiceLocator.getInstance().getPlugin();

    public void makeTeam(){

        List<Player> onlinePlayers = new ArrayList<>();
        for(Player player : plugin.getServer().getOnlinePlayers()){
            onlinePlayers.add(player);
        }

        /*
        for(int i = 0; i< (int) gv.teamSize/2; i++) {
            int index = new Random().nextInt(onlinePlayers.size());
            Player chosen = onlinePlayers.remove(index);
            gv.playerMap.put(chosen, new Villager(chosen, Team.SEIGUN));
        }*/

        int i = 0;
        int n = 0;
        for(Player player : onlinePlayers){
            if(i%2==0) {
                if(i%3==3){
                    gv.sjPlayerDB.put(player, new Wolf(player, Team.TOUGUN, gv));
                }else{
                    gv.sjPlayerDB.put(player, new Villager(player, Team.TOUGUN, gv));
                }
            }else{
                if(i%3==3){
                    gv.sjPlayerDB.put(player, new Wolf(player, Team.SEIGUN, gv));
                }else{
                    gv.sjPlayerDB.put(player, new Villager(player, Team.SEIGUN, gv));
                }
            }
            i++;
        }

        for(SJPlayer sjPlayer : gv.sjPlayerDB.getAllPlayers()){
            Bukkit.broadcastMessage(sjPlayer.getPlayer().getDisplayName()
                    +": " + sjPlayer.getTeam().name());
        }

    }
}
