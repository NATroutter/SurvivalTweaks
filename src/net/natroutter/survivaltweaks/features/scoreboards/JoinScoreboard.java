package net.natroutter.survivaltweaks.features.scoreboards;

import net.natroutter.natlibs.handlers.database.YamlDatabase;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.features.scoreboards.ScoreboardHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

public class JoinScoreboard implements Listener {

    private ScoreboardHandler sbHandler;
    private YamlDatabase database;

    public JoinScoreboard(Handler handler) {
        sbHandler = handler.getScoreboardHandler();
        database = handler.getYamlDatabase();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (database.getBoolean(p, "SBVisible") == null) {
            database.save(p, "SBVisible", true);
        }

        Scoreboard sb = sbHandler.getCurrent();
        if (sb != null) {
            if (database.getBoolean(p, "SBVisible")) {
                p.setScoreboard(sb);
            }
        }
    }

}
