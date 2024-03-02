package com.github.u1lowsogood.stackjinrou.game;

import com.github.u1lowsogood.stackjinrou.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public enum SJItems {

    PLANT_TOUGUN(new ItemBuilder(Material.RED_BANNER)
            .displayname(ChatColor.RED + "東軍タワー：建設地設定")
            .lore(new ArrayList<String>(){
                {
                    add("");
                    add(ChatColor.DARK_AQUA + "仲間と話し合って建設地を決めよう。");
                    add("");
                    add(ChatColor.GRAY + "旗を設置した位置を中心とする");
                    add(ChatColor.GRAY + "5x5の範囲をチームのタワー建設地に設定する。");
                    add(ChatColor.GRAY + "ゲーム終了時、建設地の中で最も高い位置に");
                    add(ChatColor.GRAY + "ブロックのあったチームが勝者となる。");
                }
            })
            .build()),

    PLANT_SEIGUN(new ItemBuilder(Material.BLUE_BANNER)
            .displayname(ChatColor.BLUE + "西軍タワー：建設地設定")
            .lore(new ArrayList<String>(){
                {
                    add("");
                    add(ChatColor.DARK_AQUA + "仲間と話し合って建設地を決めよう。");
                    add("");
                    add(ChatColor.GRAY + "旗を設置した位置を中心とする");
                    add(ChatColor.GRAY + "5x5の範囲をチームのタワー建設地に設定する。");
                    add(ChatColor.GRAY + "ゲーム終了時、建設地の中で最も高い位置に");
                    add(ChatColor.GRAY + "ブロックのあったチームが勝者となる。");
                }
            })
            .build()),
    ;

    ItemStack itemStack;
    SJItems(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
