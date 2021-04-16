package net.natroutter.survivaltweaks.commands;

import net.natroutter.natlibs.handlers.Database.YamlDatabase;
import net.natroutter.natlibs.objects.BasePlayer;
import net.natroutter.survivaltweaks.SurvivalTweaks;
import net.natroutter.survivaltweaks.utilities.Lang;
import net.natroutter.survivaltweaks.utilities.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PvP extends Command {

    private final Lang lang = SurvivalTweaks.getLang();
    private final YamlDatabase database = SurvivalTweaks.getYamlDatabase();

    int cooldown = 60 * 5;
    public static HashMap<UUID, Long> cooldowns = new HashMap<>();

    public PvP() {
        super("");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(lang.Prefix + lang.OnlyIngame);
            return false;
        }
        BasePlayer p = BasePlayer.from(sender);

        if (cooldowns.containsKey(p.getUniqueId())) {
            long seconds = ((cooldowns.get(p.getUniqueId())/1000)+cooldown) - (System.currentTimeMillis()/1000);
            if (seconds > 0) {
                p.sendMessage(lang.Prefix + "Command cooldown: " + Utils.timeLeft(seconds));
                return false;
            }
        }
        cooldowns.put(p.getUniqueId(), System.currentTimeMillis());

        if (args.length == 0) {

            boolean state = database.getBoolean(p, "PvP");
            if (state) {
                database.save(p, "PvP", false);
                p.sendMessage(lang.Prefix + "PvP Protection: §cDisabled");
            } else {
                database.save(p, "PvP", true);
                p.sendMessage(lang.Prefix + "PvP Protection: §cEnabled");
            }

        } else {
            p.sendMessage(lang.Prefix + lang.TooManyArguments);
        }

        return false;
    }
}