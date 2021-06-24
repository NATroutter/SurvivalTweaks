package net.natroutter.survivaltweaks.features;

import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class AfkDisplay implements Listener {

    public static HashMap <UUID, Long> lastMoved = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        long lastmoved_sec = ((System.currentTimeMillis() - lastMoved.getOrDefault(p.getUniqueId(), 0L)) / 1000);
        if (lastmoved_sec >= 5) {
            Utils.updateTabname(p, false);
        }

        lastMoved.put(p.getUniqueId(), System.currentTimeMillis());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!lastMoved.containsKey(p.getUniqueId())) {
            lastMoved.put(p.getUniqueId(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        lastMoved.remove(p.getUniqueId());
    }

}
