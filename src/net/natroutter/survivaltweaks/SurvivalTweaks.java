package net.natroutter.survivaltweaks;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.handlers.EventManager;
import net.natroutter.natlibs.handlers.LangManager;
import net.natroutter.survivaltweaks.commands.*;
import net.natroutter.survivaltweaks.features.*;
import net.natroutter.survivaltweaks.features.scoreboards.JoinScoreboard;
import net.natroutter.survivaltweaks.features.scoreboards.ScoreboardHandler;
import net.natroutter.survivaltweaks.utilities.Lang;
import net.natroutter.survivaltweaks.utilities.ScheduledTasks;
import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalTweaks extends JavaPlugin {

    private static YamlDatabase yamlDatabase;
    private static Lang lang;
    private static ScoreboardHandler sbHandler;

    public static YamlDatabase getYamlDatabase() {return yamlDatabase;}
    public static Lang getLang() {return lang;}
    public static ScoreboardHandler getSbHandler(){return sbHandler;}

    @Override
    public void onEnable() {

        yamlDatabase = new YamlDatabase(this);

        sbHandler = new ScoreboardHandler();

        LangManager langManager = new LangManager(this);
        lang = langManager.load(Lang.class).get(Lang.class);

        EventManager em = new EventManager();
        em.RegisterListeners(this,
                NoStripNoPath.class,
                NoCreeperDestruction.class,
                EntitySpawningTweaks.class,
                PetTeleporting.class,
                RewriteableBooks.class,
                DamageAlert.class,
                LevelChangeHandler.class,
                RewriteableBooks.class,
                ToolAlerts.class,
                JoinScoreboard.class,
                AfkDisplay.class,
                TablistNames.class,
                PvPProtection.class
        );

        em.RegisterCommands(this,
                ArmorAlert.class,
                Grasspath.class,
                HealthAlert.class,
                Pettp.class,
                Striplog.class,
                ToolAlert.class,
                PvP.class
        );

        ScheduledTasks st = new ScheduledTasks(this, sbHandler);
    }


    @Override
    public void onDisable() {
        super.onDisable();
    }
}
