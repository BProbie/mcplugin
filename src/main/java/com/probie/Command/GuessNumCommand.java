package com.probie.Command;

import java.util.List;
import com.probie.Main;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.TabExecutor;
import com.probie.Database.PluginData;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.scheduler.BukkitRunnable;

public class GuessNumCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("guess-num")) {
            switch (strings.length) {
                case 1: {
                    // start开始游戏
                    if (strings[0].equalsIgnoreCase("start")) {
                        if (!PluginData.IsGaming) {
                            startGame();
                        } else {
                            commandSender.sendMessage(Main.getSimpleMessageHead()+"游戏进行中");
                        }
                        return true;
                    }

                    // restart重启游戏
                    else if (strings[0].equalsIgnoreCase("restart")) {
                        if (commandSender.isOp()) {
                            if (PluginData.IsGaming) stopGame(true);
                            startGame();
                        } else {
                            commandSender.sendMessage(Main.getSimpleMessageHead()+"权限不足");
                        }
                        return true;
                    }

                    // stop停止游戏
                    else if (strings[0].equalsIgnoreCase("stop")) {
                        if (commandSender.isOp()) {
                            stopGame(true);
                        } else {
                            commandSender.sendMessage(Main.getSimpleMessageHead()+"权限不足");
                        }
                        return true;
                    }

                    // 查看游戏间隔
                    else if (strings[0].equalsIgnoreCase("delay")) {
                        commandSender.sendMessage(Main.getSimpleMessageHead()+"当前游戏时间间隔为\u00a7a"+PluginData.Delay+"\u00a7r秒");
                        return true;
                    }

                    // 查看数字范围
                    else if (strings[0].equalsIgnoreCase("range")) {
                        commandSender.sendMessage(Main.getSimpleMessageHead()+"当前数字范围为\u00a7a"+PluginData.Range);
                        return true;
                    }
                }
                case 2: {
                    // 设置游戏间隔
                    if (strings[0].equalsIgnoreCase("delay")) {
                        if (commandSender.isOp()) {
                            try {
                                int delay = Integer.parseInt(strings[1]);
                                if (delay >= 0) {
                                    PluginData.Delay = delay;
                                    PluginData.Config.set("Delay", delay);
                                    try {
                                        PluginData.Config.save(PluginData.ConfigFilePath);
                                    } catch (IOException ioException) {
                                        throw new RuntimeException(ioException);
                                    }
                                    commandSender.sendMessage(Main.getSimpleMessageHead()+"设置成功,"+"当前游戏时间间隔为\u00a7a"+PluginData.Delay+"\u00a7r秒");
                                    if (!PluginData.GameBukkitRunnable.isCancelled()) {
                                        stopGame(true);
                                        startGame(PluginData.Delay);
                                    }
                                    return true;
                                } else {
                                    commandSender.sendMessage(Main.getSimpleMessageHead()+strings[2]+"不是非负整数");
                                    return true;
                                }
                            } catch (NumberFormatException numberFormatException) {
                                commandSender.sendMessage(Main.getSimpleMessageHead()+strings[2]+"不是非负整数");
                                return true;
                            }
                        } else {
                            commandSender.sendMessage(Main.getSimpleMessageHead()+"权限不足");
                            return true;
                        }
                    }

                    // 设置数字范围
                    else if (strings[0].equalsIgnoreCase("range")) {
                        if (commandSender.isOp()) {
                            try {
                                int range = Integer.parseInt(strings[1]);
                                if (range >= 0) {
                                    PluginData.Range = range;
                                    PluginData.Config.set("Range", range);
                                    try {
                                        PluginData.Config.save(PluginData.ConfigFilePath);
                                    } catch (IOException ioException) {
                                        throw new RuntimeException(ioException);
                                    }
                                    commandSender.sendMessage(Main.getSimpleMessageHead()+"设置成功,"+"当前数字范围为\u00a7a"+PluginData.Range);
                                    if (!PluginData.GameBukkitRunnable.isCancelled()) {
                                        stopGame(true);
                                        startGame(PluginData.Delay);
                                    }
                                    return true;
                                } else {
                                    commandSender.sendMessage(Main.getSimpleMessageHead()+strings[2]+"不是非负整数");
                                    return true;
                                }
                            } catch (NumberFormatException numberFormatException) {
                                commandSender.sendMessage(Main.getSimpleMessageHead()+strings[2]+"不是非负整数");
                                return true;
                            }
                        } else {
                            commandSender.sendMessage(Main.getSimpleMessageHead()+"权限不足");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCommand = new ArrayList<>();
        if (strings.length == 0) {
            tabCommand.add("guess-num");
        }
        else if (strings.length == 1 && command.getName().equalsIgnoreCase("guess-num")) {
            tabCommand.add("start");
            tabCommand.add("restart");
            tabCommand.add("stop");
            tabCommand.add("delay");
            tabCommand.add("range");
        }
        return tabCommand;
    }

    public static void stopGame() {
        stopGame(false);
    }

    public static void stopGame(boolean isStopRunnable) {
        if (isStopRunnable) {
            try {
                if (!PluginData.GameBukkitRunnable.isCancelled()) {
                    PluginData.GameBukkitRunnable.cancel();
                }
            } catch (IllegalStateException ignored) {}
        }
        PluginData.IsGaming = false;
        PluginData.Answer = -1;
    }

    public static void startGame() {
        startGame(0);
    }

    public static void startGame(int startSecond) {
        try {
            if (!PluginData.GameBukkitRunnable.isCancelled()) {
                PluginData.GameBukkitRunnable.cancel();
            }
        } catch (IllegalStateException ignored) {}
        PluginData.GameBukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                PluginData.IsGaming = true;
                PluginData.Answer = PluginData.getRange(0, PluginData.Range);
                PluginData.sendMessageToAllPlayer(Main.getSimpleMessageHead()+"猜数字游戏开始了!");
                PluginData.sendMessageToAllPlayer(Main.getSimpleMessageHead()+"这次的范围是\u00a7a0-"+PluginData.Range+"\u00a7r哦!");
                PluginData.sendMessageToAllPlayer(Main.getSimpleMessageHead()+"请在\u00a7a聊天栏\u00a7r输入你心中的答案吧!");
            }
        };
        PluginData.GameBukkitRunnable.runTaskTimerAsynchronously(PluginData.Plugin, startSecond * 20L, PluginData.Delay * 20L);
    }

}