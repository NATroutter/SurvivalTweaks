package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class DamageAlert implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    @EventHandler
    public void PlayerDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player) {
            BasePlayer p = BasePlayer.from(e.getEntity());

            if (database.getBoolean(p.getUniqueId().toString(), "HealthAlert")) {
                if (p.getHealth() <= 10) {

                    if (!e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                        p.sendTitle("", "§c§l❤ LOW HEALTH ❤", 0, 15, 10);
                    }
                }
            }

            if (database.getBoolean(p.getUniqueId().toString(), "ArmorAlert")) {

                for (ItemStack armor : p.getEquipment().getArmorContents()) {
                    if (armor != null || !armor.getType().equals(Material.AIR)) {
                        BaseItem item = BaseItem.from(armor);
                        if (Utils.lowDurability(p,  item)) {
                            p.sendTitle("", "§c§l🛡 LOW ARMOR DURABIITY 🛡", 0, 15, 10);
                            break;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onGlide(PlayerMoveEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        EntityEquipment equip = p.getEquipment();

        if (equip == null) { return; }
        if (equip.getChestplate() == null) {return;}

        if (equip.getChestplate().getType().equals(Material.ELYTRA)) {
            BaseItem elytra = BaseItem.from(equip.getChestplate());
            if (Utils.lowDurability(p, elytra)) {
                p.sendTitle("", "§c§l🛡 LOW ARMOR DURABIITY 🛡", 0, 15, 10);
            }
        }
    }

}
