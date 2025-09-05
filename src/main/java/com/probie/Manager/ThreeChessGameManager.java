package com.probie.Manager;

import com.probie.Data;
import java.util.HashSet;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.probie.Packet.ThreeChessGamePacket;

public class ThreeChessGameManager extends ThreeChessManager {

    private final static ThreeChessGameManager INSTANCE = new ThreeChessGameManager();

    private final HashSet<ThreeChessGamePacket> gamePackets = new HashSet<>();

    public void game(Player sendPlayer, Player acceptPlayer) {
        ThreeChessGamePacket gamePacket = new ThreeChessGamePacket(sendPlayer, acceptPlayer);
        sendPlayer.openInventory(gamePacket.getInventory());
        acceptPlayer.openInventory(gamePacket.getInventory());
        sendPlayer.playSound(sendPlayer.getLocation(), Data.getOpenSound(), 1.0f, 1.0f);
        acceptPlayer.playSound(acceptPlayer.getLocation(), Data.getOpenSound(), 1.0f, 1.0f);
        getGamePackets().add(gamePacket);
    }

    public ThreeChessGamePacket getGamePacketByInventory(Inventory inventory) {
        for (ThreeChessGamePacket gamePacket : getGamePackets()) {
            if (gamePacket.getInventory() == inventory) {
                return gamePacket;
            }
        }
        return null;
    }

    public void chess(ThreeChessGamePacket gamePacket, int slot) {
        switch (gamePacket.getTurn()) {
            case 1: {
                gamePacket.getInventory().setItem(slot, Data.getSeat1());
                gamePacket.setTurn(2);
                break;
            }
            case 2: {
                gamePacket.getInventory().setItem(slot, Data.getSeat2());
                gamePacket.setTurn(1);
                break;
            }
        }
        gamePacket.getPlayer1().playSound(gamePacket.getPlayer1().getLocation(), Data.getDownSound(), 1.0f, 1.0f);
        gamePacket.getPlayer2().playSound(gamePacket.getPlayer2().getLocation(), Data.getDownSound(), 1.0f, 1.0f);
        win(gamePacket);
    }

    public void win(ThreeChessGamePacket gamePacket) {
        Inventory inventory = gamePacket.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            try {
                try {
                    // 横
                    if (inventory.getItem(i).getType() == inventory.getItem(i+1).getType()) {
                        if (inventory.getItem(i+1).getType() == inventory.getItem(i+2).getType()) {
                            if (inventory.getItem(i).getType() == Data.getSeat1().getType()) {
                                gamePacket.setIsFinish(true);
                                praise(true, gamePacket.getPlayer1(), gamePacket.getPlayer2());
                                return;
                            }
                            else if (inventory.getItem(i).getType() == Data.getSeat2().getType()) {
                                gamePacket.setIsFinish(true);
                                praise(true, gamePacket.getPlayer1(), gamePacket.getPlayer2());
                                return;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
                try {
                    // 竖
                    if (inventory.getItem(i).getType() == inventory.getItem(i+9).getType()) {
                        if (inventory.getItem(i+9).getType() == inventory.getItem(i+9+9).getType()) {
                            if (inventory.getItem(i).getType() == Data.getSeat1().getType()) {
                                gamePacket.setIsFinish(true);
                                praise(true, gamePacket.getPlayer1(), gamePacket.getPlayer2());
                                return;
                            }
                            else if (inventory.getItem(i).getType() == Data.getSeat2().getType()) {
                                gamePacket.setIsFinish(true);
                                praise(true, gamePacket.getPlayer1(), gamePacket.getPlayer2());
                                return;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
                try {
                    // 斜下
                    if (inventory.getItem(i).getType() == inventory.getItem(i+9+1).getType()) {
                        if (inventory.getItem(i+9+1).getType() == inventory.getItem(i+9+1+9+1).getType()) {
                            if (inventory.getItem(i).getType() == Data.getSeat1().getType()) {
                                gamePacket.setIsFinish(true);
                                praise(true, gamePacket.getPlayer1(), gamePacket.getPlayer2());
                                return;
                            }
                            else if (inventory.getItem(i).getType() == Data.getSeat2().getType()) {
                                gamePacket.setIsFinish(true);
                                praise(true, gamePacket.getPlayer1(), gamePacket.getPlayer2());
                                return;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
                try {
                    // 斜上
                    if (inventory.getItem(i).getType() == inventory.getItem(i-9+1).getType()) {
                        if (inventory.getItem(i-9+1).getType() == inventory.getItem(i-9+1-9+1).getType()) {
                            if (inventory.getItem(i).getType() == Data.getSeat1().getType()) {
                                gamePacket.setIsFinish(true);
                                praise(true, gamePacket.getPlayer1(), gamePacket.getPlayer2());
                                return;
                            }
                            else if (inventory.getItem(i).getType() == Data.getSeat2().getType()) {
                                gamePacket.setIsFinish(true);
                                praise(true, gamePacket.getPlayer1(), gamePacket.getPlayer2());
                                return;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            } catch (NullPointerException ignored) {}
        }
        balance(gamePacket);
    }

    public void balance(ThreeChessGamePacket gamePacket) {
        for (int i = 0; i < gamePacket.getInventory().getSize(); i++) {
            try {
                if (gamePacket.getInventory().getItem(i).getType() == Data.getSeat0().getType()) {
                    return;
                }
            } catch (NullPointerException ignored) {}
        }
        gamePacket.setIsFinish(true);
        praise(false, gamePacket.getPlayer1(), gamePacket.getPlayer2());
    }

    public void praise(boolean isWin, Player winPlayer, Player lostPlayer) {
        if (isWin) {
            winPlayer.playSound(winPlayer.getLocation(), Data.getWinSound(), 1.0f, 1.0f);
            lostPlayer.playSound(lostPlayer.getLocation(), Data.getLostSound(), 1.0f, 1.0f);

            winPlayer.sendMessage(Data.getSimpleMessageHead()+Data.colorWord("你赢了！"));
            lostPlayer.sendMessage(Data.getSimpleMessageHead()+Data.colorWord("你输了..."));

            winPlayer.getInventory().addItem(new ItemStack(Material.DIAMOND));
            winPlayer.getInventory().addItem(new ItemStack(Material.GOLD_INGOT));
        } else {
            winPlayer.playSound(winPlayer.getLocation(), Data.getBalanceSound(), 1.0f, 1.0f);
            lostPlayer.playSound(winPlayer.getLocation(), Data.getBalanceSound(), 1.0f, 1.0f);

            winPlayer.sendMessage(Data.getSimpleMessageHead()+Data.colorWord("平局"));
            lostPlayer.sendMessage(Data.getSimpleMessageHead()+Data.colorWord("平局"));

            winPlayer.getInventory().addItem(new ItemStack(Material.IRON_ORE));
            lostPlayer.getInventory().addItem(new ItemStack(Material.IRON_ORE));
        }
    }

    public static ThreeChessGameManager getINSTANCE() {
        return INSTANCE;
    }

    public HashSet<ThreeChessGamePacket> getGamePackets() {
        return gamePackets;
    }

}