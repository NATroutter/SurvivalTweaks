package net.natroutter.survivaltweaks.features.scoreboards.boards;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class RichestPlayersBoard {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    private UUID uuid;
    private Scoreboard board;
    private Objective obj;

    public RichestPlayersBoard(ScoreboardManager sbManager) {
        this.uuid = UUID.randomUUID();
        this.board = sbManager.getNewScoreboard();

        obj = board.registerNewObjective("RichestPlayers", "dummy","", RenderType.INTEGER);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§4§lRichest Players");
    }

    public Scoreboard getBoard() {
        if (board != null) {
            Utils.calculateRichest();
            for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {

                Integer riches = database.getInt(p, "Riches");
                if (riches == null) { riches = 0; }

                if (riches > 0) {
                    Score score = obj.getScore("§7" + p.getName());
                    score.setScore(riches);
                }
            }
        }
        return board;
    }

    public UUID getUUID() {
        return uuid;
    }

}

