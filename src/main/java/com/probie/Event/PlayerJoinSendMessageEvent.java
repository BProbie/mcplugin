package com.probie.Event;

import com.probie.Data;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinSendMessageEvent implements Listener {

    @EventHandler
    public void sendMessageEvent(PlayerJoinEvent playerJoinEvent) {
        playerJoinEvent.getPlayer().sendMessage(Data.getMessageHead()+"三子棋插件正常运行");
    }

}