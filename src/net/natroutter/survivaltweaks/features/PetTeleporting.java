package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PetTeleporting implements Listener {

    private Handler handler;
    private YamlDatabase database;

    public PetTeleporting(Handler handler) {
        this.handler = handler;
        database = handler.getYamlDatabase();
    }

    @EventHandler
    public void PetTeleport(EntityTeleportEvent e) {
        Entity ent = e.getEntity();

        if (!(ent instanceof Tameable pet)) {return;}
        if (!(pet.getOwner() instanceof Player owner)) {return;}

        if (database.getBoolean(owner, "PetTP")) {
            e.setCancelled(true);
        }
    }

}
