package me.tastycake.ffasumo.data;

import java.util.HashMap;
import java.util.Map;

public class Player {
    Map<org.bukkit.entity.Player, Integer> kills = new HashMap<>();
    Map<Integer, org.bukkit.entity.Player> fromKillsToPlayer = new HashMap<>();

    public int getKills(org.bukkit.entity.Player player) {
        return kills.getOrDefault(player, 0);
    }
    public Map<org.bukkit.entity.Player, Integer> getKillsMap() { return kills; }
    public Map<Integer, org.bukkit.entity.Player> getPlayerKills() { return fromKillsToPlayer; }
    public void addKills(org.bukkit.entity.Player player) {
        if (player != null) {
            kills.put(player, kills.getOrDefault(player, 0) + 1);
            fromKillsToPlayer.put(kills.getOrDefault(player, 0), player);
        }
    }
    public void resetKills(org.bukkit.entity.Player player) {
        fromKillsToPlayer.put(kills.get(player), null);
        kills.put(player, null);
    }
}
