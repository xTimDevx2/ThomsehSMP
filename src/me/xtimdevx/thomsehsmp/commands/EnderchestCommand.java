package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("enderchest")) {
            Player player = (Player) sender;


            if(!player.hasPermission("smp.command.enderchest")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if(args.length == 0) {
                player.sendMessage("§cUsage: /enderchest <player>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if(target == null) {
                player.sendMessage("§cError: This player is not online.");
                return true;
            }

            player.openInventory(target.getEnderChest());
            player.sendMessage(MessageUtils.GARY + "I am opening §c" + target.getName() + "'s §fenderchest for you.");

        }
        return true;
    }
}
