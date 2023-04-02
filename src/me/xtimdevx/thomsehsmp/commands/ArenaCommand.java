package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ArenaCommand implements CommandExecutor {

    public static int taskID = -1;
    public static int taskID2 = -1;
    public static int taskID4 = -1;
    public static int taskID5 = -1;
    public static int taskID6 = -1;
    public static int taskID3 = -1;

    public static ArrayList<Player> moving = new ArrayList<>();


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);

        player.sendMessage("§8> §fYou will be teleported in §35 §fseconds. Please refrain from moving until then!");
        moving.add(player);
        taskID6 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                player.sendTitle("§3§lTeleport", "§8> §45 §8<");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

            }
        }, 0L);
        taskID5 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
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
        taskID3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                player.sendTitle("§3§lTeleport", "§8> §e2 §8<");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

            }
        }, 60L);
        taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                player.sendTitle("§3§lTeleport", "§8> §a1 §8<");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            }
        }, 80L);
        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                player.sendMessage("§8> §fYou are being teleported to the §3Arena§f.");
                player.sendTitle("", "");

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

                LocationUtils.spawn.setPitch(0);
                LocationUtils.spawn.setYaw(180);

                player.teleport(LocationUtils.arena);
                moving.remove(player);
            }
        }, 100L);
        return true;
    }
}
