package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
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


            player.sendMessage("§8» §fJe hebt je home hier geplaatst. §7§o(Naam: home)");
            return true;
        }

        if(args.length == 1) {
            String name = args[0].toLowerCase();

            user.getFile().set("home." + name + ".x", location.getX());
            user.getFile().set("home." + name + ".y", location.getY());
            user.getFile().set("home." + name + ".z", location.getZ());
            user.getFile().set("home." + name + ".world", location.getWorld().getName());
            user.getFile().set("home." + name + ".pitch", location.getPitch());
            user.getFile().set("home." + name + ".yaw", location.getYaw());

            if(user.getFile().getStringList("homelist").contains(name)) {
                player.sendMessage("§8» §fDe locatie van je home is aangepast. §7§o(Naam: " + name + ")");
            } else {
                Main.home.clear();
                Main.home.addAll(user.getFile().getStringList("homelist"));
                if(Main.home.size() == 3) {
                    player.sendMessage("§cError: Je kan niet meer als 3 homes zetten.");
                    return true;
                }
                Main.home.add(name);
                user.getFile().set("homelist", Main.home);
                user.saveFile();
                player.sendMessage("§8» §fJe hebt je home hier geplaatst. §7§o(Naam: " + name + ")");
            }

        }
        return true;
    }
}
