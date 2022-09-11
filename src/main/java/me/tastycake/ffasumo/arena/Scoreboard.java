package me.tastycake.ffasumo.arena;

import me.tastycake.ffasumo.Main;
import me.tastycake.ffasumo.utils.Chat;
import me.tastycake.ffasumo.utils.ScoreboardUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard extends ScoreboardUtil {

    me.tastycake.ffasumo.data.Player data = new me.tastycake.ffasumo.data.Player();

    FileConfiguration config = Main.getInstance().getConfig();

    Map<Player, Integer> playerKills = data.getKillsMap();

    Player highPlayer;
    Player secPlayer;
    Player thirdPlayer;

    int highKills = 0;
    int secHighKills = 0;
    int thirdHighKills = 0;

    public Scoreboard() {
        super(20 * 5);
        String name = Chat.code(config.getString("SCOREBOARD_NAME"));
        for (Integer kills : playerKills.values()) {
            if (kills > highKills) {
                highKills = kills;
                highPlayer = data.getPlayerKills().get(highKills);
                return;
            } else if (kills <= secHighKills) {
                secHighKills = kills;
                secPlayer = data.getPlayerKills().get(secHighKills);
                return;
            } else if (kills <= thirdHighKills) {
                thirdHighKills = kills;
                thirdPlayer = data.getPlayerKills().get(thirdHighKills);
                return;
            }
        }
        createBoard(name, player -> {
            List<String> objects = new ArrayList<>();
            objects.add("&8&m-------------------------");
            objects.add("&bTop kills: ");
            if (highPlayer != null) {
                objects.add("&6" + highPlayer.getName() + ": " + highKills);
            }
            if (secPlayer != null) {
                objects.add("&6" + secPlayer.getName() + ": " + secHighKills);
            }
            if (thirdPlayer != null) {
                objects.add("&6" + thirdPlayer.getName() + ": " + thirdHighKills);
            }
            objects.add("");
            objects.add("&8&m-------------------------&7");
            return objects;
        });
    }
}
