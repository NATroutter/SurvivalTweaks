package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
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
                Utils.sendAction(victim, "§7You have been protected from §c" + attacker.getName() + "'s §7attacks");
                Utils.sendAction(attacker, "§7You can't attack §c" + victim.getName() + " §7they are protected §c/pvp");
            }

        }

    }

}
