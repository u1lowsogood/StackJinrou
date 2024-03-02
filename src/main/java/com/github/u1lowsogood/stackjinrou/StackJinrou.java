package com.github.u1lowsogood.stackjinrou;

import com.github.u1lowsogood.stackjinrou.singleton.ServiceLocator;
import org.bukkit.plugin.java.JavaPlugin;

public final class StackJinrou extends JavaPlugin {

    @Override
    public void onEnable() {
        ServiceLocator.getInstance().setPlugin(this);
        SJCommandCreator.createCommand(this);
    }

    @Override
    public void onDisable() {
    }
}
