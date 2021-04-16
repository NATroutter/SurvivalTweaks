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

    private JavaPlugin plugin;

    private ScoreboardHandler sbHandler;
    private Integer scoreboardRotate_TaskID;

    private Integer lastmovedCheck_TaskID;

    public ScheduledTasks(JavaPlugin plugin, ScoreboardHandler sbHandler) {
        this.plugin = plugin;
        this.sbHandler = sbHandler;

        scoreboardRotate_TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()->{
            sbHandler.rotateBoards();
        },0, 20L * 120);

        lastmovedCheck_TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()->{
            for (Map.Entry<UUID, Long> entry : AfkDisplay.lastMoved.entrySet()) {
                long lastmoved_sec = ((System.currentTimeMillis() - entry.getValue()) / 1000);
                long lastmoved_min = lastmoved_sec / 60;

                OfflinePlayer offP = Bukkit.getOfflinePlayer(entry.getKey());
                if (!offP.isOnline()) { continue; }

                BasePlayer p = BasePlayer.from(offP);

                if (lastmoved_sec >= 5) {
                    AfkDisplay.isAFK.put(entry.getKey(), true);
                } else {
                    if(AfkDisplay.isAFK.containsKey(entry.getKey())) {
                        AfkDisplay.isAFK.put(entry.getKey(), false);
                    }
                }
                Utils.UpdateTabname(p);
            }

        },0, 20L * 60);

    }

}
