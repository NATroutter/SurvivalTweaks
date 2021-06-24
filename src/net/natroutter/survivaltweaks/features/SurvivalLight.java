package net.natroutter.survivaltweaks.features;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SurvivalLight implements Listener {

    @EventHandler
    public void onLightBreak(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if (!e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {return;}
        if (!e.hasBlock()) {return;}
        Block block = e.getClickedBlock();

        if (!block.getType().equals(Material.LIGHT)) {return;}
        if (!p.getInventory().getItemInMainHand().getType().equals(Material.LIGHT)) {return;}

        if (p.isSneaking()) {
            block.setType(Material.AIR);
            p.getWorld().dropItem(block.getLocation().add(0.5, 0.5, 0.5), new ItemStack(Material.LIGHT));
        }
    }

}
