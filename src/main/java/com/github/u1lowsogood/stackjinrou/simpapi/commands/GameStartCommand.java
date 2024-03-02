package com.github.u1lowsogood.stackjinrou.simpapi.commands;

import com.github.u1lowsogood.stackjinrou.game.game.Game;
import com.github.u1lowsogood.stackjinrou.game.game.GameManager;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GameStartCommand extends SubCommand {

    @Override
    public String getName() {
        return "start";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "ゲームを開始します";
    }

    @Override
    public String getSyntax() {
        return "/stackjinrou start";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player psender = (Player) sender;
        GameManager.getInstance().startGame(new Game() ,psender.getLocation());
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
