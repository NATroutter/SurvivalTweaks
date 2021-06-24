package net.natroutter.survivaltweaks.utilities;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.survivaltweaks.features.Settings.AlertMode;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class Items {



    public static BaseItem armorAlert(boolean active) {
        BaseItem item = new BaseItem(Material.LEATHER_CHESTPLATE);
        item.setDisplayName("§4§lArmor alert");
        item.setLore(
                "§7You get alert when your",
                "§7armor durability is low",
                " ",
                "§7Status: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        if (active) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        return item;
    }

    public static BaseItem grassPath(boolean active) {
        BaseItem item = new BaseItem(Material.DIRT_PATH);
        item.setDisplayName("§4§lGrass path");
        item.setLore(
                "§7Prevents you from accidentally",
                "§7turning grass blocks to path blocks",
                " ",
                "§7Status: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        if (active) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        return item;
    }

    public static BaseItem logStrip(boolean active) {
        BaseItem item = new BaseItem(Material.STRIPPED_OAK_LOG);
        item.setDisplayName("§4§lLog Stripping");
        item.setLore(
                "§7Prevents you from accidentally",
                "§7stripping your logs",
                " ",
                "§7Status: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        if (active) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        return item;
    }

    public static BaseItem PvpTogle(boolean active) {
        BaseItem item = new BaseItem(Material.WOODEN_SWORD);
        item.setDisplayName("§4§lPvP Toggle");
        item.setLore(
                "§7Toggle pvp",
                " ",
                "§7Status: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        if (active) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        return item;
    }

    public static BaseItem ToolAlert(boolean active) {
        BaseItem item = new BaseItem(Material.WOODEN_PICKAXE);
        item.setDisplayName("§4§lTool alert");
        item.setLore(
                "§7You get alert when your",
                "§7tool durability is low",
                " ",
                "§7Status: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        if (active) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        return item;
    }

    public static BaseItem PetTP(boolean active) {
        BaseItem item = new BaseItem(Material.LEAD);
        item.setDisplayName("§4§lPet Teleporting");
        item.setLore(
                "§7You can prevent your pets",
                "§7teleporting to you",
                " ",
                "§7Status: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        if (active) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        return item;
    }

    public static BaseItem HealthAlert(boolean active) {
        BaseItem item = new BaseItem(Material.POTION);

        PotionMeta meta = (PotionMeta)item.getItemMeta();
        meta.setColor(Color.fromRGB(255, 0, 238));
        item.setItemMeta(meta);

        item.setDisplayName("§4§lHealth alert");
        item.setLore(
                "§7You get alert when your",
                "§7Health is low",
                " ",
                "§7Status: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        if (active) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        return item;
    }

    public static BaseItem ScoreboardToggle(boolean active) {
        BaseItem item = new BaseItem(Material.PAPER);
        item.setDisplayName("§4§lScoreboard");
        item.setLore(
                "§7Toggle scoreboard visibility",
                " ",
                "§7Status: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        if (active) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        return item;
    }

    public static BaseItem AlertMode(AlertMode mode) {
        BaseItem item = new BaseItem(Material.SLIME_BALL);
        item.setDisplayName("§4§lAlert mode");
        item.setLore(
                "§7Change how you want to receive alerts",
                " ",
                "§7Mode: " + mode.lang()
        );
        item.addItemFlags(ItemFlag.values());
        return item;
    }

    public static BaseItem useSound(boolean active) {
        BaseItem item = new BaseItem(Material.NOTE_BLOCK);
        item.setDisplayName("§4§lUse alertsound");
        item.setLore(
                "§7Change do you want hear sound",
                "§7when you get alert",
                " ",
                "§7Mode: " + Utils.status(active)
        );
        item.addItemFlags(ItemFlag.values());
        return item;
    }



}
