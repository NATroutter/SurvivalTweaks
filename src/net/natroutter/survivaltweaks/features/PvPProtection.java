package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.UUID;

public class PvPProtection implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    int cooldown = 60 * 5;
    public static HashMap<UUID, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player victim = (Player)e.getEntity();
            Player attacker = (Player)e.getDamager();

            if (database.getBoolean(victim, "PvP")) {
                e.setCancelled(true);
                Utils.sendAction(victim, "§cYou have been protected from §4" + attacker.getName() + "'s §cattacks");
                Utils.sendAction(attacker, "§cYou can't attack §4" + victim.getName() + " §cthey are protected");

            } else if (database.getBoolean(attacker, "PvP")) {
                e.setCancelled(true);
                Utils.sendAction(victim, "§cYou have been protected from §4" + attacker.getName() + "'s §cattacks");
                Utils.sendAction(attacker, "§cYou can't attack §4" + victim.getName() + " §cwhen you are pvp protected");
            } else {
                cooldowns.put(victim.getUniqueId(), System.currentTimeMillis());
                cooldowns.put(attacker.getUniqueId(), System.currentTimeMillis());
            }

        }
    }

}
