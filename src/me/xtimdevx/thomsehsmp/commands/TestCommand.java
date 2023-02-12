package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.crates.CratesEvents;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    CratesEvents events = new CratesEvents();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if(!player.hasPermission("smp.command.test")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("§cUsage: /test <subject>");
            player.sendMessage("§cSubjects: TEST, SAVEINV");
            return true;
        }

        if(args[0].equalsIgnoreCase("test")) {
            events.createHelix(player);
            player.sendMessage("success");
        }

        if(args[0].equalsIgnoreCase("saveinv")) {
            Utils.saveInventory(player);
            player.sendMessage("saved inv");
        }

        if(args[0].equalsIgnoreCase("loadinv")) {
            Utils.restoreInventory(player);
            player.sendMessage("loaded inv");
        }

        return true;
    }
}
