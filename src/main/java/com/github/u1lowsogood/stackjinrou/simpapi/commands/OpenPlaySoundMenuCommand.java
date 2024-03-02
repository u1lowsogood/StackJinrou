package com.github.u1lowsogood.stackjinrou.simpapi.commands;

import com.github.u1lowsogood.stackjinrou.simpapi.SoundCheckMenu;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class OpenPlaySoundMenuCommand extends SubCommand {

    @Override
    public String getName() {
        return "playsound";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("ps");
    }

    @Override
    public String getDescription() {
        return "サウンド一覧をアレします";
    }

    @Override
    public String getSyntax() {
        return "/stackjinrou playsound";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        try {
            MenuManager.openMenu(SoundCheckMenu.class, (Player) sender);

        } catch (MenuManagerException | MenuManagerNotSetupException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
