package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpawnCommand implements CommandExecutor {

    public static int taskID = -1;

    public static ArrayList<Player> moving = new ArrayList<>();


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage("§8> §fTeleporting you in §35 §fseconds. Do not move!");
        moving.add(player);
        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                player.sendMessage("§8> §fTeleporting to §3Spawn§f.");
                player.teleport(LocationUtils.spawn);
                moving.remove(player);
            }
        }, 100L);
        return true;
    }
}
