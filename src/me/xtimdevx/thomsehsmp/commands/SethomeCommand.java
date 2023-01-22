package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SethomeCommand implements CommandExecutor {



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        Location location = player.getLocation();

        User user = User.get(player);
        if(args.length == 0) {
            user.getFile().set("home.home.x", location.getX());
            user.getFile().set("home.home.y", location.getY());
            user.getFile().set("home.home.z", location.getZ());
            user.getFile().set("home.home.world", location.getWorld().getName());
            user.getFile().set("home.home.pitch", location.getPitch());
            user.getFile().set("home.home.yaw", location.getYaw());
            user.saveFile();

            if (user.getFile().getStringList("homelist").stream().anyMatch("home"::equalsIgnoreCase)) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fThe location of your home has been altered. §7§o(Naam: home)");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fDe locatie van je home is aangepast. §7§o(Naam: home)");
                }
            } else {
                Main.home.clear();
                Main.home.addAll(user.getFile().getStringList("homelist"));

                if(player.hasPermission("smp.homes.gold")) {
                    if(Main.home.size() == 5) {
                        if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            player.sendMessage("§cError: You cannot set more then 5 homes.");
                        }
                        if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                            player.sendMessage("§cError: Je kan niet meer als 5 homes zetten.");
                        }
                        return true;
                    }
                }else {
                    if(Main.home.size() == 3) {
                        if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            player.sendMessage("§cError: You cannot set more then 3 homes.");
                        }
                        if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                            player.sendMessage("§cError: Je kan niet meer als 3 homes zetten.");
                        }
                        return true;
                    }
                }
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fYou placed your home here. §7§o(Name: home)");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fJe hebt je home hier geplaatst. §7§o(Naam: home)");
                }

                Main.home.add("Home");
                user.getFile().set("homelist", Main.home);
                user.saveFile();
            }
            return true;
        }

        if(args.length == 1) {
            String name = args[0];

            user.getFile().set("home." + name + ".x", location.getX());
            user.getFile().set("home." + name + ".y", location.getY());
            user.getFile().set("home." + name + ".z", location.getZ());
            user.getFile().set("home." + name + ".world", location.getWorld().getName());
            user.getFile().set("home." + name + ".pitch", location.getPitch());
            user.getFile().set("home." + name + ".yaw", location.getYaw());
            user.saveFile();

            if (user.getFile().getStringList("homelist").stream().anyMatch(name::equalsIgnoreCase)) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fThe location of your home has been altered. §7§o(Name: " + name + ")");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fDe locatie van je home is aangepast. §7§o(Naam: " + name + ")");
                }
            } else {
                Main.home.clear();
                Main.home.addAll(user.getFile().getStringList("homelist"));

                if(player.hasPermission("smp.homes.gold")) {
                    if(Main.home.size() == 5) {
                        if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            player.sendMessage("§cError: You cannot set more then 5 homes.");
                        }
                        if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                            player.sendMessage("§cError: Je kan niet meer als 5 homes zetten.");
                        }                            return true;
                    }
                }else {
                    if(Main.home.size() == 3) {
                        if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            player.sendMessage("§cError: You cannot set more then 3 homes.");
                        }
                        if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                            player.sendMessage("§cError: Je kan niet meer als 3 homes zetten.");
                        }
                        return true;
                    }
                }
                Main.home.add(name);
                user.getFile().set("homelist", Main.home);
                user.saveFile();
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fYou placed your home here. §7§o(Name: home)");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fJe hebt je home hier geplaatst. §7§o(Naam: home)");
                }
            }

        }
        return true;
    }
}
