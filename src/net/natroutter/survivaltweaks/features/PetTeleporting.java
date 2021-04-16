package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;

public class PetTeleporting implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    @EventHandler
    public void PetTeleport(EntityTeleportEvent e) {
        Entity ent = e.getEntity();

        if (ent instanceof Tameable) {
            Tameable pet = (Tameable)ent;

            if (pet.getOwner() instanceof Player) {
                BasePlayer owner = BasePlayer.from((Player)pet.getOwner());
                String ownerUUID = owner.getUniqueId().toString();

                if (!database.getBoolean(ownerUUID, "PetTP")) {
                    e.setCancelled(true);
                }

            }
        }
    }

}
