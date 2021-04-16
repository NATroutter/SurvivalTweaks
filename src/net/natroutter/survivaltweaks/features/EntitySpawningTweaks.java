package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.objects.BaseItem;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawningTweaks implements Listener {


    //Disable bats from spawning
    @EventHandler
    public void EntitySpawn(EntitySpawnEvent e) {
        if (e.getEntityType().equals(EntityType.BAT)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void CreatureSpawn(CreatureSpawnEvent e) {

        SpawnReason reason = e.getSpawnReason();
        LivingEntity lent = e.getEntity();

        //Prevent entities from spawning with helmet
        if (reason.equals(SpawnReason.NATURAL) || reason.equals(SpawnReason.DEFAULT)) {
            //ignore if pillager
            if (lent.getType().equals(EntityType.PILLAGER)) {return;}
            lent.getEquipment().setHelmet(new BaseItem(Material.AIR));

        //Prevent zombie Piglins from spawning in nether portals
        } else if (reason.equals(SpawnReason.NETHER_PORTAL)) {
            if (lent.getType().equals(EntityType.ZOMBIFIED_PIGLIN)) {
                e.setCancelled(true);
            }
        }


    }



}
