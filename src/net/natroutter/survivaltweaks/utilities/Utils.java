package net.natroutter.survivaltweaks.utilities;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.survivaltweaks.Handler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class Utils {

    private final YamlDatabase database;

    public Utils(Handler handler) {
        database = handler.getYamlDatabase();
    }

    public String locKey(Location loc) {
        return loc.getWorld().getName()+"|"+loc.getBlockX()+"|"+loc.getBlockY()+"|"+loc.getBlockZ();
    }

    public String status(boolean status) {
        return status ? "§cEnabled" : "§cDisabled";
    }

    public boolean lowDurability(Player p, BaseItem armor) {
        double MaxDur = (double) armor.getType().getMaxDurability();
        double CurrentDur = (double) MaxDur - armor.getDurability();
        double CalcResult = MaxDur * 0.10;

        return CurrentDur < CalcResult;
    }

    public void updateTabname(Player p, boolean isAFK) {
        if (isAFK) {
            p.setPlayerListName("§8[AFK] §7" + p.getDisplayName());
        } else {
            p.setPlayerListName("§7" + p.getDisplayName());
        }
    }

    public void sendAction(Player p, String msg) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
    }

    public String timeLeft(long timeoutSeconds) {
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

    public void calculateRichest() {

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

                } else if (item.getType().name().endsWith("SHULKER_BOX")) {
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
