package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.DateUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
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
import java.util.Date;

public class TempbanCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, final String[] args) {
        final Player p = (Player) sender;
        final User user = User.get(p);
        if (cmd.getName().equalsIgnoreCase("tempban")) {
            if (!p.hasPermission("smp.command.tempban")) {
                p.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if (args.length < 3) {
                p.sendMessage("§cUsage: /tempban <player> <time> <reason>");
                return true;
            }

            final Player target = Bukkit.getServer().getPlayer(args[0]);

            final BanList list = Bukkit.getBanList(BanList.Type.NAME);

            long time = DateUtils.parseDateDiff(args[1], true);
            Date date = new Date(time);

            StringBuilder reason = new StringBuilder();

            for (int i = 2; i < args.length; i++) {
                reason.append(args[i]).append(" ");
            }

            final String msg = reason.toString().trim();

            if (target == null) {
                p.getServer().broadcastMessage(MessageUtils.GARY + "§c" + args[0] + " §fhas been §ctemporarily banned§f for §c" + msg + "§f!");
                list.addBan(args[0], msg, date, sender.getName());
                user.getFile().set("bans", user.getFile().getInt("bans") + 1);
                user.saveFile();
                return true;
            }

            p.getServer().broadcastMessage(MessageUtils.GARY + "§c" + args[0] + " §fhas been §ctemporarily banned§f for §c" + msg + "§f!");
            user.getFile().set("bans", user.getFile().getInt("bans") + 1);
            user.saveFile();
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.playSound(online.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
            }

            BanEntry ban = list.addBan(target.getName(), msg, date, p.getName());

            target.setWhitelisted(false);

            PlayerDeathEvent event = new PlayerDeathEvent(target, new ArrayList<ItemStack>(), 0, null);
            Bukkit.getServer().getPluginManager().callEvent(event);

            target.kickPlayer("§8§m----------------------------------------------" +
                    "\n     §c§lYOU ARE BANNED" +
                    "\n§fReason§8: §f" + ban.getReason() +
                    "\n§fBanned by§8: §f" + ban.getSource() +
                    "\n§fDuration§8: §f" + DateUtils.formatDateDiff(time) +
                    "\n§8§m----------------------------------------------"
            );

        }
        return true;
    }
}
