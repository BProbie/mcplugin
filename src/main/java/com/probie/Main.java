package com.probie;

import java.util.Objects;
import org.bukkit.Bukkit;
import java.io.IOException;
import com.probie.Config.DataConfig;
import com.probie.Config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.probie.Command.LuckyTimeCommand;
import com.probie.Runnable.RunnableManager;
import com.probie.Event.PlayerJoinSendMessageEvent;
import com.probie.Runnable.PlayerGameSecondBukkitRunnable;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigManager.getInstance().register(DataConfig.getInstance(), this);
        RunnableManager.getInstance().register(PlayerGameSecondBukkitRunnable.getInstance(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinSendMessageEvent(), this);
        Objects.requireNonNull(this.getCommand("luckytime")).setExecutor(new LuckyTimeCommand());
        this.getLogger().info(LuckyTime.getMessageHead()+"插件正常运行");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            DataConfig.getInstance().getConfiguration().save(LuckyTime.getFilePath());
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
        this.getLogger().info(LuckyTime.getMessageHead()+"插件卸载成功");
    }

}
