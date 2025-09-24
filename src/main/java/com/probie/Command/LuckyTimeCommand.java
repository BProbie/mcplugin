package com.probie.Command;

import java.util.List;
import java.util.ArrayList;
import com.probie.LuckyTime;
import org.bukkit.command.Command;
import com.probie.Config.DataConfig;
import org.bukkit.command.TabExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class LuckyTimeCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("luckytime")) {
            switch (strings.length) {
                case 0: {
                    commandSender.sendMessage(LuckyTime.getSimpleMessageHead()+"当前幸运间隔为"+LuckyTime.colorWord(String.valueOf(LuckyTime.getLuckyTime()), "\u00a7a")+"秒");
                    return true;
                }
                case 2: {
                    if (strings[0].equalsIgnoreCase("set")) {
                        if (commandSender.isOp()) {
                            try {
                                int second = Integer.parseInt(strings[1]);
                                LuckyTime.setLuckyTime(second);
                                DataConfig.getInstance().getConfiguration().set(LuckyTime.getKeyLuckyTime(), second);
                                commandSender.sendMessage(LuckyTime.getSimpleMessageHead()+"更改成功，"+"当前幸运间隔为"+LuckyTime.colorWord(String.valueOf(LuckyTime.getLuckyTime()), "\u00a7a")+"秒");
                            } catch (NumberFormatException ignored) {
                                commandSender.sendMessage(LuckyTime.getSimpleMessageHead()+"无效的输入"+LuckyTime.colorWord(strings[1], "\u00a7a"));
                            }
                        } else {
                            commandSender.sendMessage(LuckyTime.getSimpleMessageHead()+"权限不足");
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> commandTabs = new ArrayList<>();
        switch (strings.length) {
            case 0: {
                commandTabs.add("luckytime");
                break;
            }
            case 1: {
                if (command.getName().equalsIgnoreCase("luckytime")) {
                    commandTabs.add("set");
                }
                break;
            }
            case 2: {
                if (command.getName().equalsIgnoreCase("luckytime") && strings[0].equalsIgnoreCase("set")) {
                    for (int i = 1; i <= 10; i++) {
                        commandTabs.add(String.valueOf(60*i));
                    }
                }
                break;
            }
        }
        return commandTabs;
    }
}