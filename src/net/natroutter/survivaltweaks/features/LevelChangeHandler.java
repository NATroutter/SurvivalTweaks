package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.database.YamlDatabase;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class LevelChangeHandler implements Listener {

    private YamlDatabase database;

    public LevelChangeHandler(Handler handler) {
        database = handler.getYamlDatabase();
    }

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        database.save(e.getPlayer(), "level", e.getNewLevel());
    }

}
