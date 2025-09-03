package com.probie.Event;

import com.probie.Main;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

public class PlayerJoinSendMsgEvent implements Listener {

    @EventHandler
    public void sendMsgEvent(org.bukkit.event.player.PlayerJoinEvent playerJoinEvent) {
        playerJoinEvent.getPlayer().sendMessage(Main.getMessageHead()+"简单的Tpa指令插件正常运行");
    }

}