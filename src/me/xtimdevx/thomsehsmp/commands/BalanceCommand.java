package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.managers.EconomyManager;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    private final EconomyManager economyManager = new EconomyManager();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User puser = User.get(player);


        if(args.length == 0) {
            player.sendMessage("§8> §fYour Balance: §3" + economyManager.getBalance(player) + " §f⛀");
            return true;
        }

        if(args.length == 1) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

            User user = User.get(target);

            if(user == null) {
                player.sendMessage("§cThis player is not known to us yet.");
                return true;
            }

            player.sendMessage("§8> §f" + target.getName() + "'s Balance: §3" + user.getFile().getInt("balance") + " §f⛀");
            return true;
        }

        if(args.length < 3) {
            if(!player.hasPermission("smp.commands.balance.admin")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }
            player.sendMessage("§cUsage: /balance add <player> <amount>");
            player.sendMessage("§cUsage: /balance remove <player> <amount>");
            player.sendMessage("§cUsage: /balance set <player> <amount>");
        }else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

            if(target.isOnline()) {
                int amount = Integer.parseInt(args[2]);
                Player targetonline = Bukkit.getPlayer(args[1]);
                if(args[0].equalsIgnoreCase("add")) {
                    economyManager.addBalance(targetonline, amount);

                    player.sendMessage("§8> §fAdded §3" + amount + " §f⛀ to " + target.getName() + "'s balance.");
                }
                if(args[0].equalsIgnoreCase("remove")) {
                    economyManager.removeBalance(targetonline, amount);

                    player.sendMessage("§8> §fRemoved §3" + amount + " §f⛀ from " + target.getName() + "'s balance.");
                }
                if(args[0].equalsIgnoreCase("set")) {
                    economyManager.setBalance(targetonline, amount);

                    player.sendMessage("§8> §fSet §3" + target.getName() + " §f's balance to §3" + amount + " §f⛀.");
                }
                return true;
            }

            int amount = Integer.parseInt(args[2]);
            if(args[0].equalsIgnoreCase("add")) {
                economyManager.addBalance(target, amount);

                player.sendMessage("§8> §fAdded §3" + amount + " §f⛀ to " + target.getName() + "'s balance.");
            }
            if(args[0].equalsIgnoreCase("remove")) {
                economyManager.removeBalance(target, amount);

                player.sendMessage("§8> §fRemoved §3" + amount + " §f⛀ from " + target.getName() + "'s balance.");
            }
            if(args[0].equalsIgnoreCase("set")) {
                economyManager.setBalance(target, amount);

                player.sendMessage("§8> §fSet §3" + target.getName() + " §f's balance to §3" + amount + " §f⛀.");
            }
        }
        return true;

    }
}
