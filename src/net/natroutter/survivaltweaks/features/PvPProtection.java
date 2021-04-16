package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.commands.PvP;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvPProtection implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            BasePlayer victim = BasePlayer.from(e.getEntity());
            BasePlayer attacker = BasePlayer.from(e.getDamager());

            if (database.getBoolean(victim, "PvP")) {
                e.setCancelled(true);
                Utils.sendAction(victim, "§cYou have been protected from §4" + attacker.getName() + "'s §cattacks");
                Utils.sendAction(attacker, "§cYou can't attack §4" + victim.getName() + " §cthey are protected");

            } else if (database.getBoolean(attacker, "PvP")) {
                e.setCancelled(true);
                Utils.sendAction(victim, "§cYou have been protected from §4" + attacker.getName() + "'s §cattacks");
                Utils.sendAction(attacker, "§cYou can't attack §4" + victim.getName() + " §cwhen you are pvp protected");
            } else {
                PvP.cooldowns.put(victim.getUniqueId(), System.currentTimeMillis());
                PvP.cooldowns.put(attacker.getUniqueId(), System.currentTimeMillis());
            }

        }
    }

}
