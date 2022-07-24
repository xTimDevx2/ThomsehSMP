package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeCommand implements CommandExecutor {



    public static int taskID = -1;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);
        if(args.length == 0) {

            List<String> strings = user.getFile().getStringList("homelist");

            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lHome List");
            for(String entry : strings) {
                player.sendMessage("§8- §f" + entry);
            }
            player.sendMessage("§8§m----------------------------------------------------");


        }else {

            String home = args[0];
            if(user.getFile().get("home." + home) == null) {
                player.sendMessage("§cERROR: Deze home bestaat niet.");
                return true;
            }
            player.sendMessage("§8> §fWe teleporteren je in §35 §fseconden. Niet bewegen!");
            SpawnCommand.moving.add(player);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {


                    double x = user.getFile().getDouble("home." + home + ".x");
                    double y = user.getFile().getDouble("home." + home + ".y");
                    double z = user.getFile().getDouble("home." + home + ".z");
                    String pitchstring = user.getFile().getString("home." + home + ".pitch");
                    String yawstring = user.getFile().getString("home." + home + ".yaw");
                    String world = user.getFile().getString("home." + home + ".world");

                    float pitch = Float.parseFloat(pitchstring);
                    float yaw = Float.parseFloat(yawstring);

                    Location homeloc = new Location(Bukkit.getWorld(world), x ,y, z);
                    homeloc.setPitch(pitch);
                    homeloc.setYaw(yaw);

                    player.teleport(homeloc);


                    player.sendMessage("§8> §fWe teleporteren je naar je home. §7§o(" + home + ")");
                    SpawnCommand.moving.remove(player);
                }
            }, 100L);


        }
        return true;
    }
}
