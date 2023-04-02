package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CraftCommand implements CommandExecutor {

    public static final int CRAFTCOOLDOWN = 120;
    public static Map<String, Long> cooldownscraft = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);

        if (!player.hasPermission("smp.command.craft")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if (cooldownscraft.containsKey(player.getUniqueId().toString())) {
            long secondsleft = ((cooldownscraft.get(player.getUniqueId().toString()) / 1000) + CRAFTCOOLDOWN) - (System.currentTimeMillis() / 1000);

            if (secondsleft > 0) {
                player.sendMessage("§8> §fYou have to wait §3§o" + RewardCommand.convert(secondsleft) + " §fseconds before using that command again.");

            } else {
                cooldownscraft.put(player.getUniqueId().toString(), System.currentTimeMillis());
                player.openWorkbench(player.getLocation(), true);
                player.sendMessage("§8> §fYou opened your §3§oVirtual Workbench§f.");
            }
        } else {
            cooldownscraft.put(player.getUniqueId().toString(), System.currentTimeMillis());
            player.openWorkbench(player.getLocation(), true);
            player.sendMessage("§8> §fYou opened your §3§oVirtual Workbench§f.");
        }
        return true;
    }
}
