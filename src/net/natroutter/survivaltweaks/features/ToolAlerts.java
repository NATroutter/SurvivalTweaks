package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ToolAlerts implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    ArrayList<String> toolIdentiers = new ArrayList<String>() {{
        add("shovel");
        add("pickaxe");
        add("axe");
        add("hoe");
        add("sword");
    }};

    public boolean validTool(BaseItem tool) {
        String ToolName = tool.getType().toString().toLowerCase();
        for (String toolID : toolIdentiers) {
            if (ToolName.contains(toolID.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void alert(BasePlayer p, BaseItem tool) {
        double MaxDur = (double) tool.getType().getMaxDurability();
        double CurrentDur = (double) MaxDur - tool.getDurability();
        double CalcResult = MaxDur * 0.10;

        if (CurrentDur < CalcResult) {
            p.sendTitle("", "§c§l⚒ LOW DURABILITY ⚒", 0, 15, 10);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());

        if (database.getBoolean(p, "ToolAlert")) {
            BaseItem mainHand = BaseItem.from(p.getInventory().getItemInMainHand());
            BaseItem offHand = BaseItem.from(p.getInventory().getItemInOffHand());

            if (validTool(mainHand)) {
                alert(p, mainHand);

            } else if (validTool(offHand)) {
                alert(p, offHand);

            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        if (e.hasBlock() && e.hasItem() && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            BasePlayer p = BasePlayer.from(e.getPlayer());
            BaseItem item = BaseItem.from(e.getItem());
            String itemName = item.getType().name();
            Block block = e.getClickedBlock();
            String blockName = block.getType().name();

            if (itemName.endsWith("_SHOVEL")) {
                if (block.getType().equals(Material.GRASS_BLOCK)) {
                    if (database.getBoolean(p, "GrassPath")) {
                        alert(p, item);
                    }
                }
            } else if (itemName.endsWith("_AXE")) {
                if (blockName.endsWith("_LOG") || blockName.endsWith("_STEM")) {
                    if (database.getBoolean(e.getPlayer(), "StripLog")) {
                        alert(p, item);
                    }
                }
            }
        }

    }



}
