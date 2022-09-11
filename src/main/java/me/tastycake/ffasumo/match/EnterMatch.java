package me.tastycake.ffasumo.match;

import me.tastycake.ffasumo.arena.Scoreboard;
import me.tastycake.ffasumo.config.ConfigManager;
import me.tastycake.ffasumo.utils.Chat;
import me.tastycake.ffasumo.utils.config.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;

public class EnterMatch extends Match {

    CustomConfig arenas = new ConfigManager("arenas");

    public EnterMatch(Player player, String match) {
        if (getPlayers(match).size() < arenas.getConfig().getInt("arenas." + match + ".maxPlayers")) {
            if (Objects.equals(getPlayerInMatch(player), "none") || getPlayerInMatch(player) == null) {
                Bukkit.broadcastMessage(getPlayerInMatch(player));
                addPlayer(player, match);
                Bukkit.broadcastMessage(getPlayerInMatch(player));
                player.teleport((Location) arenas.getConfig().get("arenas." + match + ".spawn"));
                // player.setBedSpawnLocation((Location) arenas.getConfig().get("arenas." + match + ".spawn"));
                player.sendMessage(Chat.code("&aYou entered " + match + " arena!"));
                player.setGameMode(GameMode.SURVIVAL);
                new Scoreboard();
            } else {
                player.sendMessage(Chat.code("&cYou already in a match!"));
            }
        } else {
            player.sendMessage(Chat.code("&cThis match is full"));
        }
    }
}
