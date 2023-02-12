package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarnCommand implements CommandExecutor {

   public static List<String> warnings = Settings.getInstance().getData().getStringList("warns");

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("warn")) {
            if(!player.hasPermission("smp.command.warn")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }
            if(args.length < 2) {
                player.sendMessage("§cUsage: /warn <player> <reden>");
                return true;
            }

            Player target = (Player) Bukkit.getPlayer(args[0]);

            if(target == null) {
                player.sendMessage("§cError: Speler niet online");
                return true;
            }


            StringBuilder message = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                message.append(args[i]).append(" ");
            }

            String msg = message.toString().trim();

            player.sendMessage(MessageUtils.GARY + "Je hebt §c§o" + target.getName() + " §fgewarned voor §c" + msg + "§f.");
            player.sendMessage(MessageUtils.GARY + "Warning opgeslagen in database.");


            User user = User.get(target);

            target.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(target, "§4§lWarning!");
            MessageUtils.sendCenteredMessage(target, "§fJe hebt een warning gekregen!");
            MessageUtils.sendCenteredMessage(target, "§c" + msg);
            MessageUtils.sendCenteredMessage(target, "§7§o(Staff zal deze warning reviewen.)");
            target.sendMessage("§8§m----------------------------------------------------");


            warnings.addAll(user.getFile().getStringList("warns"));
            warnings.add(msg + " - " + player.getName());
            user.getFile().set("warns", warnings);
            user.saveFile();

            warnings.clear();
        }

        if(cmd.getName().equalsIgnoreCase("warnlist")) {
            if(!player.hasPermission("smp.command.warnlist")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }
            if(args.length == 0) {
                player.sendMessage("§cUsage: /warnlist <player>");
                return true;
            }

            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

            if(!target.hasPlayedBefore()) {
                player.sendMessage("§cError: Deze speler heeft is nog niet gejoined.");
                return true;
            }

            User user = User.get(target);

            if(user.getFile().getStringList("warns").isEmpty()) {
                player.sendMessage(MessageUtils.GARY + "Deze speler heeft geen warnings.");
                return true;
            }

            List<String> strings = user.getFile().getStringList("warns");

            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§c§l" + args[0] + "'s Warnings");
            for(String entry : strings) {
                player.sendMessage("§8- §f" + entry);
            }
            player.sendMessage("§8§m----------------------------------------------------");

        }
        return true;
    }
}
