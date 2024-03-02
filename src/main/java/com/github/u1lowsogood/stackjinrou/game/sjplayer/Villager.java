package com.github.u1lowsogood.stackjinrou.game.sjplayer;

import com.github.u1lowsogood.stackjinrou.game.Team;
import com.github.u1lowsogood.stackjinrou.game.game.GameVariables;
import org.bukkit.entity.Player;

public class Villager extends SJPlayer{

    public Villager(Player player, Team team, GameVariables gv) {
        super(player, team, gv);
        super.role = Role.VILLAGER;
    }
}
