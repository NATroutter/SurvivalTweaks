package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.database.YamlDatabase;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PvPProtection implements Listener {

    private YamlDatabase database;
    private Utils utils;

    int cooldown = 60 * 5;
    public static HashMap<UUID, Long> cooldowns = new HashMap<>();

    public PvPProtection(Handler handler) {
        database = handler.getYamlDatabase();
        utils = handler.getUtils();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        cooldowns.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getHitEntity() instanceof Player victim) {
            if (e.getEntity().getShooter() instanceof Player attacker) {
                if (database.getBoolean(victim, "PvP")) {
                    e.setCancelled(true);
                    utils.sendAction(victim, "§cYou have been protected from §4" + attacker.getName() + "'s §cattacks");
                    utils.sendAction(attacker, "§cYou can't attack §4" + victim.getName() + " §cthey are protected");

                } else if (database.getBoolean(attacker, "PvP")) {
                    e.setCancelled(true);
                    utils.sendAction(victim, "§cYou have been protected from §4" + attacker.getName() + "'s §cattacks");
                    utils.sendAction(attacker, "§cYou can't attack §4" + victim.getName() + " §cwhen you are pvp protected");
                } else {
                    cooldowns.put(victim.getUniqueId(), System.currentTimeMillis());
                    cooldowns.put(attacker.getUniqueId(), System.currentTimeMillis());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player victim && e.getDamager() instanceof Player attacker) {

            if (database.getBoolean(victim, "PvP")) {
                e.setCancelled(true);
                utils.sendAction(victim, "§cYou have been protected from §4" + attacker.getName() + "'s §cattacks");
                utils.sendAction(attacker, "§cYou can't attack §4" + victim.getName() + " §cthey are protected");

            } else if (database.getBoolean(attacker, "PvP")) {
                e.setCancelled(true);
                utils.sendAction(victim, "§cYou have been protected from §4" + attacker.getName() + "'s §cattacks");
                utils.sendAction(attacker, "§cYou can't attack §4" + victim.getName() + " §cwhen you are pvp protected");
            } else {
                cooldowns.put(victim.getUniqueId(), System.currentTimeMillis());
                cooldowns.put(attacker.getUniqueId(), System.currentTimeMillis());
            }

        }
    }

}
