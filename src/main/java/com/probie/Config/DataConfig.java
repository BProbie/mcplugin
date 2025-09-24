package com.probie.Config;

import java.io.File;
import java.io.IOException;
import com.probie.LuckyTime;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.InvalidConfigurationException;

public class DataConfig implements Config {

    private volatile static DataConfig INSTANCE;

    private volatile FileConfiguration configuration;

    private volatile Plugin plugin;

    public static DataConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataConfig();
        }
        return INSTANCE;
    }

    @Override
    public void register(Plugin plugin) {
        if (this.plugin != plugin) {
            this.plugin = plugin;
            configuration = null;
            LuckyTime.setLuckyTime(getConfiguration().getInt(LuckyTime.getKeyLuckyTime(), 60 * 10));
        }
    }

    public FileConfiguration getConfiguration() {
        if (configuration == null) {
            configuration = this.plugin.getConfig();
            File file = new File(LuckyTime.getFilePath());
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ioException) {
                    throw new RuntimeException(ioException);
                }
            }
            try {
                configuration.load(file);
            } catch (IOException | InvalidConfigurationException exception) {
                throw new RuntimeException(exception);
            }
        }
        return configuration;
    }

    public Plugin getPlugin() {
        return plugin;
    }

}