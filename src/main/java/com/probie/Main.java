package com.probie;

import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.probie.Event.PlayerChessEvent;
import org.bukkit.scheduler.BukkitRunnable;
import com.probie.Event.PlayerCloseChessEvent;
import com.probie.Command.ThreeChessAcceptCommand;
import com.probie.Command.ThreeChessInviteCommand;
import com.probie.Event.PlayerJoinSendMessageEvent;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Data.init(this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinSendMessageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChessEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerCloseChessEvent(), this);
        Objects.requireNonNull(this.getCommand("threechess-invite")).setExecutor(new ThreeChessInviteCommand());
        Objects.requireNonNull(this.getCommand("threechess-accept")).setExecutor(new ThreeChessAcceptCommand());
        this.getLogger().info(Data.getMessageHead()+"加载成功");
    }

    @Override
    public void onDisable() {
        for (BukkitRunnable bukkitRunnable : Data.getBukkitRunnables()) {
            try {
                if (bukkitRunnable.isCancelled()) {
                    bukkitRunnable.cancel();
                }
            } catch (IllegalStateException ignored) {}
        }
        this.getLogger().info(Data.getMessageHead()+"卸载成功");
    }

}
