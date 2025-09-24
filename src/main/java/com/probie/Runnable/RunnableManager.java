package com.probie.Runnable;

import org.bukkit.plugin.Plugin;

public class RunnableManager<T extends BukkitRunnable> {

    private volatile static RunnableManager INSTANCE;

    public synchronized static RunnableManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RunnableManager<>();
        }
        return INSTANCE;
    }

    public synchronized void register(T runnable, Plugin plugin) {
        runnable.register(plugin);
    }

}