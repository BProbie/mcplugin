package com.probie;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.probie.Event.PlayerLoginAddOPEvent;
import com.probie.Event.PlayerJoinSendMsgEvent;

public final class Main extends JavaPlugin {

    public static final String pluginName = "AutoOP";
    public static final String pluginVersion = "1.0";
    public static final String pluginType = "SpigotPlugin";
    public static final String messageHead = getPluginType()+"-"+getPluginName()+"-"+getPluginVersion()+": ";

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerLoginAddOPEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinSendMsgEvent(), this);
        this.getLogger().info(getMessageHead()+"加载成功");
    }

    @Override
    public void onDisable() {
        this.getLogger().info(getMessageHead()+"卸载成功");
    }

    public static String getPluginName() {
        return pluginName;
    }

    public static String getPluginVersion() {
        return pluginVersion;
    }

    public static String getPluginType() {
        return pluginType;
    }

    public static String getMessageHead() {
        return messageHead;
    }

}
