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
                    try {
                        if (inventoryClickEvent.getCurrentItem().getType() == Data.getSeat0().getType()) {
                            switch (gamePacket.getTurn()) {
                                case 1: {
                                    if (inventoryClickEvent.getWhoClicked().getUniqueId() == gamePacket.getPlayer1().getUniqueId()) {
                                        ThreeChessGameManager.getINSTANCE().chess(gamePacket, inventoryClickEvent.getSlot());
                                    }
                                    break;
                                }
                                case 2: {
                                    if (inventoryClickEvent.getWhoClicked().getUniqueId() == gamePacket.getPlayer2().getUniqueId()) {
                                        ThreeChessGameManager.getINSTANCE().chess(gamePacket, inventoryClickEvent.getSlot());
                                    }
                                    break;
                                }
                            }
                        }
                    } catch (NullPointerException ignored) {}
                }
            }
        }
    }

}