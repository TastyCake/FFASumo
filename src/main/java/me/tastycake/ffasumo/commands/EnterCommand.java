package me.tastycake.ffasumo.commands;

import me.tastycake.ffasumo.config.ConfigManager;
import me.tastycake.ffasumo.match.EnterMatch;
import me.tastycake.ffasumo.utils.config.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnterCommand implements CommandExecutor {

    CustomConfig arenas = new ConfigManager("arenas");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player) {
                if (arenas.getConfig().contains("arenas." + args[0])) {
                    Player player = (Player) sender;

                    new EnterMatch(player, args[0]);
                }
            }
        }
        return false;
    }
}
