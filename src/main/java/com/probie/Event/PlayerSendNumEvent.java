package com.probie.Event;

import com.probie.Main;

import java.util.HashSet;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import com.probie.Database.PluginData;
import com.probie.Command.GuessNumCommand;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerSendNumEvent implements Listener {

    private static HashSet<ItemStack> praiseItems = new HashSet<>();

    @EventHandler
    public void sendNumberEvent(AsyncPlayerChatEvent asyncPlayerChatEvent) {
        if (PluginData.IsGaming) {
            try {
                int num = Integer.parseInt(asyncPlayerChatEvent.getMessage());
                if (num >= 0 && num <= PluginData.Range) {
                    asyncPlayerChatEvent.setCancelled(true);
                    Player player = asyncPlayerChatEvent.getPlayer();
                    int mistake = Math.abs(num-PluginData.Answer);

                    // 猜对了
                    if (mistake == 0) {
                        PluginData.sendMessageToAllPlayer(String.format(asyncPlayerChatEvent.getFormat(), player.getName(), asyncPlayerChatEvent.getMessage()+" "+"("+"\u00a7a"+"猜对了!"+"\u00a7r"+")"));
                        praisePlayer(player);
                        GuessNumCommand.stopGame(true);
                        PluginData.sendMessageToAllPlayer(Main.getSimpleMessageHead()+"游戏结束,下一场将在"+"\u00a7a"+PluginData.Delay+"\u00a7r"+"秒后开启");
                        GuessNumCommand.startGame(PluginData.Delay);
                    }
                    // 很接近
                    else if (mistake <= 5) {
                        PluginData.sendMessageToAllPlayer(String.format(asyncPlayerChatEvent.getFormat(), player.getName(), asyncPlayerChatEvent.getMessage()+" "+"("+"\u00a7a"+"很接近了"+"\u00a7r"+")"));
                    }
                    else {
                        if (num < PluginData.Answer) {
                            // 猜小了但很接近
                            if (Math.abs(num-PluginData.Answer) <= 10) {
                                PluginData.sendMessageToAllPlayer(String.format(asyncPlayerChatEvent.getFormat(), player.getName(), asyncPlayerChatEvent.getMessage()+" "+"("+"\u00a7a"+"有点小了"+"\u00a7r"+")"));
                            }
                            // 猜小了但有点接近
                            else if (Math.abs(num-PluginData.Answer) <= 100) {
                                PluginData.sendMessageToAllPlayer(String.format(asyncPlayerChatEvent.getFormat(), player.getName(), asyncPlayerChatEvent.getMessage()+" "+"("+"\u00a7a"+"小了呀"+"\u00a7r"+")"));
                            }
                            // 猜小了且不接近
                            else {
                                PluginData.sendMessageToAllPlayer(String.format(asyncPlayerChatEvent.getFormat(), player.getName(), asyncPlayerChatEvent.getMessage()+" "+"("+"\u00a7a"+"太小了吧"+"\u00a7r"+")"));
                            }
                        }
                        else if (num > PluginData.Answer) {
                            // 猜大了但很接近
                            if (Math.abs(num-PluginData.Answer) <= 10) {
                                PluginData.sendMessageToAllPlayer(String.format(asyncPlayerChatEvent.getFormat(), player.getName(), asyncPlayerChatEvent.getMessage()+" "+"("+"\u00a7a"+"有点大了"+"\u00a7r"+")"));
                            }
                            // 猜大了但有点接近
                            else if (Math.abs(num-PluginData.Answer) <= 100) {
                                PluginData.sendMessageToAllPlayer(String.format(asyncPlayerChatEvent.getFormat(), player.getName(), asyncPlayerChatEvent.getMessage()+" "+"("+"\u00a7a"+"大了呀"+"\u00a7r"+")"));
                            }
                            // 猜大了且不接近
                            else {
                                PluginData.sendMessageToAllPlayer(String.format(asyncPlayerChatEvent.getFormat(), player.getName(), asyncPlayerChatEvent.getMessage()+" "+"("+"\u00a7a"+"太大了吧"+"\u00a7r"+")"));
                            }
                        }
                    }
                }
            } catch (NumberFormatException ignored) {}
        }
    }

    public static void praisePlayer(Player player) {
        ItemStack praiseItem = (ItemStack) getPraiseItems().toArray()[PluginData.getRange(0, getPraiseItems().size()-1)];
        praiseItem.setAmount(PluginData.getRange(1, 64));
        switch (PluginData.getRange(0, 3)) {
            case 0: {
                ItemMeta itemMeta = praiseItem.getItemMeta();
                itemMeta.setItemName("奖励");
                praiseItem.setItemMeta(itemMeta);
            }
            case 1: {
                ItemMeta itemMeta = praiseItem.getItemMeta();
                itemMeta.setItemName("大自然的馈赠");
                praiseItem.setItemMeta(itemMeta);
            }
            case 2: {
                ItemMeta itemMeta = praiseItem.getItemMeta();
                itemMeta.setItemName("服主的恩泽");
                praiseItem.setItemMeta(itemMeta);
            }
        }
        player.getInventory().addItem(praiseItem);
        PluginData.sendMessageToAllPlayer(Main.getSimpleMessageHead()+"恭喜玩家"+"\u00a7a"+player.getName()+"\u00a7r"+"获得了奖励 ("+praiseItem.getType().name()+" * "+praiseItem.getAmount()+")");
    }

    public static HashSet<ItemStack> getPraiseItems() {
        return praiseItems;
    }

}