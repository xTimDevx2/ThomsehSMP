package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("invsee")) {
            Player player = (Player) sender;

            if (!player.hasPermission("smp.command.invsee")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if (args.length == 0) {
                player.sendMessage("§cUsage: /invsee <player>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                player.sendMessage("§cError: This player is not online.");
                return true;
            }

            player.openInventory(target.getInventory());
            player.sendMessage(MessageUtils.GARY + "You are now manipulating the inventory of §c" + target.getName() + "§f.");
        }
        return true;
    }

}
