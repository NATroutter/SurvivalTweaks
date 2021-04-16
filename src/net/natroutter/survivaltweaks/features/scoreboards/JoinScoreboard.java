package net.natroutter.survivaltweaks.features.scoreboards;

import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.features.scoreboards.ScoreboardHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

public class JoinScoreboard implements Listener {

    private final ScoreboardHandler sbHandler = SurvivalTweaks.getSbHandler();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Scoreboard sb = sbHandler.getCurrent();
        if (sb != null) {
            e.getPlayer().setScoreboard(sb);
        }
    }

}
