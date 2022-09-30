package net.natroutter.survivaltweaks.features;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import net.natroutter.natlibs.handlers.database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.features.settings.AlertMode;
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

import java.time.Duration;

public class DamageAlert implements Listener {

    private final YamlDatabase database;
    private final Lang lang;
    private final Utils utils;

    public DamageAlert(Handler handler) {
        database = handler.getYamlDatabase();
        lang = handler.getLang();
        utils = handler.getUtils();
    }

    @EventHandler
    public void PlayerDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player p) {

            if (database.getBoolean(p, "HealthAlert")) {
                if (p.getHealth() <= 10) {

                    if (!e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {

                        AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
                        if (mode == null) {mode = AlertMode.CHAT;}
                        switch (mode) {
                            case CHAT -> p.sendMessage(lang.Prefix + "§7Low health!!");
                            case TITLE -> {
                                Title.Times times = Title.Times.of(Duration.ZERO, Duration.ofMillis(1000), Duration.ofMillis(500));
                                Component title = Component.text("");
                                Component subTitle = LegacyComponentSerializer.legacyAmpersand().deserialize("&c&l❤ LOW HEALTH ❤");
                                p.showTitle(Title.title(title, subTitle, times));
                            }
                            case ACTION -> p.sendActionBar(LegacyComponentSerializer.legacyAmpersand().deserialize("&c&l❤ LOW HEALTH ❤"));
                        }
                        if (database.getBoolean(p, "UseSound")) {
                            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100 ,1);
                        }

                    }
                }
            }

            if (database.getBoolean(p, "ArmorAlert")) {

                for (ItemStack armor : p.getEquipment().getArmorContents()) {
                    if (armor == null) {continue;}
                    if (armor.getType().equals(Material.AIR)) {continue;}

                    BaseItem item = BaseItem.from(armor);
                    if (utils.lowDurability(p,  item)) {

                        AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
                        if (mode == null) {mode = AlertMode.CHAT;}p.sendMessage("Mode: " + mode);
                        switch (mode) {
                            case CHAT -> p.sendMessage(lang.Prefix + "§7Low armor durability!!");
                            case TITLE -> {
                                Title.Times times = Title.Times.of(Duration.ZERO, Duration.ofMillis(1000), Duration.ofMillis(500));
                                Component title = Component.text("");
                                Component subTitle = LegacyComponentSerializer.legacyAmpersand().deserialize("&c&l🛡 LOW ARMOR DURABIITY 🛡");
                                p.showTitle(Title.title(title, subTitle, times));
                            }
                            case ACTION -> p.sendActionBar(LegacyComponentSerializer.legacyAmpersand().deserialize("&c&l🛡 LOW ARMOR DURABIITY 🛡"));
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

    @EventHandler
    public void onGlide(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        EntityEquipment equip = p.getEquipment();

        if (equip.getChestplate() == null) {return;}

        if (equip.getChestplate().getType().equals(Material.ELYTRA)) {
            BaseItem elytra = BaseItem.from(equip.getChestplate());
            if (utils.lowDurability(p, elytra)) {

                AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
                if (mode == null) {mode = AlertMode.CHAT;}
                switch (mode) {
                    case CHAT -> p.sendMessage(lang.Prefix + "§7Low armor durability!!");
                    case TITLE -> {
                        Title.Times times = Title.Times.of(Duration.ZERO, Duration.ofMillis(1000), Duration.ofMillis(500));
                        Component title = Component.text("");
                        Component subTitle = LegacyComponentSerializer.legacyAmpersand().deserialize("&c&l🛡 LOW ARMOR DURABIITY 🛡");
                        p.showTitle(Title.title(title, subTitle, times));
                    }
                    case ACTION -> p.sendActionBar(LegacyComponentSerializer.legacyAmpersand().deserialize("&c&l🛡 LOW ARMOR DURABIITY 🛡"));
                }
                if (database.getBoolean(p, "UseSound")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100 ,1);
                }

            }
        }
    }

}
