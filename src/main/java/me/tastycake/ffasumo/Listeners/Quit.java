package me.tastycake.ffasumo.Listeners;

import me.tastycake.ffasumo.config.ConfigManager;
import me.tastycake.ffasumo.match.Match;
import me.tastycake.ffasumo.utils.config.CustomConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class Quit extends Match implements Listener {

    CustomConfig arenas = new ConfigManager("arenas");

    @EventHandler
    public void quitEvent(PlayerQuitEvent e) {
        if (!Objects.equals(getPlayerInMatch(e.getPlayer()), "none")) {
            Player player = (Player) e.getPlayer();

            player.setBedSpawnLocation((Location) arenas.getConfig().get(getPlayerInMatch(player) + ".lobbyspawn"));
            me.tastycake.ffasumo.data.Player data = new me.tastycake.ffasumo.data.Player();
            data.resetKills(player);
            removePlayer(player, getPlayerInMatch(player));
        }
    }
}
