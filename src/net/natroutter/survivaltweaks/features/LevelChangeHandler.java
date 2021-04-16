package net.natroutter.survivaltweaks.features;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class LevelChangeHandler implements Listener {

    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        database.save(e.getPlayer(), "level", e.getNewLevel());
    }

}
