package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.managers.ScoreboardManager;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.PermsUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.LuckPermsEvent;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetRankCommand implements CommandExecutor, TabCompleter {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("smp.commands.setrank")) {
            sender.sendMessage(MessageUtils.NOPERM);
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage("§cMissing arguments: /setrank <player> <rank> <-s>");
            return true;
        }
        if(args.length == 3) {
            if(args[2].equalsIgnoreCase("-s")) {
                Group group = PermsUtils.getInstance().getGroup(args[1]);

                if (group == null) {
                    sender.sendMessage("§cERROR: This group does not excist.");
                    return true;
                }


                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    sender.sendMessage("§cERROR: This sender is not online.");
                    return true;
                }

                PermsUtils.getInstance().setGroup(target, args[1]);

                Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ScoreboardManager.updateScoreBoard(target);
                    }
                }, 20);
                return true;
            }
        }
        Group group = PermsUtils.getInstance().getGroup(args[1]);

        if (group == null) {
            sender.sendMessage("§cERROR: This group does not excist.");
            return true;
        }


        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("§cERROR: This sender is not online.");
            return true;
        }

        PermsUtils.getInstance().setGroup(target, args[1]);


        Bukkit.broadcastMessage("§8§m----------------------------------------------------");
        MessageUtils.broadcastCenteredMessage("§3§lRank set!");
        Bukkit.broadcastMessage(" ");
        MessageUtils.broadcastCenteredMessage("§fPlayer §3§o" + args[0] + "");
        MessageUtils.broadcastCenteredMessage("§3§l" + args[1]);
        Bukkit.broadcastMessage("§8§m----------------------------------------------------");


        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
                ScoreboardManager.updateScoreBoard(target);
            }
        }, 20);

        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if(args.length == 1) {
            return null;
        }

        if (args.length == 2) {
            if (sender.hasPermission("smp.commands.setrank")) {
                commands.add("Owner");
                commands.add("Developer");
                commands.add("Mod");
                commands.add("Builder");
                commands.add("Twitch");
                commands.add("Emerald");
                commands.add("Diamond");
                commands.add("Gold");
                commands.add("Default");
            }
        }
        StringUtil.copyPartialMatches(args[1], commands, completions);
        Collections.sort(completions);
        return completions;
    }
}
