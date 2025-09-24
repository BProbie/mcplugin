package com.probie.Event;

import com.probie.LuckyTime;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinSendMessageEvent implements Listener {

    @EventHandler
    public void sendMessage(PlayerJoinEvent playerJoinEvent) {
        playerJoinEvent.getPlayer().sendMessage(LuckyTime.getMessageHead()+"幸运物品获得时刻插件正常运行");
    }

}