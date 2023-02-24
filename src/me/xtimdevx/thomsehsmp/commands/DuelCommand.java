package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.managers.DuelsManager;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class DuelCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("duel")) {
            Player player = (Player) sender;
            User user = User.get(player);

            /*

            if (args.length == 0) {
                player.sendMessage("§c? /duel <player>");
                return true;
            }

            DuelsManager manager = new DuelsManager();

            if (args[0].equalsIgnoreCase("accept")) {
                if (user.getFile().get("duelincoming").equals("none")) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cERROR: No incoming duel requests.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cERROR: Geen inkomende duel aanvraag.");
                    }
                    return true;
                }

                Player target = Bukkit.getPlayer(user.getFile().getString("duelincoming"));
                if (target == null) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cERROR: This player is no longer online.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cERROR: Deze speler is niet meer online.");
                    }
                    return true;
                }
                Bukkit.getScheduler().cancelTask(DuelsManager.taskID);

                Utils.saveInventory(player);
                Utils.saveInventory(target);

                manager.startDuel(player, target, user.getFile().getString("duelmode"));
                return true;
            }
            if (args[0].equalsIgnoreCase("deny")) {
                if (user.getFile().get("duelincoming").equals("none")) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cERROR: No incoming duel request.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cERROR: Geen inkomende duel aanvraag.");
                    }
                    return true;
                }

                Player target = Bukkit.getPlayer(user.getFile().getString("duelincoming"));
                if (target == null) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cERROR: This player is no longer online.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cERROR: Deze speler is niet meer online.");
                    }
                    return true;
                }
                Bukkit.getScheduler().cancelTask(DuelsManager.taskID);

                User tuser = User.get(target);
                tuser.getFile().set("duelrequest", "none");
                tuser.getFile().set("duelincoming", "none");
                user.getFile().set("duelincoming", "none");
                user.getFile().set("duelrequest", "none");
                DuelsManager.duelInvite.remove(player);
                user.saveFile();
                tuser.saveFile();

                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fYou denied the duel request.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fJe hebt de duel aanvraag geweigerd.");
                }

                if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    target.sendMessage("§8> §3" + player.getName() + " §fdenied your duel request.");
                }
                if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                    target.sendMessage("§8> §3" + player.getName() + " §fheeft je duel aanvraag geweigerd.");
                }
                return true;
            }


            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cError: This player is not online.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cError: Deze speler is niet online.");
                }
                return true;
            }
            if (DuelsManager.duelInvite.contains(target)) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cError: This player has already been invited to a duel.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cError: Deze speler is al geinviteerd voor een duel.");
                }
                return true;
            }
            if(DuelsManager.duel.contains(player)) {
                player.sendMessage("§cError: Je zit al in een duel");
                return true;
            }
            if(DuelsManager.duel.contains(target)) {
                player.sendMessage("§cError: Deze speler zit al in een duel");
                return true;
            }
            if(target.getGameMode() != GameMode.SURVIVAL) {
                player.sendMessage("§cError: Deze speler zit niet in survival mode");
                return true;
            }
            if(player.getGameMode() != GameMode.SURVIVAL) {
                player.sendMessage("§cError: Je zit niet in survival mode");
                return true;
            }
            User tuser = User.get(target);
            boolean builder = user.getFile().getBoolean("builder");
            boolean tbuilder = tuser.getFile().getBoolean("builder");
            if(builder) {
                player.sendMessage("§cJe kan niemand inviten voor een duel in builder mode.");
                return true;
            }
            if(tbuilder) {
                player.sendMessage("§cDeze speler zit in builder mode en kan niet geinviteerd worden.");
                return true;
            }
            if (target == player) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cERROR: You can't duel yourself.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cERROR: Je kan jezelf niet dueleren.");
                }
                return true;
            }

            user.getFile().set("duelrequest", target.getName());
            user.saveFile();

            manager.proposeKits(player);
            //
            
             */
        }
        return true;
    }
}
