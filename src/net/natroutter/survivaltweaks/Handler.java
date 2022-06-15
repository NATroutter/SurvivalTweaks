package net.natroutter.survivaltweaks;

import lombok.Getter;
import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.handlers.ParticleSpawner;
import net.natroutter.natlibs.utilities.Utilities;
import net.natroutter.survivaltweaks.features.afkdisplay.AfkHandler;
import net.natroutter.survivaltweaks.features.settings.SettingsGUI;
import net.natroutter.survivaltweaks.features.recipies.RecipeHandler;
import net.natroutter.survivaltweaks.features.scoreboards.ScoreboardHandler;
import net.natroutter.survivaltweaks.utilities.GenericItems;
import net.natroutter.survivaltweaks.utilities.Lang;
import net.natroutter.survivaltweaks.utilities.ScheduledTasks;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

@Getter
public class Handler {



    private SurvivalTweaks instance;
    private YamlDatabase yamlDatabase;
    private ScoreboardHandler scoreboardHandler;
    private Lang lang;
    private Utilities utilities;
    private ScheduledTasks scheduledTasks;
    private RecipeHandler recipeHandler;
    private Utils utils;
    private GenericItems genericItems;
    private SettingsGUI settingsGUI;
    private AfkHandler afkHandler;
    private ParticleSpawner particleSpawner;

    public Handler(SurvivalTweaks instance) {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        PluginDescriptionFile pdf = instance.getDescription();
        console.sendMessage(" ");
        console.sendMessage("§4  ___              _          _             ");
        console.sendMessage("§4 / __|_  _ _ ___ _(_)_ ____ _| |            ");
        console.sendMessage("§4 \\__ \\ || | '_\\ V / \\ V / _` | |            ");
        console.sendMessage("§4 |___/\\_,_|_|__\\_/|_|\\_/\\__,_|_|   _        ");
        console.sendMessage("§4            |_   _|_ __ _____ __ _| |__ ___ ");
        console.sendMessage("§4              | | \\ V  V / -_) _` | / /(_-< ");
        console.sendMessage("§4              |_|  \\_/\\_/\\___\\__,_|_\\_\\/__/ ");
        console.sendMessage(" ");
        console.sendMessage("        §cSurvivalTweaks §4" + pdf.getVersion() + " §cLoaded");
        console.sendMessage("       §cDeveloped by §4NATroutter §cwith §4♥");
        console.sendMessage(" ");

        this.instance = instance;

        this.lang = new Lang();

        this.utilities = new Utilities(instance);
        this.yamlDatabase = new YamlDatabase(instance);

        this.particleSpawner = new ParticleSpawner();

        this.utils = new Utils(this);
        this.genericItems = new GenericItems(this);

        this.scoreboardHandler = new ScoreboardHandler(this);
        this.scheduledTasks = new ScheduledTasks(this);

        this.recipeHandler = new RecipeHandler(this);
        this.settingsGUI = new SettingsGUI(this);

        this.afkHandler = new AfkHandler();


    }

}
