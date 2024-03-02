package com.github.u1lowsogood.stackjinrou.simpapi.commands;

import com.github.u1lowsogood.stackjinrou.game.game.Game;
import com.github.u1lowsogood.stackjinrou.game.game.GameManager;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GameShutdownCommand extends SubCommand {

    @Override
    public String getName() {
        return "shutdown";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "ゲームを中断します";
    }

    @Override
    public String getSyntax() {
        return "/stackjinrou end";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        GameManager.getInstance().shutdownGame();
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
