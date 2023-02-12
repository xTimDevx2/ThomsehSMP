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
                Bukkit.broadcastMessage("§8§m----------------------------------------------------");
                MessageUtils.broadcastCenteredMessage( "§5§lThomseh is nu live!");
                MessageUtils.broadcastCenteredMessage( "§5§nTwitch.tv/Thomseh");
                MessageUtils.broadcastCenteredMessage( "Kom even langs en geef een follow!");
                Bukkit.broadcastMessage("§8§m----------------------------------------------------");

            }
            return true;
        }

        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("live")) {
                String game = args[1];
                Bukkit.broadcastMessage("§8§m----------------------------------------------------");
                MessageUtils.broadcastCenteredMessage( "§5§lThomseh is nu live!");
                MessageUtils.broadcastCenteredMessage( "§5§nTwitch.tv/Thomseh");
                MessageUtils.broadcastCenteredMessage( "§fVandaag spelen we §5§n" + game.replace("_", " ") + "§f.");
                MessageUtils.broadcastCenteredMessage( "Kom even langs en geef een follow!");
                Bukkit.broadcastMessage("§8§m----------------------------------------------------");

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
