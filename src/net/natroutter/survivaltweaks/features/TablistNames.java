package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.objects.BasePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TablistNames implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());

        p.setPlayerListName("§7" + p.getDisplayName());

    }


}
