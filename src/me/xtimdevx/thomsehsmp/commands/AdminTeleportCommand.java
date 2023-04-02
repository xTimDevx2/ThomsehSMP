package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminTeleportCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("atp")) {
            Player player = (Player) sender;

            if(!player.hasPermission("smp.command.atp")) {
                player.sendMessage(MessageUtils.NOPERM);
                return true;
            }

            if(args.length == 0) {
                player.sendMessage("§cUsage: /atp <player>");
                player.sendMessage("§cUsage: /atp <x> <y> <z>");
                return true;
            }
            if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if(target == null) {
                    player.sendMessage("§cError: This player is not online.");
                    return true;
                }

                player.setGameMode(GameMode.SPECTATOR);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        player.teleport(target.getLocation());
                        player.sendMessage("§4§lADMIN! §fAdmin teleport to §c§o" + target.getName() + " §7§o(Spectator mode)");
                    }
                }, 40L);
                return true;
            }

            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double z = Double.parseDouble(args[2]);

            player.setGameMode(GameMode.SPECTATOR);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.teleport(new Location(Bukkit.getWorld(player.getWorld().getName()), x, y, z));
                    player.sendMessage("§4§lADMIN! §fAdmin teleport to §c§ox:" + Math.round(x) + " y:" + Math.round(y) + " z:" + Math.round(z) + " §7§o(Spectator mode)");
                }
            }, 40L);

        }
        return true;
    }
}
