package com.probie.Runnable;

import java.util.HashSet;
import java.util.UUID;
import org.bukkit.Sound;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import com.probie.LuckyTime;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.ItemStack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerGameSecondBukkitRunnable implements BukkitRunnable {

    private volatile static PlayerGameSecondBukkitRunnable INSTANCE;

    private volatile org.bukkit.scheduler.BukkitRunnable bukkitRunnable;

    private volatile Plugin plugin;

    private volatile HashSet<Material> itemSet = new HashSet<>();
    private volatile ConcurrentHashMap<UUID, Integer> playerGameSecondMap = new ConcurrentHashMap<>();

    public synchronized static PlayerGameSecondBukkitRunnable getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerGameSecondBukkitRunnable();
        }
        return INSTANCE;
    }

    @Override
    public synchronized void register(Plugin plugin) {
        if (this.plugin != plugin) {
            if (this.plugin == null) {
                for (Material material : Material.values()) {
                    if (material.isItem()) {
                        itemSet.add(material);
                    }
                }
            }
            this.plugin = plugin;
            if (getBukkitRunnable() != null) {
                try {
                    getBukkitRunnable().cancel();
                } catch (IllegalStateException ignored) {}
            }
            this.bukkitRunnable = null;
        }

        if (getBukkitRunnable() == null) {
            bukkitRunnable = new org.bukkit.scheduler.BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                        getPlayerGameSecondMap().put(onlinePlayer.getUniqueId(), getPlayerGameSecondMap().getOrDefault(onlinePlayer.getUniqueId(), 0) + 1);
                        if (getPlayerGameSecondMap().getOrDefault(onlinePlayer.getUniqueId(), 0) >= LuckyTime.getLuckyTime()) {
                            ItemStack itemStack = new ItemStack(
                                    (Material) getItemSet().toArray()[ThreadLocalRandom.current().nextInt(0, getItemSet().size()-1)],
                                    ThreadLocalRandom.current().nextInt(LuckyTime.getMinCount(), LuckyTime.getMaxCount() + 1)
                            );
                            onlinePlayer.getInventory().addItem(itemStack);
                            Bukkit.getOnlinePlayers().forEach(player -> {
                                player.sendMessage(
                                        LuckyTime.getSimpleMessageHead()
                                                +"玩家"+LuckyTime.colorPlayerName(onlinePlayer.getName())
                                                +"获得了"+LuckyTime.colorWord(itemStack.getType().name(), "\u00a7a")
                                                +" * "+itemStack.getAmount()
                                );
                                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                            });
                            getPlayerGameSecondMap().put(onlinePlayer.getUniqueId(), 0);
                        }
                    });
                }
            };
            getBukkitRunnable().runTaskTimerAsynchronously(getPlugin(), 0, 20 * 1);
        }
    }

    public synchronized org.bukkit.scheduler.BukkitRunnable getBukkitRunnable() {
        return bukkitRunnable;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public HashSet<Material> getItemSet() {
        return itemSet;
    }

    public synchronized ConcurrentHashMap<UUID, Integer> getPlayerGameSecondMap() {
        return playerGameSecondMap;
    }

}