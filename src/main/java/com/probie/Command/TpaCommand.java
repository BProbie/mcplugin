package com.probie.Command;

import com.probie.Main;
import org.bukkit.Bukkit;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class TpaCommand implements CommandExecutor {

    /**
     * Player1 toPlayer
     * Player2 fromPlayer
     * */
    private final static HashMap<Player, Player> tpaMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("tpa")) {
            if (commandSender instanceof Player fromPlayer) {
                if (strings.length == 1) {
                    Player toPlayer = Bukkit.getPlayer(strings[0]);
                    if (toPlayer != null) {
                        tpa(fromPlayer, toPlayer);
                    } else {
                        fromPlayer.sendMessage(Main.getMessageHead()+"玩家"+strings[0]+"不存在");
                    }
                } else {
                    fromPlayer.sendMessage(Main.getMessageHead()+command.getUsage());
                }
            } else {
                commandSender.sendMessage(Main.getMessageHead()+"这个指令只有玩家能使用");
            }
            return true;
        }
        return false;
    }

    // tpa请求逻辑
    public void tpa(Player fromPlayer, Player toPlayer) {
        if (fromPlayer.isOnline() && toPlayer.isOnline()) {
            fromPlayer.sendMessage("你请求传送到\u00a7a"+toPlayer.getName()+"\u00a7r那里");
            toPlayer.sendMessage("玩家\u00a7a"+fromPlayer.getName()+"\u00a7r请求传送到你这里");
            getTpaMap().put(toPlayer, fromPlayer);
        }
    }

    public static HashMap<Player, Player> getTpaMap() {
        return tpaMap;
    }

}