package net.natroutter.survivaltweaks.features;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.features.Settings.AlertMode;
import net.natroutter.survivaltweaks.utilities.Lang;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class DamageAlert implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();
    private final Lang lang = SurvivalTweaks.getLang();

    @EventHandler
    public void PlayerDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (database.getBoolean(p, "HealthAlert")) {
                if (p.getHealth() <= 10) {

                    if (!e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {

                        AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
                        if (mode == null) {mode = AlertMode.CHAT;}
                        switch (mode) {
                            case CHAT:
                                p.sendMessage(lang.Prefix + "§7Low health!!");
                            case TITLE:
                                p.sendTitle("", "§c§l❤ LOW HEALTH ❤", 0, 15, 10);
                            case ACTION:
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§c§l❤ LOW HEALTH ❤").create());
                        }
                        if (database.getBoolean(p, "UseSound")) {
                            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100 ,1);
                        }

                    }
                }
            }

            if (database.getBoolean(p, "ArmorAlert")) {

                for (ItemStack armor : p.getEquipment().getArmorContents()) {
                    if (armor != null) {
                        if (armor.getType().equals(Material.AIR)) {continue;}

                        BaseItem item = BaseItem.from(armor);
                        if (Utils.lowDurability(p,  item)) {

                            AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
                            if (mode == null) {mode = AlertMode.CHAT;}
                            switch (mode) {
                                case CHAT:
                                    p.sendMessage(lang.Prefix + "§7Low armor durability!!");
                                case TITLE:
                                    p.sendTitle("", "§c§l🛡 LOW ARMOR DURABIITY 🛡", 0, 15, 10);
                                case ACTION:
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§c§l🛡 LOW ARMOR DURABIITY 🛡").create());
                            }
                            if (database.getBoolean(p, "UseSound")) {
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100 ,1);
                            }

                            break;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onGlide(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        EntityEquipment equip = p.getEquipment();

        if (equip == null) { return; }
        if (equip.getChestplate() == null) {return;}

        if (equip.getChestplate().getType().equals(Material.ELYTRA)) {
            BaseItem elytra = BaseItem.from(equip.getChestplate());
            if (Utils.lowDurability(p, elytra)) {

                AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
                if (mode == null) {mode = AlertMode.CHAT;}
                switch (mode) {
                    case CHAT:
                        p.sendMessage(lang.Prefix + "§7Low armor durability!!");
                    case TITLE:
                        p.sendTitle("", "§c§l🛡 LOW ARMOR DURABIITY 🛡", 0, 15, 10);
                    case ACTION:
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§c§l🛡 LOW ARMOR DURABIITY 🛡").create());
                }
                if (database.getBoolean(p, "UseSound")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100 ,1);
                }

            }
        }
    }

}
