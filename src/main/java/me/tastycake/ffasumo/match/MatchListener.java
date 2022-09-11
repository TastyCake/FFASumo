package me.tastycake.ffasumo.match;

import me.tastycake.ffasumo.config.ConfigManager;
import me.tastycake.ffasumo.utils.config.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MatchListener extends Match implements Listener {

    Map<Player, Player> lastDamager = new HashMap<>();

    FileConfiguration config = ConfigManager.getConfig("arenas");

    me.tastycake.ffasumo.data.Player data = new me.tastycake.ffasumo.data.Player();

    @EventHandler
    public void Debug(PlayerMoveEvent e) {
        e.getPlayer().sendMessage(getPlayerInMatch((Player) e.getPlayer()));
    }

    @EventHandler
    public void damage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof Player)) return;
        Player attacker = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();

        if (!Objects.equals(getPlayerInMatch(attacker), "none") && !Objects.equals(getPlayerInMatch(victim), "none")) {
            lastDamager.put(victim, attacker);
            e.setDamage(0);
        }
    }
    @EventHandler
    public void voidDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof  Player)) return;
        Player player = (Player) e.getEntity();

        if (e.getCause() != EntityDamageEvent.DamageCause.VOID) return;

        if (!Objects.equals(getPlayerInMatch(player), "none")) {

            data.addKills(lastDamager.getOrDefault(player, null));

            lastDamager.put(player, null);

            player.teleport((Location) config.get("arenas." + getPlayerInMatch(player) + ".spawn"));
        }
    }
    @EventHandler
    public void blocker(BlockBreakEvent e) {
        if (!Objects.equals(getPlayerInMatch((Player) e.getPlayer()), "none")) {
            e.setCancelled(true);
        }
    }
}
