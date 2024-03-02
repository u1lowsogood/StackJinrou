package com.github.u1lowsogood.stackjinrou.game;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Team {
    SEIGUN(Material.BLUE_WOOL, ChatColor.BLUE,"西軍"),
    TOUGUN(Material.RED_WOOL,ChatColor.RED,"東軍");

    private Material colorBlock;
    private ChatColor chatColor;
    private String teamName;

    Team(Material colorBlock,ChatColor chatColor, String teamName){
        this.colorBlock = colorBlock;
        this.chatColor = chatColor;
        this.teamName = teamName;
    }

    public Material getColorBlock() {
        return colorBlock;
    }

    public String getTeamName(){
        return teamName;
    }

    public Team getOpponent(){
        if(this == SEIGUN)return TOUGUN;
        return SEIGUN;
    }

    public ChatColor getChatColor(){
        return this.chatColor;
    }
}
