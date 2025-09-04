package com.probie.Event;

import com.probie.Main;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinSendMsgEvent implements Listener {

    @EventHandler
    public void sendMessageEvent(PlayerJoinEvent playerJoinEvent) {
        playerJoinEvent.getPlayer().sendMessage(Main.getMessageHead()+"猜数字游戏插件正常运行");
    }

}