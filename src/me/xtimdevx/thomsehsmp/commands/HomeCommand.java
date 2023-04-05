package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;


public class HomeCommand implements CommandExecutor, TabCompleter {



    public static int taskID = -1;
    public static int taskID2 = -1;
    public static int taskID3 = -1;
    public static int taskID4 = -1;
    public static int taskID5 = -1;
    public static int taskID6 = -1;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);
        if(args.length == 0) {

            List<String> strings = user.getFile().getStringList("homelist");

            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lHome List");
            for(String entry : strings) {
                if(entry.isEmpty()) {
                    player.sendMessage("§fNo homes yet..");
                }else {
                    player.sendMessage("§8- §f" + entry);
                }
            }
            player.sendMessage("§8§m----------------------------------------------------");


        }else {

            String home = args[0];
            if (user.getFile().getStringList("homelist").stream().noneMatch(home::equalsIgnoreCase)) {
                player.sendMessage("§cERROR: This home does not exist.");
                return true;
            }
            player.sendMessage("§8> §fWe will teleport you in §35 §fseconds. Do not move!");

            SpawnCommand.moving.add(player);
            taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("§3§lTeleport", "§8> §45 §8<");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 0L);
            taskID3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("§3§lTeleport", "§8> §44 §8<");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 20L);
            taskID4 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("§3§lTeleport", "§8> §c3 §8<");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 40L);
            taskID5 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("§3§lTeleport", "§8> §e2 §8<");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 60L);
            taskID6 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("§3§lTeleport", "§8> §a1 §8<");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                }
            }, 80L);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendTitle("", "");

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

                    Location location = player.getLocation();

                    double bx = location.getX();
                    double by = location.getY();
                    double bz = location.getZ();
                    float bPitch = location.getPitch();
                    float bYaw = location.getYaw();
                    String bWorld = location.getWorld().getName();

                    user.getFile().set("back.x", bx);
                    user.getFile().set("back.y", by);
                    user.getFile().set("back.z", bz);
                    user.getFile().set("back.pitch", bPitch);
                    user.getFile().set("back.yaw", bYaw);
                    user.getFile().set("back.world", bWorld);
                    user.saveFile();

                    player.teleport(homeloc);

                    player.sendMessage("§8> §fWe will teleport you to your home. §7§o(" + home + ")");
                    SpawnCommand.moving.remove(player);
                }
            }, 100L);


        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        User user = User.get(Bukkit.getPlayer(sender.getName()));

        List<String> strings = (List<String>) user.getFile().getStringList("homelist");
        commands.addAll(strings);

        if(strings == null) {
            List<String> empty = new ArrayList<>();
            strings = empty;
        }

        List<String> newList = new ArrayList<>();

        for(String home : strings) {
            newList.add(home);
        }

        return newList;
    }
}
