package net.natroutter.survivaltweaks.features.scoreboards.boards;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class LevelBoard {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    private UUID uuid;
    private Scoreboard board;
    private Objective obj;

    public LevelBoard(ScoreboardManager sbManager) {
        this.uuid = UUID.randomUUID();
        this.board = sbManager.getNewScoreboard();

        obj = board.registerNewObjective("PlayerLevels", "dummy","", RenderType.INTEGER);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§4§lPlayer levels");
    }

    public Scoreboard getBoard() {
        if (board != null) {
            for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

                Integer level = database.getInt(p, "level");
                if (level == null) { level = 0; }

                if (level > 0) {
                    Score score = obj.getScore("§7" + p.getName());
                    score.setScore(level);
                }
            }
        }
        return board;
    }

    public UUID getUUID() {
        return uuid;
    }

}
