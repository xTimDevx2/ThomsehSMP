package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
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
        User user = User.get(player);


        int timeLeft = cooldownManager.getCooldown(player.getUniqueId());

        if(timeLeft == 0){
            if(player.getWorld().getName().equalsIgnoreCase("SMP")) {
                List<Location> loc = LocationUtils.getScatterLocations(Bukkit.getWorld("SMP"), 1500, false);
                Location rtp = loc.get(0);


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

                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§2§lOverworld"); // "Overworld" stays the same in English
                MessageUtils.sendCenteredMessage(player, "§3§lWe will send you to a random location...");
                MessageUtils.sendCenteredMessage(player, "§fX: " + rtp.getBlockX() + " Y: " + rtp.getBlockY() + " Z: " + rtp.getBlockZ());
                MessageUtils.sendCenteredMessage(player, "§fBiome: §3§o" + NameUtils.getBiomeName(player.getWorld().getBiome(rtp)));
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
            }
            if(player.getWorld().getName().equalsIgnoreCase("SMP_nether")) {
                List<Location> loc = LocationUtils.getScatterLocations(Bukkit.getWorld("SMP_nether"), 500, true);
                Location rtp = loc.get(0);


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


                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§c§lNether");
                MessageUtils.sendCenteredMessage(player, "§3§lWe will send you to a random location...");
                MessageUtils.sendCenteredMessage(player, "§fX: " + rtp.getBlockX() + " Y: " + rtp.getBlockY() + " Z: " + rtp.getBlockZ());
                MessageUtils.sendCenteredMessage(player, "§fBiome: §3§o" + NameUtils.getBiomeName(player.getWorld().getBiome(rtp)));
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
            }
        }else{
            //Hasn't expired yet, shows how many seconds left until it does
            player.sendMessage("§8> §fPlease wait another §3" + timeLeft + " §fseconds before using rtp again.");
        }

        return true;
    }
}
