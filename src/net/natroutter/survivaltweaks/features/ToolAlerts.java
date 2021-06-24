package net.natroutter.survivaltweaks.features;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.features.Settings.AlertMode;
import net.natroutter.survivaltweaks.utilities.Lang;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class ToolAlerts implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();
    private final Lang lang = SurvivalTweaks.getLang();

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

    public void alert(Player p, BaseItem tool) {
        double MaxDur = (double) tool.getType().getMaxDurability();
        double CurrentDur = (double) MaxDur - tool.getDurability();
        double CalcResult = MaxDur * 0.10;

        if (CurrentDur < CalcResult) {

            AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
            if (mode == null) {mode = AlertMode.CHAT;}
            switch (mode) {
                case CHAT:
                    p.sendMessage(lang.Prefix + "§7Low duravility!!");
                case TITLE:
                    p.sendTitle("", "§c§l⚒ LOW DURABILITY ⚒", 0, 15, 10);
                case ACTION:
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§c§l⚒ LOW DURABILITY ⚒").create());
            }
            if (database.getBoolean(p, "UseSound")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100 ,1);
            }

        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

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
            Player p = e.getPlayer();
            BaseItem item = BaseItem.from(e.getItem());
            String itemName = item.getType().name();
            Block block = e.getClickedBlock();
            String blockName = block.getType().name();

            if (itemName.endsWith("_SHOVEL")) {
                if (NoStripNoPath.Pathmats.contains(block.getType())) {
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
