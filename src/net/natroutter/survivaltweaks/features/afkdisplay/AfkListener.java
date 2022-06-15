package net.natroutter.survivaltweaks.features.afkdisplay;

import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class AfkListener implements Listener {

    private Utils utils;
    private AfkHandler afkHandler;

    public AfkListener(Handler handler) {
        utils = handler.getUtils();
        this.afkHandler = handler.getAfkHandler();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        afkHandler.lastMoved.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        long lastmoved_sec = ((System.currentTimeMillis() - afkHandler.lastMoved.getOrDefault(p.getUniqueId(), 0L)) / 1000);
        if (lastmoved_sec >= 5) {
            utils.updateTabname(p, false);
        }

        afkHandler.lastMoved.put(p.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!afkHandler.lastMoved.containsKey(p.getUniqueId())) {
            afkHandler.lastMoved.put(p.getUniqueId(), System.currentTimeMillis());
        }
    }

}
