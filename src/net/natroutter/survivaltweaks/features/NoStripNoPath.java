package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class NoStripNoPath implements Listener {

    private YamlDatabase database;
    private Utils utils;

    public NoStripNoPath(Handler handler) {
        database = handler.getYamlDatabase();
        utils = handler.getUtils();
    }

    public static ArrayList<Material> Pathmats = new ArrayList<>() {{
        add(Material.GRASS_BLOCK);
        add(Material.MYCELIUM);
        add(Material.DIRT);
        add(Material.COARSE_DIRT);
        add(Material.PODZOL);
        add(Material.ROOTED_DIRT);
        add(Material.MYCELIUM);
    }};

    @EventHandler
    public void BlockTransform(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        PlayerInventory inv = e.getPlayer().getInventory();

        if (e.hasBlock() && e.getClickedBlock() != null) {
            Block block = e.getClickedBlock();
            Action act = e.getAction();

            if (act.equals(Action.RIGHT_CLICK_BLOCK)) {
                if (block.getType().name().endsWith("_LOG") || block.getType().name().endsWith("_STEM") || block.getType().name().endsWith("_WOOD")) {

                    if (inv.getItemInMainHand().getType().name().endsWith("_AXE") || inv.getItemInOffHand().getType().name().endsWith("_AXE")) {
                        if (database.getBoolean(p, "StripLog")) {
                            utils.sendAction(p, "§cAction prevented by SurvivalTweaks §4/settings");
                            e.setCancelled(true);
                        }
                    }

                } else if (Pathmats.contains(block.getType())) {

                    if (inv.getItemInMainHand().getType().name().endsWith("_SHOVEL") || inv.getItemInOffHand().getType().name().endsWith("_SHOVEL")) {
                        if (database.getBoolean(p, "GrassPath")) {
                            e.setCancelled(true);
                            utils.sendAction(p, "§cAction prevented by SurvivalTweaks §4/settings");
                        }
                    }

                }
            }
        }


    }



}
