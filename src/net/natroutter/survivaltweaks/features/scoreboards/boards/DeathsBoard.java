package net.natroutter.survivaltweaks.features.scoreboards.boards;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class DeathsBoard {

    private UUID uuid;
    private Scoreboard board;
    private Objective obj;

    public DeathsBoard(ScoreboardManager sbManager) {
        this.uuid = UUID.randomUUID();
        this.board = sbManager.getNewScoreboard();

        obj = board.registerNewObjective("PlayerDeaths", "dummy","", RenderType.INTEGER);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§4§lDeaths");
    }

    public Scoreboard getBoard() {
        if (board != null) {
            for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                int distance = p.getStatistic(Statistic.DEATHS);
                if (distance > 0) {
                    Score score = obj.getScore("§7" + p.getName());
                    score.setScore(distance);
                }
            }
        }
        return board;
    }

    public UUID getUUID() {
        return uuid;
    }

}
