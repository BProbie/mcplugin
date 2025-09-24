package com.probie.Config;

import org.bukkit.plugin.Plugin;

public class ConfigManager<T extends Config> {

    private volatile static ConfigManager INSTANCE;

    public synchronized static ConfigManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigManager();
        }
        return INSTANCE;
    }

    public synchronized void register(T config, Plugin plugin) {
        config.register(plugin);
    }

}