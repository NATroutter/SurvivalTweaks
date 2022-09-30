package net.natroutter.survivaltweaks.utilities;

import net.natroutter.natlibs.handlers.ParticleSpawner;
import net.natroutter.natlibs.objects.ParticleSettings;
import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.features.afkdisplay.AfkHandler;
import net.natroutter.survivaltweaks.features.scoreboards.ScoreboardHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public class ScheduledTasks {

    public ScheduledTasks(Handler handler) {
        JavaPlugin plugin = handler.getInstance();
        Utilities util = handler.getUtilities();
        ScoreboardHandler sbHandler = handler.getScoreboardHandler();
        Utils utils = handler.getUtils();
        AfkHandler afkHandler = handler.getAfkHandler();
        ParticleSpawner spawner = handler.getParticleSpawner();

        //--[ Scoreboard rotator ]----------------------------------------------------------

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, sbHandler::rotateBoards, 0, 20L * 120);

        //--[ Afk updater ]----------------------------------------------------------

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (afkHandler.lastMoved == null ) {return;}
            for (Map.Entry<UUID, Long> entry : afkHandler.lastMoved.entrySet()) {
                long lastmoved_sec = ((System.currentTimeMillis() - entry.getValue()) / 1000);
                long lastmoved_min = lastmoved_sec / 60;

                OfflinePlayer offP = Bukkit.getOfflinePlayer(entry.getKey());
                if (!offP.isOnline()) {
                    continue;
                }

                Player p = (Player)offP;

                utils.updateTabname(p, lastmoved_min >= 3);
            }

        }, 0, 20L * 60);

        //--[ Survival light block particles ]----------------------------------------------------------

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {

                if (!p.getInventory().getItemInMainHand().getType().equals(Material.LIGHT)) {continue;}
                if (!p.getGameMode().equals(GameMode.SURVIVAL)) {continue;}

                for (Block block : util.getBlocks(p.getLocation(), 10)) {
                    if (!block.getType().equals(Material.LIGHT)) {continue;}

                    spawner.spawnParticle(p, new ParticleSettings(Particle.GLOW, block.getLocation().add(0.5, 0.5, 0.5), 10, 0, 0, 0, 0));
                }
            }
        }, 0, 20L);
    }

}
