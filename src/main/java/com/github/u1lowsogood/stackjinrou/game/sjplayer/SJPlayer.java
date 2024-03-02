package com.github.u1lowsogood.stackjinrou.game.sjplayer;

import com.github.u1lowsogood.stackjinrou.game.Tower;
import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import com.github.u1lowsogood.stackjinrou.game.Team;
import com.github.u1lowsogood.stackjinrou.game.SJItems;
import com.github.u1lowsogood.stackjinrou.game.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class SJPlayer {

    Team team;
    Player player;
    Role role;
    GameVariables gv;
    Statistics statistics;
    boolean isBanned;

    public SJPlayer(Player player,Team team, GameVariables gv) {
        this.player = player;
        this.team = team;
        this.gv = gv;
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam(){
        return team;
    }

    public Role getRole(){
        return role;
    }

    public void spawn(){
        player.teleport(player.getBedSpawnLocation());
    }

    public void giveFlag(){
        if(team.equals(Team.TOUGUN)){
            player.getInventory().addItem(SJItems.PLANT_TOUGUN.getItemStack());
            return;
        }
        player.getInventory().addItem(SJItems.PLANT_SEIGUN.getItemStack());
    }

    public void removeFlag(){
        if(team.equals(Team.TOUGUN)){
            player.getInventory().remove(SJItems.PLANT_TOUGUN.getItemStack());
            return;
        }
        player.getInventory().remove(SJItems.PLANT_SEIGUN.getItemStack());
    }
}