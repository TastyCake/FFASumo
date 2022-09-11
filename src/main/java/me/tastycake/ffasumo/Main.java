package me.tastycake.ffasumo;

import me.tastycake.ffasumo.Listeners.Quit;
import me.tastycake.ffasumo.arena.SetupCommand;
import me.tastycake.ffasumo.commands.EnterCommand;
import me.tastycake.ffasumo.match.MatchListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getCommand("joinmatch").setExecutor(new EnterCommand());
        getCommand("arena").setExecutor(new SetupCommand());

        getServer().getPluginManager().registerEvents(new MatchListener(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }
}
