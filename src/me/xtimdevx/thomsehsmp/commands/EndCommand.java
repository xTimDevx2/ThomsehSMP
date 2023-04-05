package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class EndCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if(!player.hasPermission("smp.command.end")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("§cUsage: /end <close/open>");
            return true;
        }

        FileConfiguration settings = Settings.getInstance().getData();
        if(args[0].equalsIgnoreCase("close")) {
            if(!settings.getBoolean("end")) {
                player.sendMessage("§cThe End is already closed!");
                return true;
            }
            Bukkit.broadcastMessage(MessageUtils.PREFIX + "The §3End §fhas been closed!");

            settings.set("end", false);
            Settings.getInstance().saveData();
        }

        if(args[0].equalsIgnoreCase("open")) {
            if(settings.getBoolean("end")) {
                player.sendMessage("§cThe End is already open!");
                return true;
            }

            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lEnd");
            MessageUtils.sendCenteredMessage(player, "§fThe End is now §a§lopen§f!");
            MessageUtils.sendCenteredMessage(player, "§fDo you want to fight the dragon? §3§o/tpa §fto §lThomseh§f!");
            player.sendMessage("§8§m----------------------------------------------------");

            settings.set("end", true);
            Settings.getInstance().saveData();
        }
        return true;
    }

}
