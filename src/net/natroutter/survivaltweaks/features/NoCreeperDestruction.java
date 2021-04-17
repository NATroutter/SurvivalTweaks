package net.natroutter.survivaltweaks.features;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NoCreeperDestruction implements Listener {


    @EventHandler
    public void EntityExplode(EntityExplodeEvent e) {

        if (e.getEntityType().equals(EntityType.CREEPER)) {
            e.blockList().clear();
        }
    }



}
