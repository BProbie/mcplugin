package com.probie.Command;

import java.util.List;
import com.probie.Data;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.TabExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import com.probie.Manager.ThreeChessAcceptManager;

public class ThreeChessAcceptCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("threechess-accept")) {
            switch (strings.length) {
                case 0: {
                    if (commandSender instanceof Player acceptPlayer) {
                        ThreeChessAcceptManager.getINSTANCE().accept(acceptPlayer);
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
            tabCommand.add("threechess-accept");
        }
        return tabCommand;
    }

}