package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.commands.HomeCommand;
import me.xtimdevx.thomsehsmp.commands.SpawnCommand;
import me.xtimdevx.thomsehsmp.commands.TpaCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
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

    public int playersSleeping5;

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        Player player = (Player) event.getPlayer();
        if(Bukkit.getServer().getOnlinePlayers().size() < 5) {
            return;
        }

        int taskID = -1;

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if(!event.getPlayer().isSleeping()) {
                    return;
                }

                if(playersSleeping5 > 5) {
                    return;
                }

                playersSleeping5++;

                Bukkit.broadcastMessage("§8> §3" + playersSleeping5 + "§8/§35 §fspelers liggen in bed om te slapen.");

                if(playersSleeping5 == 5) {
                    Bukkit.getServer().getWorld(player.getWorld().getName()).setTime(0);

                    Bukkit.broadcastMessage("§8> §35 §fspelers lagen in bed, de nacht is overgeslagen.");
                }
            }
        }, 101);
    }

    @EventHandler
    public void onWake(PlayerBedLeaveEvent event) {
        Player player = (Player) event.getPlayer();

        playersSleeping5--;
    }
}
