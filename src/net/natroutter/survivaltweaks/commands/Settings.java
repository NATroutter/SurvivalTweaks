package net.natroutter.survivaltweaks.commands;

import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.features.Settings.SettingsGUI;
import net.natroutter.survivaltweaks.utilities.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Settings extends Command {

    private static final Lang lang = SurvivalTweaks.getLang();

    public Settings() {
        super("");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§7This command can only be used ingame");
            return false;
        }
        Player p = (Player)sender;

        if (args.length == 0) {

            SettingsGUI.show(p);

        } else {
            p.sendMessage(lang.Prefix + lang.TooManyArguments);
        }
        return false;
    }
}
