package net.natroutter.survivaltweaks.features.settings;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIRow;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.features.scoreboards.ScoreboardHandler;
import net.natroutter.survivaltweaks.utilities.GenericItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SettingsGUI {

    private YamlDatabase database;
    private GenericItems items;
    private ScoreboardHandler scoreboardHandler;

    public SettingsGUI(Handler handler) {
        database = handler.getYamlDatabase();
        items = handler.getGenericItems();
        scoreboardHandler = handler.getScoreboardHandler();
    }

    public void show(Player p) {
        GUI(p).show(p);
    }

    private GUIWindow GUI(Player p) {
        GUIWindow gui = new GUIWindow("§4§lSettings", GUIRow.row6, true);

        gui.setItem(new GUIItem(items.HealthAlert(database.getBoolean(p, "HealthAlert")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            database.save(clicker, "HealthAlert" , !database.getBoolean(p, "HealthAlert"));
            show(clicker);
        }), GUIRow.row2, 1);

        gui.setItem(new GUIItem(items.armorAlert(database.getBoolean(p, "ArmorAlert")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            database.save(clicker, "ArmorAlert" , !database.getBoolean(p, "ArmorAlert"));
            show(clicker);
        }), GUIRow.row2, 2);

        gui.setItem(new GUIItem(items.ToolAlert(database.getBoolean(p, "ToolAlert")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            database.save(clicker, "ToolAlert" , !database.getBoolean(p, "ToolAlert"));
            show(clicker);
        }), GUIRow.row2, 3);

        gui.setItem(new GUIItem(items.PvpTogle(database.getBoolean(p, "PvP")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            database.save(clicker, "PvP" , !database.getBoolean(p, "PvP"));
            show(clicker);
        }), GUIRow.row2, 4);

        gui.setItem(new GUIItem(items.logStrip(database.getBoolean(p, "StripLog")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            database.save(clicker, "StripLog" , !database.getBoolean(p, "StripLog"));
            show(clicker);
        }), GUIRow.row2, 5);

        gui.setItem(new GUIItem(items.grassPath(database.getBoolean(p, "GrassPath")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            database.save(clicker, "GrassPath" , !database.getBoolean(p, "GrassPath"));
            show(clicker);
        }), GUIRow.row2, 6);

        gui.setItem(new GUIItem(items.PetTP(database.getBoolean(p, "PetTP")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            database.save(clicker, "PetTP" , !database.getBoolean(p, "PetTP"));
            show(clicker);
        }), GUIRow.row2, 7);

        gui.setItem(new GUIItem(items.ScoreboardToggle(database.getBoolean(p, "SBVisible")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            boolean newstate = !database.getBoolean(p, "SBVisible");
            if (newstate) {
                clicker.setScoreboard(scoreboardHandler.getCurrent());clicker.sendMessage("active!");
            } else {
                clicker.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            }
            database.save(clicker, "SBVisible" , newstate);
            show(clicker);
        }), GUIRow.row3, 1);


        gui.setItem(new GUIItem(items.AlertMode(AlertMode.fromString(database.getString(p, "AlertMode"))), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
            database.save(clicker, "AlertMode" , mode.next().name());
            show(clicker);
        }), GUIRow.row6, 3);

        gui.setItem(new GUIItem(items.useSound(database.getBoolean(p, "UseSound")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player clicker)) {return;}
            database.save(clicker, "UseSound" , !database.getBoolean(p, "UseSound"));
            show(clicker);
        }), GUIRow.row6, 5);

        return gui;
    }


}
