package me.xtimdevx.thomsehsmp.events;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandEvents implements Listener, TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ver") || cmd.getName().equalsIgnoreCase("version") || cmd.getName().equalsIgnoreCase("about")) {
            List<String> completions = new ArrayList<>();
            List<String> commands = new ArrayList<>();

            commands.add("Plugins");
            commands.add("Developed");
            commands.add("By");
            commands.add("xTimDevx");
            StringUtil.copyPartialMatches(args[1], commands, completions);
            Collections.sort(completions);
            return completions;
        }
        return null;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();
        Player player = (Player) event.getPlayer();

        String command = message.split(" ")[0].substring(1);

        if (command.equalsIgnoreCase("me") || command.equalsIgnoreCase("minecraft:me") || command.equalsIgnoreCase("kill")) {
            player.sendMessage("§cERROR: You don't have permissions to use this command.");
            event.setCancelled(true);
            return;
        }

        if (command.startsWith("bukkit:") && command.startsWith("minecraft:")) {
            if (player.hasPermission("smp.staff")) {
                return;
            }

            player.sendMessage("§cERROR: You don't have permissions to use this command.");
            event.setCancelled(true);
            return;
        }

        if (command.equalsIgnoreCase("reload") || command.equalsIgnoreCase("rl") || command.equalsIgnoreCase("bukkit:reload") || command.equalsIgnoreCase("bukkit:rl")) {
            event.setCancelled(true);
            player.performCommand("stop");
            return;
        }

        if (command.equalsIgnoreCase("freload") || command.equalsIgnoreCase("frl")) {
            if (!player.hasPermission("uhc.command.freload")) {
                player.sendMessage("§cERROR: You don't have permissions to use this command.");
                return;
            }
            event.setCancelled(true);
            Bukkit.broadcastMessage("§c§lWARNING: We are forcing a server reload. If something is not working please contact Tim.");
            Bukkit.reload();
            Bukkit.broadcastMessage("§aFinished reloading the server.");
        }

        if (command.equalsIgnoreCase("pl") || command.equalsIgnoreCase("plugins") || command.equalsIgnoreCase("?") || command.equalsIgnoreCase("ver") || command.equalsIgnoreCase("version") || command.equalsIgnoreCase("about")) {
            event.setCancelled(true);
            player.sendMessage("§cThe plugins are custom coded by xTimDevx.");
        }
        if(command.equalsIgnoreCase("/calc")||command.equalsIgnoreCase("/calculate")||command.equalsIgnoreCase("/solve")||command.equalsIgnoreCase("/eval")||command.equalsIgnoreCase("/evaluate")) {
            event.setCancelled(true);
            player.sendMessage("§cThis command has been disabled due to bug abuse.");
        }
    }

}
