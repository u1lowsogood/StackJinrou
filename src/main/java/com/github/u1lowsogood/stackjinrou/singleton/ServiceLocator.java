package com.github.u1lowsogood.stackjinrou.singleton;

import org.bukkit.plugin.Plugin;

public class ServiceLocator {

    private static final ServiceLocator serviceLocator = new ServiceLocator();

    private Plugin plugin;

    private ServiceLocator(){
    }

    public static ServiceLocator getInstance(){
        return serviceLocator;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin(){
        return this.plugin;
    }
}
