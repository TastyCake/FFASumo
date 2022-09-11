package me.tastycake.ffasumo.utils;

import me.tastycake.ffasumo.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.List;

public class ScoreboardUtil {
    private final int updateTicks;
    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board;
    private final Objective objective;
    private String title;
    private StringFromPlayerRunnable stringFromPlayerRunnable;

    public ScoreboardUtil(int updateTicks) {
        this.board = this.manager.getNewScoreboard();
        this.objective = this.board.registerNewObjective("test", "dummy");
        this.updateTicks = updateTicks;
    }

    public void createBoard(String str, StringFromPlayerRunnable playerRunnable) {
        this.title = str;
        this.stringFromPlayerRunnable = playerRunnable;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            public void run() {
                List<String> objects = stringFromPlayerRunnable.run(p);
                if (title != null || objects != null) {

                    ScoreboardManager manager = Bukkit.getScoreboardManager();
                    final Scoreboard board = manager.getNewScoreboard();
                    final Objective objective = board.registerNewObjective("test", "dummy");
                    objective.setDisplaySlot(DisplaySlot.BELOW_NAME.SIDEBAR);
                    objective.setDisplayName(Chat.code(title));
                    int index = 0;
                    for (int i = objects.size() - 1; i >= 0; i--) {
                        Score score = objective.getScore(Chat.code(objects.get(i)));
                        score.setScore(index);
                        index++;
                    }
                    p.setScoreboard(board);
                }
            }
        },0, updateTicks);
    }
}
