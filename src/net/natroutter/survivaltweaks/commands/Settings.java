package net.natroutter.survivaltweaks.commands;

import net.natroutter.survivaltweaks.Handler;
import net.natroutter.survivaltweaks.features.settings.SettingsGUI;
import net.natroutter.survivaltweaks.utilities.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Settings extends Command {

    private Lang lang;
    private SettingsGUI settingsGUI;

    public Settings(Handler handler) {
        super("Settings");
        lang = handler.getLang();
        settingsGUI = handler.getSettingsGUI();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(lang.Prefix + lang.OnlyIngame);
            return false;
        }

        if (args.length == 0) {

            settingsGUI.show(p);

        } else {
            p.sendMessage(lang.Prefix + lang.TooManyArguments);
        }
        return false;
    }
}
