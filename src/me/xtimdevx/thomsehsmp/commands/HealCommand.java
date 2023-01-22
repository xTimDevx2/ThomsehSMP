package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class HealCommand implements CommandExecutor {

    public static final int HEALCOOLDOWNGOLD = 3600;
    public static Map<String, Long> cooldownshealgold = new HashMap<>();


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);

        if(args.length == 0) {
            if(!player.hasPermission("smp.command.heal.self")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if(player.hasPermission("rank.gold")) {
                if (cooldownshealgold.containsKey(player.getUniqueId().toString())) {
                    long secondsleft = ((cooldownshealgold.get(player.getUniqueId().toString()) / 1000) + HEALCOOLDOWNGOLD) - (System.currentTimeMillis() / 1000);

                    if (secondsleft > 0) {
                        if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            player.sendMessage("§8> §fYou have to wait §3§o" + RewardCommand.convert(secondsleft) + " §fmore seconds before healing again.");
                        }
                        if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                            player.sendMessage("§8> §fJe moet nog §3§o" + RewardCommand.convert(secondsleft) + " §fwachten tot je opnieuw mag healen.");
                        }

                    } else {
                        cooldownshealgold.put(player.getUniqueId().toString(), System.currentTimeMillis());
                        player.setHealth(20);
                        player.setSaturation(20);
                        player.setFireTicks(0);

                        if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            player.sendMessage("§8> §fYou just §3§ohealed§f yourself!");
                        }
                        if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                            player.sendMessage("§8> §fJe hebt jezelf §3§ogehealed§f!");
                        }
                    }
                }else {
                    cooldownshealgold.put(player.getUniqueId().toString(), System.currentTimeMillis());
                    player.setHealth(20);
                    player.setSaturation(20);
                    player.setFireTicks(0);

                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§8> §fYou just §3§ohealed§f yourself!");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§8> §fJe hebt jezelf §3§ogehealed§f!");
                    }                }
            }
            return true;
        }

        if(!player.hasPermission("smp.command.heal.other")) {
            if(!player.hasPermission("smp.command.heal.self")) {
                player.sendMessage(MessageUtils.NOPERM);
            }else {
                player.sendMessage("§c§lError: §cJe kan alleen jezelf healen.");
            }
            return true;
        }
        player.sendMessage("§cDit is nog geen functie #blametim");



        return true;
    }
}
