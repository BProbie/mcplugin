package com.probie.Event;

import com.probie.Data;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import com.probie.Packet.ThreeChessGamePacket;
import com.probie.Manager.ThreeChessGameManager;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerChessEvent implements Listener {

    @EventHandler
    public void playerChessEvent(InventoryClickEvent inventoryClickEvent) {
        ThreeChessGamePacket gamePacket = ThreeChessGameManager.getINSTANCE().getGamePacketByInventory(inventoryClickEvent.getClickedInventory());
        if (gamePacket != null) {
            if (!inventoryClickEvent.isCancelled()) {
                inventoryClickEvent.setCancelled(true);
            }
            if (!gamePacket.getIsFinish()) {
                if (inventoryClickEvent.isLeftClick()) {
                    System.out.println(inventoryClickEvent.getCurrentItem());
                    try {
                        if (inventoryClickEvent.getCurrentItem().getType() == Data.getSeat0().getType()) {
                            ThreeChessGameManager.getINSTANCE().chess(gamePacket, inventoryClickEvent.getSlot());
                        }
                    } catch (NullPointerException ignored) {}
                }
            }
        }
    }

}