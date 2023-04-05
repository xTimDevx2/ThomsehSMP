package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MaintenanceCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (!player.hasPermission("smp.commands.maintenance")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§cUsage: /maintenance <enable/disable>");
            return true;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            if (Settings.getInstance().getData().getBoolean("maintenance")) {
                player.sendMessage("§cMaintenance mode is already enabled.");
                return true;
            }

            Settings.getInstance().getData().set("maintenance", true);
            Settings.getInstance().saveData();
        }

        if (args[0].equalsIgnoreCase("disable")) {
            if (!Settings.getInstance().getData().getBoolean("maintenance")) {
                player.sendMessage("§cMaintenance mode is not enabled right now.");
                return true;
            }

            Settings.getInstance().getData().set("maintenance", false);
            Settings.getInstance().saveData();
        }

        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (sender.hasPermission("smp.commands.maintenance")) {
            commands.add("enable");
            commands.add("disable");
        }
        StringUtil.copyPartialMatches(args[0], commands, completions);
        Collections.sort(completions);
        return completions;
    }
}
