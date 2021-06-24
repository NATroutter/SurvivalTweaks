package net.natroutter.survivaltweaks.utilities;

import net.natroutter.natlibs.objects.ParticleSettings;
import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.features.AfkDisplay;
import net.natroutter.survivaltweaks.features.scoreboards.ScoreboardHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public class ScheduledTasks {

    private static final Utilities util = SurvivalTweaks.getUtilities();

    public ScheduledTasks(JavaPlugin plugin, ScoreboardHandler sbHandler) {

        //--[ Scoreboard rotator ]----------------------------------------------------------

        Integer scoreboardRotate_TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, sbHandler::rotateBoards, 0, 20L * 120);

        //--[ Afk updater ]----------------------------------------------------------

        Integer lastmovedCheck_TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Map.Entry<UUID, Long> entry : AfkDisplay.lastMoved.entrySet()) {
                long lastmoved_sec = ((System.currentTimeMillis() - entry.getValue()) / 1000);
                long lastmoved_min = lastmoved_sec / 60;

                OfflinePlayer offP = Bukkit.getOfflinePlayer(entry.getKey());
                if (!offP.isOnline()) {
                    continue;
                }

                Player p = (Player)offP;

                Utils.updateTabname(p, lastmoved_sec >= 5);
            }

        }, 0, 20L * 60);

        //--[ Survival light block particles ]----------------------------------------------------------

        Integer survivalLight_TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {

                if (!p.getInventory().getItemInMainHand().getType().equals(Material.LIGHT)) {continue;}
                if (!p.getGameMode().equals(GameMode.SURVIVAL)) {continue;}

                for (Block block : util.getBlocks(p.getLocation(), 10)) {
                    if (!block.getType().equals(Material.LIGHT)) {continue;}

                    util.spawnParticle(p, new ParticleSettings(Particle.LIGHT, block.getLocation().add(0.5, 0.5, 0.5), 1, 0, 0, 0, 0));
                }
            }
        }, 0, 20L);
    }

}
