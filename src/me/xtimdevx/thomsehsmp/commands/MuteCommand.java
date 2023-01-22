package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.DateUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.TimeZone;

public class MuteCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("mute")) {
                if(!p.hasPermission("smp.command.mute")) {
                p.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if(args.length < 3) {
                p.sendMessage("§c/mute <player> [time] [reason]");
                return true;
            }
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            User user = User.get(target);

            StringBuilder message = new StringBuilder();

            for(int i = 2; i < args.length; i++) {
                message.append(args[i]).append(" ");
            }

            String reason = message.toString().trim();
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            long time = DateUtils.parseDateDiff(args[1], true);

            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                Bukkit.broadcastMessage(MessageUtils.GARY + "§c" + target.getName() + " §fhas been §c" + (args[1].equals("-") ? "muted" : "temp-muted") + " §ffor §c" + reason + "§f.");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                Bukkit.broadcastMessage(MessageUtils.GARY + "§c" + target.getName() + " §fis §c" + (args[1].equals("-") ? "gemute" : "getemp-mute") + " §fvoor §c" + reason + "§f.");
            }
            if(target.isOnline()) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    target.getPlayer().sendMessage("§8> §fYou have been muted for §c" + reason + "§f.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    target.getPlayer().sendMessage("§8> §fJe bent gemute voor §c" + reason + "§f.");
                }
            }

            user.mute(reason, (time <= 0 ? null : new Date(time)));
        }
        return true;
    }
}
