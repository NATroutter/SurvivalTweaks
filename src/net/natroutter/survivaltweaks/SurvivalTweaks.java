package net.natroutter.survivaltweaks;

import net.natroutter.survivaltweaks.commands.*;
import net.natroutter.survivaltweaks.features.*;
import net.natroutter.survivaltweaks.features.afkdisplay.AfkListener;
import net.natroutter.survivaltweaks.features.scoreboards.JoinScoreboard;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalTweaks extends JavaPlugin {

    @Override
    public void onEnable() {

        CommandMap map = getServer().getCommandMap();
        PluginManager pm = getServer().getPluginManager();

        Handler handler = new Handler(this);

        //Register commands
        map.register("SurvivalTweaks", new Settings(handler));

        //Register listener
        pm.registerEvents(new NoStripNoPath(handler), this);
        pm.registerEvents(new NoCreeperDestruction(), this);
        pm.registerEvents(new EntitySpawningTweaks(), this);
        pm.registerEvents(new PetTeleporting(handler), this);
        pm.registerEvents(new RewriteableBooks(handler), this);
        pm.registerEvents(new DamageAlert(handler), this);
        pm.registerEvents(new LevelChangeHandler(handler), this);
        pm.registerEvents(new ToolAlerts(handler), this);
        pm.registerEvents(new ToolAlerts(handler), this);
        pm.registerEvents(new JoinScoreboard(handler), this);
        pm.registerEvents(new AfkListener(handler), this);
        pm.registerEvents(new TablistNames(), this);
        pm.registerEvents(new PvPProtection(handler), this);
        pm.registerEvents(new SurvivalLight(), this);
    }


    @Override
    public void onDisable() {
        super.onDisable();
    }
}
