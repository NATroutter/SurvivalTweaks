package net.natroutter.survivaltweaks.commands;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToolAlert extends Command {

    private final Lang lang = SurvivalTweaks.getLang();
    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    public ToolAlert() {
        super("");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(lang.Prefix + lang.OnlyIngame);
            return false;
        }
        BasePlayer p = BasePlayer.from(sender);

        if (args.length == 0) {

            boolean state = database.getBoolean(p, "ToolAlert");
            if (state) {
                database.save(p, "ToolAlert", false);
                p.sendMessage(lang.Prefix + "ToolAlert: §cDisabled");
            } else {
                database.save(p, "ToolAlert", true);
                p.sendMessage(lang.Prefix + "ToolAlert: §cEnabled");
            }

        } else {
            p.sendMessage(lang.Prefix + lang.TooManyArguments);
        }

        return false;
    }
}
