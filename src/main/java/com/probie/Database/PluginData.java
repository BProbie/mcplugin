package com.probie.Database;

import java.io.File;
import com.probie.Main;
import org.bukkit.Bukkit;
import java.util.HashSet;
import java.io.IOException;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import com.probie.Event.PlayerSendNumEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;

public class PluginData {

    public static Plugin Plugin; // 插件主类
    public static FileConfiguration Config; // 配置文件

    public static int Delay = 600; // 游戏间隔默认600秒
    public static int Range = 1000; // 数字范围默认1000

    public static boolean IsGaming = false; // 游戏是否正在执行循环
    public static int Answer = -1; // 答案

    public static String ConfigFilePath = System.getProperty("user.dir")+"\\"+Main.getPluginName()+".config"; // 配置文件路径

    public static BukkitRunnable GameBukkitRunnable = new BukkitRunnable() { // 游戏线程
        @Override
        public void run() {
            IsGaming = true;
            Answer = getRange(0, Range);
            sendMessageToAllPlayer(Main.getSimpleMessageHead()+"猜数字游戏开始了!");
            sendMessageToAllPlayer(Main.getSimpleMessageHead()+"这次的范围是\u00a7a0-"+Range+"\u00a7r哦!");
            sendMessageToAllPlayer(Main.getSimpleMessageHead()+"请在\u00a7a聊天栏\u00a7r输入你心中的答案吧!");
        }
    };

    // 初始化
    public static void init(JavaPlugin javaPlugin) {
        Plugin = javaPlugin;
        Config = javaPlugin.getConfig();
        File configFile = new File(ConfigFilePath);
        if (!configFile.getParentFile().exists()) configFile.getParentFile().mkdirs();
        try {
            if (!configFile.exists()) configFile.createNewFile();
            Config.load(ConfigFilePath);
        } catch (IOException | InvalidConfigurationException exception) {
            throw new RuntimeException(exception);
        }
        Delay = Config.getInt("Delay", 600);
        Range = Config.getInt("Range", 1000);


        HashSet<ItemStack> praiseItems = PlayerSendNumEvent.getPraiseItems();
        praiseItems.add(new ItemStack(Material.DIAMOND));
        praiseItems.add(new ItemStack(Material.DIAMOND_BLOCK));
        praiseItems.add(new ItemStack(Material.GOLD_ORE));
        praiseItems.add(new ItemStack(Material.GOLD_BLOCK));
        praiseItems.add(new ItemStack(Material.GOLDEN_APPLE));
        praiseItems.add(new ItemStack(Material.IRON_AXE));
        praiseItems.add(new ItemStack(Material.IRON_BLOCK));
        praiseItems.add(new ItemStack(Material.ICE));
        praiseItems.add(new ItemStack(Material.APPLE));
        praiseItems.add(new ItemStack(Material.FIREWORK_ROCKET));
    }

    // 获取随机数
    public static int getRange(int min,int max) {
        if (max>=min) {
            if (min>=0) {
                return (int)(java.lang.Math.random()*(max-min+1)+min);
            } else {
                if (max>=0) {
                    return (int)(java.lang.Math.random()*(max-min+2)+min-1);
                } else {
                    return (int)(java.lang.Math.random()*(max-min+1)+min-1);
                }
            }
        }
        return -1;
    }

    // 给所有玩家发消息
    public static void sendMessageToAllPlayer(String message) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage(message);
        }
    }

}