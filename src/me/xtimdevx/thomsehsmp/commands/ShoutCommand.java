package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShoutCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
        Player player = (Player) sender;

        if(!player.hasPermission("smp.command.shout")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("§cUsage: /shout <message>");
            player.sendMessage("§cUsage: /shout live <game>");
            return true;
        }

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("live")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§5§lThomseh is nu live!");
                MessageUtils.sendCenteredMessage(player, "§5§nTwitch.tv/Thomseh");
                MessageUtils.sendCenteredMessage(player, "Kom even langs en geef een follow!");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            return true;
        }

        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("live")) {
                String game = args[1];
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§5§lThomseh is nu live!");
                MessageUtils.sendCenteredMessage(player, "§5§nTwitch.tv/Thomseh");
                MessageUtils.sendCenteredMessage(player, "§fVandaag spelen we §5§n" + game.replace("_", " ") + "§f.");
                MessageUtils.sendCenteredMessage(player, "Kom even langs en geef een follow!");
                player.sendMessage("§8§m----------------------------------------------------");

            }

            return true;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }

        String msg = sb.toString().trim();

        Bukkit.broadcastMessage("§3§lShout §8> §f" + msg);
        return true;
    }
}
