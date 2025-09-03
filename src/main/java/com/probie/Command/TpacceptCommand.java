package com.probie.Command;

import com.probie.Main;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class TpacceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("tpaccept")) {
            if (commandSender instanceof Player acceptPlayer) {
                if (strings.length == 0) {
                    Player sendPlayer = TpaCommand.getTpaMap().getOrDefault(acceptPlayer, null);
                    if (sendPlayer != null) {
                        if (sendPlayer.isOnline()) {
                            sendPlayer.teleport(acceptPlayer.getLocation());
                            TpaCommand.getTpaMap().remove(acceptPlayer);
                        } else {
                            acceptPlayer.sendMessage(Main.getMessageHead()+"玩家"+sendPlayer.getName()+"不在线");
                        }
                    } else {
                        acceptPlayer.sendMessage(Main.getMessageHead()+"不存在请求玩家");
                    }
                } else {
                    acceptPlayer.sendMessage(Main.getMessageHead()+command.getUsage());
                }
            } else {
                commandSender.sendMessage(Main.getMessageHead()+"这个指令只有玩家能使用");
            }
            return true;
        }
        return false;
    }

}