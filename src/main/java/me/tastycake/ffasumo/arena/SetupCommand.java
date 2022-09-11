package me.tastycake.ffasumo.arena;

import me.tastycake.ffasumo.config.ConfigManager;
import me.tastycake.ffasumo.utils.Chat;
import me.tastycake.ffasumo.utils.config.CustomConfig;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    CustomConfig arenas = new ConfigManager("arenas");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("ffasumo.setup")) {
                if (args.length == 0) {
                    Chat.lineln(player);
                    player.sendMessage(Chat.code(" &f- &b/arena create [arenaName] [maxPlayers] &7(Create new arena)"));
                    player.sendMessage(Chat.code(" &f- &b/arena remove [arenaName] &7(Remove arena)"));
                    player.sendMessage(Chat.code(" &f- &b/arena spawn [arenaName] &7(Set the spawn of the arena)"));
                    player.sendMessage(Chat.code(" &f- &b/arena lobbyspawn [arenaName] &7(Set the lobby spawn of the arena)"));
                    player.sendMessage(Chat.code(" &f- &b/arena list &7(Send you the list of the arenas)"));
                    Chat.lnline(player);
                } else {
                    if (args[0].equals("list")) {
                        Chat.lineln(player);
                        for (String arena : arenas.getConfig().getStringList("arenas")) {
                            player.sendMessage(Chat.code("&b" + arena));
                        }
                    }
                    if (player.hasPermission("ffasumo.setup")) {
                        if (args[0].equals("create")) {
                            if (!arenas.getConfig().contains(args[1])) {
                                if (args.length == 3) {
                                    sender.sendMessage(Chat.code("&aYou created arena " + args[1]));
                                    Location location = new Location(player.getWorld(), 0, 0, 0);
                                    arenas.getConfig().set("arenas." + args[1] + ".maxPlayers", Integer.parseInt(args[2]));
                                    arenas.getConfig().set("arenas." + args[1] + ".lobbyspawn", location);
                                    arenas.getConfig().set("arenas." + args[1] + ".spawn", location);
                                    arenas.saveConfig();
                                } else {
                                    player.sendMessage(Chat.code("&cPlease do /arena create [arenaName] [maxPlayers]"));
                                }
                            } else {
                                player.sendMessage(Chat.code("&cArena with this name already exist"));
                            }
                        }
                        if (args[0].equals("remove")) {
                            if (args.length == 2) {
                                if (arenas.getConfig().contains("arenas." + args[1])) {
                                    sender.sendMessage(Chat.code("&aYou removed arena " + args[1]));
                                    arenas.getConfig().set("arenas." + args[1], null);
                                    arenas.saveConfig();
                                } else {
                                    player.sendMessage(Chat.code("&cNo arena with this name found"));
                                }
                            } else {
                                player.sendMessage(Chat.code("&cPlease /arena remove [arenaName]"));
                            }
                        }
                        if (args[0].equals("spawn")) {
                            if (args.length == 2) {
                                if (arenas.getConfig().contains("arenas." + args[1])) {
                                    sender.sendMessage(Chat.code("&aYou setted the spawn of the arena " + args[1]));
                                    arenas.getConfig().set("arenas." + args[1] + ".spawn", player.getLocation());
                                    arenas.saveConfig();
                                } else {
                                    player.sendMessage(Chat.code("&cNo arena with this name found"));
                                }
                            } else {
                                player.sendMessage(Chat.code("&cPlease /arena spawn [arenaName]"));
                            }
                        }
                        if (args[0].equals("lobbyspawn")) {
                            if (args.length == 2) {
                                if (arenas.getConfig().contains("arenas." + args[1])) {
                                    sender.sendMessage(Chat.code("&aYou setted the lobby spawn of the arena " + args[1]));
                                    arenas.getConfig().set("arenas." + args[1] + ".lobbyspawn", player.getLocation());
                                    arenas.saveConfig();
                                } else {
                                    player.sendMessage(Chat.code("&cNo arena with this name found"));
                                }
                            } else {
                                player.sendMessage(Chat.code("&cPlease /arena lobbyspawn [arenaName]"));
                            }
                        }
                    } else {
                        player.sendMessage(Chat.code("Unknown command"));
                    }
                }
            }
        }
        return false;
    }
}
