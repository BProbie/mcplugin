package com.probie;

import java.io.File;
import java.util.HashSet;
import java.io.IOException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;

public class Data {

    private static JavaPlugin plugin;
    private static FileConfiguration config;

    private static final HashSet<BukkitRunnable> bukkitRunnables = new HashSet<>();

    private static String pluginType = "SpigotPlugin";
    private static String pluginName = "ThreeChess";
    private static String pluginVersion = "1.0";

    private static String messageHead = getPluginType()+"-"+getPluginName()+"-"+getPluginVersion()+":"+" ";
    private static String simpleMessageHead = getPluginName()+":"+" ";

    private static String configFilePath = System.getProperty("user.dir")+"\\"+getPluginName()+".config";
    private static int inviteOutDateTime = 30;

    private static ItemStack seat0 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
    private static ItemStack seat1 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    private static ItemStack seat2 = new ItemStack(Material.RED_STAINED_GLASS_PANE);

    public static void init(JavaPlugin javaPlugin) {
        plugin = javaPlugin;
        config = javaPlugin.getConfig();

        File configFile = new File(getConfigFilePath());
        if (!configFile.getParentFile().exists()) configFile.getParentFile().mkdirs();
        try {
            if (!configFile.exists()) configFile.createNewFile();
            config.load(getConfigFilePath());
        } catch (IOException | InvalidConfigurationException exception) {
            throw new RuntimeException(new Exception());
        }

        ItemMeta meta0 = seat0.getItemMeta();
        meta0.setItemName("0");
        ItemMeta meta1 = seat1.getItemMeta();
        meta1.setItemName("1");
        ItemMeta meta2 = seat2.getItemMeta();
        meta2.setItemName("2");
        seat0.setItemMeta(meta0);
        seat1.setItemMeta(meta1);
        seat2.setItemMeta(meta2);
    }

    public static String colorWord(Object word) {
        return "\u00a7a"+word+"\u00a7r";
    }

    public static String colorPlayerName(String playerName) {
        return colorWord(playerName);
    }

    public static void setPlugin(JavaPlugin plugin) {
        Data.plugin = plugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setConfig(FileConfiguration config) {
        Data.config = config;
    }

    public static String getConfigFilePath() {
        return configFilePath;
    }

    public static HashSet<BukkitRunnable> getBukkitRunnables() {
        return bukkitRunnables;
    }

    public static String getPluginType() {
        return pluginType;
    }

    public static String getPluginName() {
        return pluginName;
    }

    public static String getPluginVersion() {
        return pluginVersion;
    }

    public static String getMessageHead() {
        return messageHead;
    }

    public static String getSimpleMessageHead() {
        return simpleMessageHead;
    }

    public static void setConfigFilePath(String configFilePath) {
        Data.configFilePath = configFilePath;
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void setInviteOutDateTime(int inviteOutDateTime) {
        Data.inviteOutDateTime = inviteOutDateTime;
    }

    public static int getInviteOutDateTime() {
        return inviteOutDateTime;
    }

    public static void setSeat0(ItemStack seat0) {
        Data.seat0 = seat0;
    }

    public static ItemStack getSeat0() {
        return seat0;
    }

    public static void setSeat1(ItemStack seat1) {
        Data.seat1 = seat1;
    }

    public static ItemStack getSeat1() {
        return seat1;
    }

    public static void setSeat2(ItemStack seat2) {
        Data.seat2 = seat2;
    }

    public static ItemStack getSeat2() {
        return seat2;
    }

}