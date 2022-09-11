package me.tastycake.ffasumo.utils.config;

import lombok.Getter;
import me.tastycake.ffasumo.Main;
import me.tastycake.ffasumo.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
public class CustomConfig {

    private final FileConfiguration config;
    private final File file;

    public CustomConfig(String name) {
        file = new File(Main.getInstance().getDataFolder(), name + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignore) {}
        }

        config = YamlConfiguration.loadConfiguration(file);
        saveConfig();
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.broadcastMessage(Chat.code("&7Couldnt save file"));
        }
    }

    public static FileConfiguration getConfig(String name) {
        File file = new File(Main.getInstance().getDataFolder(), name + ".yml");

        if (file.exists()) {
            return YamlConfiguration.loadConfiguration(file);
        } else {
            return null;
        }
    }
}
