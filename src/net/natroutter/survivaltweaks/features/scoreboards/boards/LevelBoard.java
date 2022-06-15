package net.natroutter.survivaltweaks.features.scoreboards.boards;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class LevelBoard {

    private YamlDatabase database;

    private UUID uuid;
    private Scoreboard board;
    private Objective obj;

    private Utils utils;

    public LevelBoard(Handler handler, ScoreboardManager sbManager) {
        this.uuid = UUID.randomUUID();
        this.board = sbManager.getNewScoreboard();

        database = handler.getYamlDatabase();

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
