package com.probie;

import org.bukkit.Bukkit;
import com.probie.Command.TpaCommand;
import org.bukkit.plugin.java.JavaPlugin;
import com.probie.Command.TpacceptCommand;
import com.probie.Event.PlayerJoinSendMsgEvent;

public final class Main extends JavaPlugin {

    public static String pluginName = "EasyTpa";
    public static String pluginVersion = "1.0";
    public static String pluginType = "SpigotPlugin";
    public static String messageHead = getPluginType()+"-"+getPluginName()+"-"+getPluginVersion()+": ";

    @Override
    public void onEnable() {
        this.getCommand("tpa").setExecutor(new TpaCommand());
        this.getCommand("tpaccept").setExecutor(new TpacceptCommand());
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
