package me.xtimdevx.thomsehsmp.commands;

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
        Player player = (Player) sender;
        if (!player.hasPermission("smp.commands.setrank")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }
        if (args.length < 2) {
            player.sendMessage("§cMissing arguments: /setrank <player> <rank>");
            return true;
        }
        Group group = PermsUtils.getInstance().getGroup(args[1]);

        if (group == null) {
            player.sendMessage("§cERROR: This group does not excist.");
            return true;
        }


        OfflinePlayer target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage("§cERROR: This player is not online.");
            return true;
        }

        PermsUtils.getInstance().setGroup(target, args[1]);


        Bukkit.broadcastMessage("§8§m----------------------------------------------------");
        MessageUtils.broadcastCenteredMessage("§3§lRank set!");
        Bukkit.broadcastMessage(" ");
        MessageUtils.broadcastCenteredMessage("§3" + args[0] + " §frecieved the rank:");
        MessageUtils.broadcastCenteredMessage("§3§l" + args[1]);
        Bukkit.broadcastMessage("§8§m----------------------------------------------------");

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
