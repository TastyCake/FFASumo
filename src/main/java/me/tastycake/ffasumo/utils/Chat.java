package me.tastycake.ffasumo.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {
    public static String code(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String line() {
        return code("&8----------------------------------");
    }
    public static void lineln(Player player) {
        player.sendMessage(line());
        player.sendMessage("");
    }
    public static void lnline(Player player) {
        player.sendMessage("");
        player.sendMessage(line());
    }
}
