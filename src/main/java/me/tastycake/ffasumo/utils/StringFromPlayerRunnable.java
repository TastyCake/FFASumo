package me.tastycake.ffasumo.utils;

import org.bukkit.entity.Player;

import java.util.List;

@FunctionalInterface
public interface StringFromPlayerRunnable {
    List<String> run(Player var1);
}
