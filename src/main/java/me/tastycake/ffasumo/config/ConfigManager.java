package me.tastycake.ffasumo.config;

import me.tastycake.ffasumo.utils.config.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager extends CustomConfig {
    public ConfigManager(String name) {
        super(name);
    }

    public static FileConfiguration getConfigs(String name) {
        return CustomConfig.getConfig(name);
    }
}
