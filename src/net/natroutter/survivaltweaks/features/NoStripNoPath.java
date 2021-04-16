package net.natroutter.survivaltweaks.features;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NoStripNoPath implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    @EventHandler
    public void BlockTransform(PlayerInteractEvent e) {

        BasePlayer p = BasePlayer.from(e.getPlayer());
        String uuid = p.getUniqueId().toString();

        if (e.hasBlock() && e.getClickedBlock() != null) {
            Block block = e.getClickedBlock();
            Action act = e.getAction();

            if (act.equals(Action.RIGHT_CLICK_BLOCK)) {
                if (block.getType().name().endsWith("_LOG") || block.getType().name().endsWith("_STEM")) {

                    if (p.getItemInHand() == null || p.getItemInHand().getType().equals(Material.AIR)) {
                        return;
                    }

                    if (p.getItemInHand().getType().name().endsWith("_AXE")) {

                        if (!database.getBoolean(uuid, "StripLog")) {
                            Utils.sendAction(p, "§cAction prevented by SurvivalTweaks §4/striplog");
                            e.setCancelled(true);
                        }
                    }
                } else if (block.getType().equals(Material.GRASS_BLOCK)) {

                    if (p.getItemInHand() == null || p.getItemInHand().getType().equals(Material.AIR)) {
                        return;
                    }

                    if (p.getItemInHand().getType().name().endsWith("_SHOVEL")) {

                        if (!database.getBoolean(uuid, "GrassPath")) {
                            e.setCancelled(true);
                            Utils.sendAction(p, "§cAction prevented by SurvivalTweaks §4/striplog");
                        }
                    }
                }
            }
        }


    }



}
