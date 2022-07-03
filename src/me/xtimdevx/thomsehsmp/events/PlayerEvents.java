package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.commands.HomeCommand;
import me.xtimdevx.thomsehsmp.commands.SpawnCommand;
import me.xtimdevx.thomsehsmp.commands.TpaCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(SpawnCommand.moving.contains(player)) {
            if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()){
                Bukkit.getScheduler().cancelTask(HomeCommand.taskID);
                Bukkit.getScheduler().cancelTask(SpawnCommand.taskID);
                Bukkit.getScheduler().cancelTask(TpaCommand.taskIDtp);
                player.sendMessage("§8> §fTeleport canceled.");
                SpawnCommand.moving.remove(player);
            }
        }
    }
}
