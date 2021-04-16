package net.natroutter.survivaltweaks.features.scoreboards;

import net.natroutter.survivaltweaks.features.scoreboards.boards.DeathsBoard;
import net.natroutter.survivaltweaks.features.scoreboards.boards.ElytraDistanceBoard;
import net.natroutter.survivaltweaks.features.scoreboards.boards.LevelBoard;
import net.natroutter.survivaltweaks.features.scoreboards.boards.RichestPlayersBoard;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class ScoreboardHandler {

    ScoreboardManager sbManager;
    UUID currentBoard = null;

    ElytraDistanceBoard elytraDistanceBoard;
    LevelBoard levelBoard;
    RichestPlayersBoard richestBoard;
    DeathsBoard deathsBoard;

    public ScoreboardHandler() {
        sbManager = Bukkit.getScoreboardManager();

        //Register different scoreboards
        elytraDistanceBoard = new ElytraDistanceBoard(sbManager);
        levelBoard = new LevelBoard(sbManager);
        richestBoard = new RichestPlayersBoard(sbManager);
        deathsBoard = new DeathsBoard(sbManager);

    }

    public Scoreboard getCurrent() {
        if (currentBoard == null) {
            return elytraDistanceBoard.getBoard();
        }

        if (currentBoard.equals(elytraDistanceBoard.getUUID())) {
            return elytraDistanceBoard.getBoard();

        } else if (currentBoard.equals(levelBoard.getUUID())) {
            return elytraDistanceBoard.getBoard();

        } else if (currentBoard.equals(richestBoard.getUUID())) {
            return elytraDistanceBoard.getBoard();

        } else if (currentBoard.equals(deathsBoard.getUUID())) {
            return elytraDistanceBoard.getBoard();
        }
        return null;
    }

    public void rotateBoards() {
        if (currentBoard == null) {
            currentBoard = elytraDistanceBoard.getUUID();
            displayBoard(elytraDistanceBoard.getBoard());
            return;
        }

        if (currentBoard.equals(elytraDistanceBoard.getUUID())) {
            currentBoard = levelBoard.getUUID();
            displayBoard(levelBoard.getBoard());

        } else if (currentBoard.equals(levelBoard.getUUID())) {
            currentBoard = richestBoard.getUUID();
            displayBoard(richestBoard.getBoard());

        } else if (currentBoard.equals(richestBoard.getUUID())) {
            currentBoard = deathsBoard.getUUID();
            displayBoard(deathsBoard.getBoard());


        //Last scoreboard (start again)
        } else if (currentBoard.equals(deathsBoard.getUUID())) {
            currentBoard = elytraDistanceBoard.getUUID();
            displayBoard(elytraDistanceBoard.getBoard());
        }

    }

    public void displayBoard(Scoreboard board) {
        Bukkit.getOnlinePlayers().forEach((p)->{
            p.setScoreboard(board);
        });
    }

}
