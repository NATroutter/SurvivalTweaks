package net.natroutter.survivaltweaks.features.Settings;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.handlers.gui.GUIItem;
import net.natroutter.natlibs.handlers.gui.GUIWindow;
import net.natroutter.natlibs.objects.BaseItem;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Items;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class SettingsGUI {

    private static final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    public static HashMap<UUID, GUIWindow> GUIS = new HashMap<>();


    private static GUIWindow getGUI(Player p) {
        if (!GUIS.containsKey(p.getUniqueId())) {
            GUIS.put(p.getUniqueId(), new GUIWindow("§4§lSettings", GUIWindow.Rows.row6, true));
        }
        return GUIS.get(p.getUniqueId());
    }

    public static void show(Player p) {
        GUI(p).show(p);
    }

    private static GUIWindow GUI(Player p) {
        GUIWindow gui = getGUI(p);

        gui.setItem(new GUIItem(Items.HealthAlert(database.getBoolean(p, "HealthAlert")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            database.save(clicker, "HealthAlert" , !database.getBoolean(p, "HealthAlert"));
            show(clicker);
        }), GUIWindow.Rows.row2, 1);

        gui.setItem(new GUIItem(Items.armorAlert(database.getBoolean(p, "ArmorAlert")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            database.save(clicker, "ArmorAlert" , !database.getBoolean(p, "ArmorAlert"));
            show(clicker);
        }), GUIWindow.Rows.row2, 2);

        gui.setItem(new GUIItem(Items.ToolAlert(database.getBoolean(p, "ToolAlert")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            database.save(clicker, "ToolAlert" , !database.getBoolean(p, "ToolAlert"));
            show(clicker);
        }), GUIWindow.Rows.row2, 3);

        gui.setItem(new GUIItem(Items.PvpTogle(database.getBoolean(p, "PvP")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            database.save(clicker, "PvP" , !database.getBoolean(p, "PvP"));
            show(clicker);
        }), GUIWindow.Rows.row2, 4);

        gui.setItem(new GUIItem(Items.logStrip(database.getBoolean(p, "StripLog")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            database.save(clicker, "StripLog" , !database.getBoolean(p, "StripLog"));
            show(clicker);
        }), GUIWindow.Rows.row2, 5);

        gui.setItem(new GUIItem(Items.grassPath(database.getBoolean(p, "GrassPath")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            database.save(clicker, "GrassPath" , !database.getBoolean(p, "GrassPath"));
            show(clicker);
        }), GUIWindow.Rows.row2, 6);

        gui.setItem(new GUIItem(Items.PetTP(database.getBoolean(p, "PetTP")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            database.save(clicker, "PetTP" , !database.getBoolean(p, "PetTP"));
            show(clicker);
        }), GUIWindow.Rows.row2, 7);

        gui.setItem(new GUIItem(Items.ScoreboardToggle(database.getBoolean(p, "SBVisible")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            boolean newstate = !database.getBoolean(p, "SBVisible");
            if (!newstate) {clicker.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());}
            database.save(clicker, "SBVisible" , newstate);
            show(clicker);
        }), GUIWindow.Rows.row3, 1);


        gui.setItem(new GUIItem(Items.AlertMode(AlertMode.fromString(database.getString(p, "AlertMode"))), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            AlertMode mode = AlertMode.fromString(database.getString(p, "AlertMode"));
            database.save(clicker, "AlertMode" , mode.next().name());
            show(clicker);
        }), GUIWindow.Rows.row6, 3);

        gui.setItem(new GUIItem(Items.useSound(database.getBoolean(p, "UseSound")), (e) -> {
            if (!(e.getWhoClicked() instanceof Player)) {return;}
            Player clicker = (Player)e.getWhoClicked();
            database.save(clicker, "UseSound" , !database.getBoolean(p, "UseSound"));
            show(clicker);
        }), GUIWindow.Rows.row6, 5);

        return gui;
    }


}
