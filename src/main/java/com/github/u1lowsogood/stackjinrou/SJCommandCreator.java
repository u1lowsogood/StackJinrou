package com.github.u1lowsogood.stackjinrou;

import com.github.u1lowsogood.stackjinrou.simpapi.commands.GameShutdownCommand;
import com.github.u1lowsogood.stackjinrou.simpapi.commands.GameStartCommand;
import com.github.u1lowsogood.stackjinrou.simpapi.commands.OpenPlaySoundMenuCommand;
import me.kodysimpson.simpapi.command.CommandList;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class SJCommandCreator {

    public static void createCommand(JavaPlugin plugin) {
        try {
            CommandManager.createCoreCommand(
                    plugin,
                    "StackJinrou",
                    "積み上げ人狼",
                    "/stackjinrou",

                    new CommandList() {
                        @Override
                        public void displayCommandList(CommandSender p, List<SubCommand> subCommandList) {

                            p.sendMessage("～～～～コマンド一覧 表示してみた！？～～～～");
                            p.sendMessage("");
                            subCommandList.forEach(subCommand -> {
                                p.sendMessage(
                                        ChatColor.GREEN + subCommand.getSyntax() +
                                                ChatColor.WHITE + subCommand.getDescription()
                                );
                            });
                            p.sendMessage("");
                            p.sendMessage("～～～～～～～～～～～ ～～～～～～～～～～～");

                        }
                    },
                    Arrays.asList("sj"),

                    GameStartCommand.class,
                    GameShutdownCommand.class,
                    OpenPlaySoundMenuCommand.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
