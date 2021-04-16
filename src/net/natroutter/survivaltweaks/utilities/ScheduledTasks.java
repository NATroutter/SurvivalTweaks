package net.natroutter.survivaltweaks.utilities;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.features.AfkDisplay;
import net.natroutter.survivaltweaks.features.scoreboards.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public class ScheduledTasks {

    public ScheduledTasks(JavaPlugin plugin, ScoreboardHandler sbHandler) {

        Integer scoreboardRotate_TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, sbHandler::rotateBoards, 0, 20L * 120);

        Integer lastmovedCheck_TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Map.Entry<UUID, Long> entry : AfkDisplay.lastMoved.entrySet()) {
                long lastmoved_sec = ((System.currentTimeMillis() - entry.getValue()) / 1000);
                long lastmoved_min = lastmoved_sec / 60;

                OfflinePlayer offP = Bukkit.getOfflinePlayer(entry.getKey());
                if (!offP.isOnline()) {
                    continue;
                }

                BasePlayer p = BasePlayer.from(offP);

                Utils.UpdateTabname(p, lastmoved_sec >= 5);
            }

        }, 0, 20L * 60);

    }

}
