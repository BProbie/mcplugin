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

        Inventory inventory = Bukkit.createInventory(null, 3*9, Data.getPluginName());
        if (Data.getUiSize() > 3*9) inventory = Bukkit.createInventory(null, Data.getUiSize(), Data.getPluginName());

        int indexX = (int) Math.floor((double) (9-Data.getChessWidth())/2);
        int indexY = (int) Math.floor((double) (((int) Math.floor((double) inventory.getSize()/9))-Data.getChessHeight())/2);
        int width = Data.getChessWidth();
        if (width < 3) {
            width = 3;
        }
        else if (width > 9) {
            width = 9;
        }
        int height = Data.getChessHeight();
        if (height < 3) {
            height = 3;
        }
        else if (height > Math.floor((double) inventory.getSize()/9)) {
            height = (int) Math.floor((double) inventory.getSize()/9);
        }
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx < width; dx++) {
                inventory.setItem(indexX+dx + indexY*9+dy*9, Data.getSeat0());
            }
        }

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, Data.getSeatDecoration());
            }
        }

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