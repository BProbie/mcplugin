package com.probie.Packet;

import com.probie.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ThreeChessGamePacket extends ThreeChessPacket {

    private final Player player1;
    private final Player player2;

    private final Inventory inventory;

    private boolean isFinish = false;
    private int turn = 1;

    public ThreeChessGamePacket(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        Inventory inventory = Bukkit.createInventory(null, 3 * 9, Data.getPluginName());
        inventory.setItem(3, Data.getSeat0());
        inventory.setItem(4, Data.getSeat0());
        inventory.setItem(5, Data.getSeat0());
        inventory.setItem(3+9, Data.getSeat0());
        inventory.setItem(4+9, Data.getSeat0());
        inventory.setItem(5+9, Data.getSeat0());
        inventory.setItem(3+9+9, Data.getSeat0());
        inventory.setItem(4+9+9, Data.getSeat0());
        inventory.setItem(5+9+9, Data.getSeat0());
        this.inventory = inventory;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public boolean getIsFinish() {
        return isFinish;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

}