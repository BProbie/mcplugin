package com.probie;

import java.io.File;
import org.bukkit.Sound;
import java.util.HashSet;
import java.io.IOException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.meta.ItemMeta;
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

    private static int size = 3; // TODO
    private static int uiSize = 9 * getSize();
    private static int chessWidth = getSize();
    private static int chessHeight = getSize();

    private static ItemStack seat0 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
    private static ItemStack seat1 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    private static ItemStack seat2 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    private static ItemStack seatDecoration = new ItemStack(Material.ROSE_BUSH);

    private static Sound openSound = Sound.BLOCK_NOTE_BLOCK_PLING;
    private static Sound closeSound = Sound.BLOCK_NOTE_BLOCK_PLING;
    private static Sound winSound = Sound.ENTITY_PLAYER_LEVELUP;
    private static Sound lostSound = Sound.ENTITY_ENDER_DRAGON_AMBIENT;
    private static Sound balanceSound = Sound.BLOCK_NOTE_BLOCK_PLING;
    private static Sound downSound = Sound.BLOCK_STONE_PLACE;

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

        size = getConfig().getInt("size",3);

        ItemMeta meta0 = getSeat0().getItemMeta();
        meta0.setItemName("0");
        ItemMeta meta1 = getSeat1().getItemMeta();
        meta1.setItemName("1");
        ItemMeta meta2 = getSeat2().getItemMeta();
        meta2.setItemName("2");
        ItemMeta metaDecoration = getSeatDecoration().getItemMeta();
        metaDecoration.setItemName("❀");
        getSeat0().setItemMeta(meta0);
        getSeat1().setItemMeta(meta1);
        getSeat2().setItemMeta(meta2);
        getSeatDecoration().setItemMeta(metaDecoration);
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

    public static void setSize(int size) {
        Data.size = size;
    }

    public static int getSize() {
        return size;
    }

    public static void setUiSize(int uiSize) {
        Data.uiSize = uiSize;
    }

    public static int getUiSize() {
        return uiSize;
    }

    public static void setChessWidth(int chessWidth) {
        Data.chessWidth = chessWidth;
    }

    public static int getChessWidth() {
        return chessWidth;
    }

    public static void setChessHeight(int chessHeight) {
        Data.chessHeight = chessHeight;
    }

    public static int getChessHeight() {
        return chessHeight;
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

    public static void setSeatDecoration(ItemStack seatDecoration) {
        Data.seatDecoration = seatDecoration;
    }

    public static ItemStack getSeatDecoration() {
        return seatDecoration;
    }

    public static void setOpenSound(Sound openSound) {
        Data.openSound = openSound;
    }

    public static Sound getOpenSound() {
        return openSound;
    }

    public static void setCloseSound(Sound closeSound) {
        Data.closeSound = closeSound;
    }

    public static Sound getCloseSound() {
        return closeSound;
    }

    public static void setWinSound(Sound winSound) {
        Data.winSound = winSound;
    }

    public static Sound getWinSound() {
        return winSound;
    }

    public static void setLostSound(Sound lostSound) {
        Data.lostSound = lostSound;
    }

    public static Sound getLostSound() {
        return lostSound;
    }

    public static void setBalanceSound(Sound balanceSound) {
        Data.balanceSound = balanceSound;
    }

    public static Sound getBalanceSound() {
        return balanceSound;
    }

    public static void setDownSound(Sound downSound) {
        Data.downSound = downSound;
    }

    public static Sound getDownSound() {
        return downSound;
    }

}