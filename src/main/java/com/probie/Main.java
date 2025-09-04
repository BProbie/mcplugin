package com.probie;

import org.bukkit.Bukkit;
import java.util.Objects;
import com.probie.Database.PluginData;
import org.bukkit.plugin.java.JavaPlugin;
import com.probie.Command.GuessNumCommand;
import com.probie.Event.PlayerSendNumEvent;
import com.probie.Event.PlayerJoinSendMsgEvent;

public final class Main extends JavaPlugin {

    private static final String pluginName = "GuessNumber";
    private static final String pluginVersion = "1.0";
    private static final String pluginType = "SpigotPlugin";
    private static final String messageHead = getPluginType()+"-"+getPluginName()+"-"+getPluginVersion()+": ";
    private static final String simpleMessageHead = getPluginName()+": ";

    private static final PluginData pluginData = new PluginData();

    @Override
    public void onEnable() {
        PluginData.init(this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinSendMsgEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSendNumEvent(), this);
        Objects.requireNonNull(this.getCommand("guess-num")).setExecutor(new GuessNumCommand());
        this.getLogger().info(messageHead+"插件加载成功");
    }

    @Override
    public void onDisable() {
        this.getLogger().info(messageHead+"插件卸载成功");
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

    public static String getSimpleMessageHead() {
        return simpleMessageHead;
    }

    public static PluginData getPluginData() {
        return pluginData;
    }

}
