package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /reply <message>");
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
        }

        if (!Main.msg.containsKey(sender)) {
            Player player = (Player) sender;
            User user = User.get(player);

            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                sender.sendMessage(ChatColor.RED + "You have no one to reply to.");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                sender.sendMessage(ChatColor.RED + "Je hebt niemand om op te antwoorden.");
            }

            return true;
        }

        CommandSender target = Main.msg.get(sender);

        StringBuilder message = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }

        String msg = message.toString().trim();

        sender.sendMessage("§8> §c§lMSG §fme §8-> §f" + target.getName() + " §8» §f" + msg);
        target.sendMessage("§8> §c§lMSG §f" + sender.getName() + " §8-> §fme §8» §f" + msg);


        Main.msg.put(target, sender);
        Main.msg.put(sender, target);

        for(Player online : Bukkit.getOnlinePlayers()) {
            User ouser = User.get(online);
            if(ouser.getFile().getBoolean("socialspy") == true) {
                online.sendMessage("§4§lOP §8> §fSocialyspy:");
                online.sendMessage("§8> §f" + sender.getName() + "§8-> §f" + target.getName() + " §8» §f" + msg);
                return true;
            }
        }
        return true;
    }
}
