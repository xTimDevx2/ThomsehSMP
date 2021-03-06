package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class UserCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("user")) {
            Player player = (Player) sender;

            if(!player.hasPermission("smp.command.user")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if(args.length < 2) {
                player.sendMessage("§cUsage: /user <user> reload");
                player.sendMessage("§cUsage: /user <user> resetquests");
                player.sendMessage("§cUsage: /user <user> reset");
                return true;
            }

            if(args[1].equalsIgnoreCase("reload")) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    player.sendMessage("§cError: Target not online.");
                    return true;
                }

                User user = User.get(target);
                user.saveFile();
                user.reloadFile();
                player.sendMessage("§8> §fReloaded user file from §3" + target.getName() + "§f.");
            }

            if(args[1].equalsIgnoreCase("resetquests")) {
                Player target = Bukkit.getPlayer(args[0]);

                if(target == null) {
                    player.sendMessage("§cError: Target not online.");
                    return true;
                }
                User user = User.get(target);

                user.getFile().set("quest.TIMBER.started", false);
                user.getFile().set("quest.TIMBER.TIMBER_16LOGS.active", false);
                user.getFile().set("quest.TIMBER.TIMBER_16LOGS.completed", false);
                user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.completed", false);
                user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.active", false);
                user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.stage", 1);
                user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.heatwakerherkansing", false);
                user.saveFile();
                player.sendMessage("§8> §fReset quests for §3" + target.getName() + "§f.");
            }
            if(args[1].equalsIgnoreCase("reset")) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);


                File file = new File(Main.plugin.getDataFolder() + "<users>"+ target.getUniqueId().toString() + ".yml");
                file.delete();

                if(target.isOnline()) {
                    int taskID = -1;
                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.kickPlayer("§3§lSMP \n§fYour account has been reset, please relog.");
                        }
                    }, 20);
                }

                player.sendMessage("§8> §fReset §3" + target.getName() + "§f's account.");
            }


        }
        return true;
    }
}
