package me.tastycake.ffasumo.match;

import org.bukkit.entity.Player;

import java.util.*;

public class Match {

    Map<Player, String> playerInMatch = new HashMap<>();
    Map<String, List<Player>> playersInArena = new HashMap<>();

    public List<Player> getPlayers(String arena) {
        List<Player> list = new ArrayList<>();
        return playersInArena.getOrDefault(arena, list);
    }
    public void addPlayer(Player player, String arena) {
        List<Player> test = new ArrayList<>();
        test = getPlayers(arena);
        test.add(player);
        playerInMatch.put(player, arena);
        playersInArena.put(arena, test);
    }
    public String getPlayerInMatch(Player player) {
        return playerInMatch.getOrDefault(player, "none");
    }
    public void removePlayer(Player player, String match) {
        playerInMatch.put(player, "none");
        List<Player> test = new ArrayList<>();
        test = playersInArena.get(match);
        test.remove(player);
        playersInArena.put(match, test);
    }
}
