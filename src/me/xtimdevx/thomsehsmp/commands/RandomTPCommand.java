package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.managers.CooldownManager;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.NameUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class RandomTPCommand implements CommandExecutor {

    private final CooldownManager cooldownManager = new CooldownManager();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;


        if(!player.getWorld().getName().equalsIgnoreCase("world")) {
            player.sendMessage("§cERROR: You can only use this in the overworld.");
            return true;
        }
        int timeLeft = cooldownManager.getCooldown(player.getUniqueId());

        if(timeLeft == 0){
            List<Location> loc = LocationUtils.getScatterLocations(Bukkit.getWorld("world"), 100);
            Location rtp = loc.get(0);
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lSending you to a random location....");
            MessageUtils.sendCenteredMessage(player, "§fX: " + rtp.getBlockX() + " Y: " + rtp.getBlockY() + " Z: " + rtp.getBlockZ());
            MessageUtils.sendCenteredMessage(player, "§fBiome: §3" + NameUtils.getBiomeName(player.getWorld().getBiome(rtp)));
            player.sendMessage("§8§m----------------------------------------------------");
            player.teleport(rtp);

            cooldownManager.setCooldown(player.getUniqueId(), CooldownManager.RANDOMTP);
            new BukkitRunnable() {
                @Override
                public void run() {
                    int timeLeft = cooldownManager.getCooldown(player.getUniqueId());
                    cooldownManager.setCooldown(player.getUniqueId(), --timeLeft);
                    if(timeLeft == 0){
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.plugin, 20, 20);

        }else{
            //Hasn't expired yet, shows how many seconds left until it does
            player.sendMessage("§8> §3"+  timeLeft + " §fseconds before you can use random teleport again.");
        }

        return true;
    }
}
