package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BookMeta;

public class RewriteableBooks implements Listener {

    @EventHandler
    public void PlayerInteractWithBook(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.hasItem()) {
            BaseItem item = BaseItem.from(e.getItem());

            if (p.isSneaking()) {
                if (item.getType().equals(Material.WRITTEN_BOOK)) {
                    BookMeta book = (BookMeta)item.getItemMeta();

                    if (book == null) {return;}
                    if (book.getAuthor() == null) {return;}

                    if (book.getAuthor().equals(p.getDisplayName())) {
                        item.setType(Material.WRITABLE_BOOK);
                        Utils.sendAction(p, "§cBook is now writeable!");
                    }

                }
            }
        }
    }

}
