package com.github.u1lowsogood.stackjinrou.game.sjplayer;

import com.github.u1lowsogood.stackjinrou.game.Team;
import com.github.u1lowsogood.stackjinrou.game.Tower;
import com.github.u1lowsogood.stackjinrou.game.SJItems;
import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Wolf extends SJPlayer{
    public Wolf(Player player, Team team, GameVariables gv) {
        super(player, team, gv);
        super.role = Role.WOLF;
    }

    @Override
    public void giveFlag(){
        if(team.equals(Team.SEIGUN)){
            player.getInventory().addItem(SJItems.PLANT_TOUGUN.getItemStack());
            return;
        }
        player.getInventory().addItem(SJItems.PLANT_SEIGUN.getItemStack());
    }

    @Override
    public void removeFlag(){
        if(team.equals(Team.TOUGUN)){
            player.getInventory().remove(SJItems.PLANT_SEIGUN.getItemStack());
            return;
        }
        player.getInventory().remove(SJItems.PLANT_TOUGUN.getItemStack());
    }
}
