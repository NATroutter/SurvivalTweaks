package net.natroutter.survivaltweaks.utilities;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.features.AfkDisplay;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class Utils {

    private static final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    public static boolean lowDurability(BasePlayer p, BaseItem armor) {
        double MaxDur = (double) armor.getType().getMaxDurability();
        double CurrentDur = (double) MaxDur - armor.getDurability();
        double CalcResult = MaxDur * 0.10;

        return CurrentDur < CalcResult;
    }

    public static void UpdateTabname(BasePlayer p, boolean isAFK) {
        if (isAFK) {
            p.setPlayerListName("§8[AFK] §7" + p.getDisplayName());
        } else {
            p.setPlayerListName("§7" + p.getDisplayName());
        }
    }

    public static void sendAction(BasePlayer p, String msg) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
    }

    public static String timeLeft(long timeoutSeconds) {
        int days = (int) (timeoutSeconds / 86400);
        int hours = (int) (timeoutSeconds / 3600) % 24;
        int minutes = (int) (timeoutSeconds / 60) % 60;
        long seconds = timeoutSeconds % 60;

        String left;
        if (days < 1) {
            if (hours < 1) {
                if (minutes < 1) {
                    left = "§c" + seconds + " §7seconds§c";
                } else {
                    left = "§c" + minutes + " §7minutes§c " + seconds + " §7seconds§c";
                }
            } else {
                left = "§c" + hours + " §7hours§c " + minutes + " §7minutes§c " + seconds + " §7seconds§c";
            }
        } else {
            left = "§c" + days + " §7days§c " + hours + " §7hours§c " + minutes + " §7minutes§c " + seconds + " §7seconds§c";
        }
        return left;

    }

    public static void calculateRichest() {

        for (Player p : Bukkit.getOnlinePlayers()) {
            int riches = 0;

            for (ItemStack item : p.getEnderChest().getContents()) {
                if (item == null || item.getType().equals(Material.AIR)) {
                    continue;
                }

                if (item.getType().equals(Material.DIAMOND)) {
                    riches = riches + item.getAmount();

                } else if (item.getType().equals(Material.DIAMOND_BLOCK)) {
                    riches = riches + item.getAmount() * 9;

                } else if (item.getType().name().endsWith("_SHULKER_BOX")) {
                    if (item.getItemMeta() instanceof BlockStateMeta) {
                        BlockStateMeta meta = (BlockStateMeta)item.getItemMeta();
                        if (meta.getBlockState() instanceof ShulkerBox) {
                            ShulkerBox box = (ShulkerBox)meta.getBlockState();
                            for (ItemStack boxItem : box.getInventory()) {
                                if (boxItem == null || boxItem.getType().equals(Material.AIR)) {
                                    continue;
                                }
                                if (boxItem.getType().equals(Material.DIAMOND)) {
                                    riches = riches + boxItem.getAmount();

                                } else if (boxItem.getType().equals(Material.DIAMOND_BLOCK)) {
                                    riches = riches + boxItem.getAmount() * 9;

                                }
                            }
                        }
                    }
                }
            }
            database.save(p, "Riches", riches);
        }
    }

}
