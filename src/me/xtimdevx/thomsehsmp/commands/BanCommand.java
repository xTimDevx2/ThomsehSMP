package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BanCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
        final Player player = (Player) sender;
        User user = User.get(player);
        if (cmd.getName().equalsIgnoreCase("ban")) {
            if (!player.hasPermission("smp.command.ban")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if (args.length < 2) {
                player.sendMessage("§cUsage: /ban <player> <reason>");
                return true;
            }

            final Player target = Bukkit.getServer().getPlayer(args[0]);

            final BanList list = Bukkit.getBanList(BanList.Type.NAME);


            StringBuilder reason = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                reason.append(args[i]).append(" ");
            }

            final String msg = reason.toString().trim();

            if (target == null) {
                Bukkit.broadcastMessage(MessageUtils.GARY + "§c" + args[0] + " §fhas been banned from the server for §c" + msg + "§f!");
                list.addBan(args[0], msg, null, sender.getName());
                user.getFile().set("bans", user.getFile().getInt("bans") + 1);
                user.saveFile();
                return true;
            }

            user.getFile().set("bans", user.getFile().getInt("bans") + 1);
            user.saveFile();
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.playSound(online.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
            }

            BanEntry ban = list.addBan(target.getName(), msg, null, player.getName());

            target.setWhitelisted(false);

            target.kickPlayer("§8§m----------------------------------------------" +
                    "\n     §c§lYOU HAVE BEEN BANNED" +
                    "\n§fReason§8: §f" + ban.getReason() +
                    "\n§fBanned by§8: §f" + ban.getSource() +
                    "\n§8§m----------------------------------------------"
            );

        }
        return true;
    }
}
