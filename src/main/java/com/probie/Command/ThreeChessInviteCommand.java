package com.probie.Command;

import java.util.List;
import com.probie.Data;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.TabExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import com.probie.Manager.ThreeChessInviteManager;

public class ThreeChessInviteCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("threechess-invite")) {
            switch (strings.length) {
                case 1: {
                    if (commandSender instanceof Player player) {
                        Player receivePlayer = Bukkit.getPlayer(strings[0]);
                        if (receivePlayer != null) {
                            ThreeChessInviteManager.getINSTANCE().invite(player, receivePlayer);
                        } else {
                            player.sendMessage(Data.getSimpleMessageHead()+"玩家"+Data.colorPlayerName(strings[0])+"不存在");
                        }
                    } else {
                        commandSender.sendMessage(Data.getSimpleMessageHead()+"只有玩家能执行");
                    }
                    break;
                }
                default: {
                    commandSender.sendMessage(Data.getSimpleMessageHead()+command.getUsage());
                    break;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCommand = new ArrayList<>();
        if (strings.length == 0) {
            tabCommand.add("threechess-invite");
        }
        else if (strings.length == 1 && command.getName().equalsIgnoreCase("threechess-invite")) {
            String value = strings[0];
            if (value.isEmpty()) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    tabCommand.add(onlinePlayer.getName());
                }
            } else {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer.getName().startsWith(value)) {
                        tabCommand.add(onlinePlayer.getName());
                    }
                }
            }
        }
        return tabCommand;
    }

}