package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {

    public static int taskID = -1;
    public static int taskID2 = -1;
    public static int taskID3 = -1;
    public static int taskID4 = -1;
    public static int taskID5 = -1;
    public static int taskID6 = -1;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("back")) {
            Player player = (Player) sender;

            User user = User.get(player);

            Location location = player.getLocation();

            if(user.getFile().get("back") == null) {
                    player.sendMessage("§c§lERROR: §cNo previous location was found.");
                return true;
            }

            double bx = user.getFile().getDouble("back.x");
            double by = user.getFile().getDouble("back.y");
            double bz = user.getFile().getDouble("back.z");
            String bPitch = user.getFile().getString("back.pitch");
            String bYaw = user.getFile().getString("back.yaw");
            String bWorld = user.getFile().getString("back.world");

            float pitch = Float.parseFloat(bPitch);
            float yaw = Float.parseFloat(bYaw);

            Location back = new Location(Bukkit.getWorld(bWorld), bx, by, bz);

            back.setPitch(pitch);
            back.setYaw(yaw);

            player.sendMessage("§8> §fYou will be teleported in §35 §fseconds. Please refrain from moving until then!");

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
                    player.sendMessage("§8> §fYou are being teleported back to your previous location.");

                    Location bloc = player.getLocation();

                    double bx = bloc.getX();
                    double by = bloc.getY();
                    double bz = bloc.getZ();
                    float bPitch = bloc.getPitch();
                    float bYaw = bloc.getYaw();
                    String bWorld = bloc.getWorld().getName();

                    user.getFile().set("back.x", bx);
                    user.getFile().set("back.y", by);
                    user.getFile().set("back.z", bz);
                    user.getFile().set("back.pitch", bPitch);
                    user.getFile().set("back.yaw", bYaw);
                    user.getFile().set("back.world", bWorld);
                    user.saveFile();

                    player.teleport(back);
                    SpawnCommand.moving.remove(player);
                }
            }, 100L);

        }
        return true;
    }
}
