package com.probie.Event;

import com.probie.Data;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import com.probie.Packet.ThreeChessGamePacket;
import com.probie.Manager.ThreeChessGameManager;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class PlayerCloseChessEvent implements Listener {

    @EventHandler
    public void playerCloseChessEvent(InventoryCloseEvent inventoryCloseEvent) {
        ThreeChessGamePacket gamePacket = ThreeChessGameManager.getINSTANCE().getGamePacketByInventory(inventoryCloseEvent.getInventory());
        if (gamePacket != null) {
            if (!gamePacket.getIsFinish()) {
                if (inventoryCloseEvent.getPlayer().getUniqueId() == gamePacket.getPlayer1().getUniqueId()) {
                    if (inventoryCloseEvent.getPlayer().getUniqueId() != gamePacket.getPlayer2().getUniqueId()) {
                        gamePacket.getPlayer2().closeInventory();
                        gamePacket.getPlayer2().sendMessage(Data.getSimpleMessageHead()+"玩家"+Data.colorPlayerName(inventoryCloseEvent.getPlayer().getName())+"退出了游戏");
                    }
                }
                else if (inventoryCloseEvent.getPlayer().getUniqueId() == gamePacket.getPlayer2().getUniqueId()) {
                    if (inventoryCloseEvent.getPlayer().getUniqueId() != gamePacket.getPlayer1().getUniqueId()) {
                        gamePacket.getPlayer1().closeInventory();
                        gamePacket.getPlayer1().sendMessage(Data.getSimpleMessageHead()+"玩家"+Data.colorPlayerName(inventoryCloseEvent.getPlayer().getName())+"退出了游戏");
                    }
                }
                ThreeChessGameManager.getINSTANCE().getGamePackets().remove(gamePacket);
            }
        }
    }

}