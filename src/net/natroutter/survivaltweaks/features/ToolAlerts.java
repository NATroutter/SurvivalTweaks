package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
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

        if (database.getBoolean(p.getUniqueId().toString(), "ToolAlert")) {
            BaseItem mainHand = BaseItem.from(p.getInventory().getItemInMainHand());
            BaseItem offHand = BaseItem.from(p.getInventory().getItemInOffHand());

            if (validTool(mainHand)) {
                alert(p, mainHand);

            } else if (validTool(offHand)) {
                alert(p, offHand);

            }

        }
    }



}
