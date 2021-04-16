package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class AfkDisplay implements Listener {

    public static HashMap <UUID, Long> lastMoved = new HashMap<>();
    public static HashMap<UUID, Boolean> isAFK = new HashMap<UUID, Boolean>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());

        lastMoved.put(p.getUniqueId(), System.currentTimeMillis());
        if (isAFK.getOrDefault(e.getPlayer().getUniqueId(), false)) {
            Utils.UpdateTabname(p);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        if (!lastMoved.containsKey(p.getUniqueId())) {
            lastMoved.put(p.getUniqueId(), System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        BasePlayer p = BasePlayer.from(e.getPlayer());
        lastMoved.remove(p.getUniqueId());
    }

}
