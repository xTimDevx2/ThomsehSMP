package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("gamemode")) {
            Player player = (Player) sender;
            User user = User.get(player);

            boolean builder = user.getFile().getBoolean("builder");

            if(!player.isOp() && !builder) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if (args.length == 0) {
                player.sendMessage("§cUsage: /gamemode <mode> [player]");
                return true;
            }

            if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
                if(args.length == 1) {
                    if(player.getGameMode() != GameMode.CREATIVE) {
                        if(player.getGameMode() == GameMode.SURVIVAL) {
                            Utils.saveInventory(player);
                        }
                        if(user.getFile().get("builderinventory") != null) {
                            Utils.restoreBuilderInventory(player);
                        }else {
                            player.getInventory().clear();
                            player.getInventory().setArmorContents(null);
                        }
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("§8> §fGamemode verandert naar §3§oCreative§f!");
                    }
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if(target == null) {
                    player.sendMessage("§cError: Deze speler is niet online.");
                }
                User tuser = User.get(target);
                if(player.getGameMode() != GameMode.CREATIVE) {
                    Utils.saveInventory(target);
                    if(tuser.getFile().get("builderinventory") != null) {
                        Utils.restoreBuilderInventory(target);
                    }else {
                        target.getInventory().clear();
                        target.getInventory().setArmorContents(null);
                    }
                    target.setGameMode(GameMode.CREATIVE);
                    target.sendMessage("§8> §fGamemode verandert naar §3§oCreative§f!");
                    player.sendMessage("§8> §fGamemode van §3§o" + target.getName() + " §fverandert naar §3§oCreative§f!");
                }else {
                    player.sendMessage("§cError: Deze speler zit al in creative mode.");
                }

            }

            if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
                if(args.length == 1) {
                    if(player.getGameMode() != GameMode.SURVIVAL) {
                        if(player.getGameMode() == GameMode.CREATIVE) {
                            Utils.saveBuilderInventory(player);
                        }
                        Utils.restoreInventory(player);
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("§8> §fGamemode verandert naar §3§oSurvival§f!");
                    }
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if(target == null) {
                    player.sendMessage("§cError: Deze speler is niet online.");
                }
                User tuser = User.get(target);
                if(target.getGameMode() != GameMode.SURVIVAL) {
                    if(target.getGameMode() == GameMode.CREATIVE) {
                        Utils.saveBuilderInventory(target);
                    }
                    Utils.restoreInventory(target);
                    target.setGameMode(GameMode.SURVIVAL);
                    target.sendMessage("§8> §fGamemode verandert naar §3§oSurvival§f!");
                    player.sendMessage("§8> §fGamemode van §3§o" + target.getName() + " §fverandert naar §3§oSurvival§f!");
                }else {
                    player.sendMessage("§cError: Deze speler zit al in survival mode.");
                }

            }

            if(args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                if(args.length == 1) {
                    if(player.getGameMode() != GameMode.SPECTATOR) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("§8> §fGamemode verandert naar §3§oSpectator§f!");
                    }
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if(target == null) {
                    player.sendMessage("§cError: Deze speler is niet online.");
                }
                User tuser = User.get(target);
                if(target.getGameMode() != GameMode.SPECTATOR) {

                    target.setGameMode(GameMode.SPECTATOR);
                    target.sendMessage("§8> §fGamemode verandert naar §3§oSpectator§f!");
                    player.sendMessage("§8> §fGamemode van §3§o" + target.getName() + " §fverandert naar §3§oSpectator§f!");
                }else {
                    player.sendMessage("§cError: Deze speler zit al in spectator mode.");
                }

            }
            if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
                if(args.length == 1) {
                    if(player.getGameMode() != GameMode.ADVENTURE) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage("§8> §fGamemode verandert naar §3§oAdventure§f!");
                    }
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if(target == null) {
                    player.sendMessage("§cError: Deze speler is niet online.");
                }
                User tuser = User.get(target);
                if(target.getGameMode() != GameMode.ADVENTURE) {

                    target.setGameMode(GameMode.ADVENTURE);
                    target.sendMessage("§8> §fGamemode verandert naar §3§oAdventure§f!");
                    player.sendMessage("§8> §fGamemode van §3§o" + target.getName() + " §fverandert naar §3§oAdventure§f!");
                }else {
                    player.sendMessage("§cError: Deze speler zit al in adventure mode.");
                }

            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if(args.length == 1) {
            if (sender.hasPermission("smp.commands.gamemode")) {
                commands.add("Creative");
                commands.add("Survival");
                commands.add("Spectator");
                commands.add("Adventure");
            }
        }

        if (args.length == 2) {
            return null;
        }
        StringUtil.copyPartialMatches(args[0], commands, completions);
        Collections.sort(completions);
        return completions;
    }
}
