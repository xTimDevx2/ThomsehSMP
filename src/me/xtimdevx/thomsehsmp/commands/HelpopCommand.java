package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class HelpopCommand implements CommandExecutor {

    public static final int HELPOPCOOLDOWN = 120;
    public static Map<String, Long> cooldownsHelpop = new HashMap<>();


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);

        if (cooldownsHelpop.containsKey(player.getUniqueId().toString())) {
            long secondsleft = ((cooldownsHelpop.get(player.getUniqueId().toString()) / 1000) + HELPOPCOOLDOWN) - (System.currentTimeMillis() / 1000);

            if (secondsleft > 0) {
                player.sendMessage("§8> §fYou still have to wait §3§o" + RewardCommand.convert(secondsleft) + " §fbefore sending another helpop.");

            } else {
                cooldownsHelpop.put(player.getUniqueId().toString(), System.currentTimeMillis());

                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(online.hasPermission("smp.helpop.recieve")) {
                        online.sendMessage("§4§lHELPOP! §c§o" + player.getName() + " §fneeds help. ");
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                    }
                }
                player.sendMessage("§8> §fYou have sent a helpop, a staff member will help you as soon as possible.");
                player.sendMessage("§8> §7§o(Tip: Join our Discord for quick help: https://discord.gg/gquF6tkb3F)");
            }
        } else {
            cooldownsHelpop.put(player.getUniqueId().toString(), System.currentTimeMillis());

            for(Player online : Bukkit.getOnlinePlayers()) {
                if(online.hasPermission("smp.helpop.recieve")) {
                    online.sendMessage("§4§lHELPOP! §c§o" + player.getName() + " §fneeds help. ");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                }
            }
            player.sendMessage("§8> §fYou have sent a helpop, a staff member will help you as soon as possible.");
            player.sendMessage("§8> §7§o(Tip: Join our Discord for quick help: https://discord.gg/gquF6tkb3F)");
        }

        return true;
    }
}
