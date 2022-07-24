package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.managers.WorldManager;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!sender.hasPermission("smp.command.world")) {
            sender.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§3§lWorld Commands");
            sender.sendMessage("§8> §f/world create §8-§f Create a world.");
            sender.sendMessage("§8> §f/world delete §8-§f Delete a world.");
            sender.sendMessage("§8> §f/world load §8-§f Load a world.");
            sender.sendMessage("§8> §f/world unload §8-§f Unload a world.");
            sender.sendMessage("§8> §f/world list §8-§f List all worlds.");
            sender.sendMessage("§8> §f/world tp §8-§f Teleport to a world.");
            return true;
        }

        WorldManager manager = WorldManager.getInstance();

        if (args[0].equalsIgnoreCase("create")) {
            if (args.length < 7) {
                sender.sendMessage("§cUsage: /world create <worldname> <radius> <seed> <worldtype> <nether> <end>");
                return true;
            }

            String worldname;
            int radius;
            long seed;
            WorldType worldtype;
            boolean nether;
            boolean end;

            worldname = args[1];

            try {
                radius = Integer.parseInt(args[2]);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + args[2] + " is not a vaild radius.");
                return true;
            }

            try {
                seed = Long.parseLong(args[3]);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + args[3] + " is not a vaild seed.");
                return true;
            }

            try {
                worldtype = WorldType.valueOf(args[4].toUpperCase());
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + args[4] + " is not an vaild worldtype.");
                return true;
            }

            if (args[5].equalsIgnoreCase("true")) {
                nether = true;
            } else if (args[5].equalsIgnoreCase("false")) {
                nether = false;
            } else {
                sender.sendMessage(ChatColor.RED + "Nether can only be true or false.");
                return true;
            }

            if (args[6].equalsIgnoreCase("true")) {
                end = true;
            } else if (args[6].equalsIgnoreCase("false")) {
                end = false;
            } else {
                sender.sendMessage(ChatColor.RED + "End can only be true or false.");
                return true;
            }
            Bukkit.broadcastMessage("§3§lCREATING NEW WORLD");
            Bukkit.broadcastMessage("§fCreating a new world called " + worldname + ".");
            Bukkit.broadcastMessage("§fNether is " + nether + ".");
            Bukkit.broadcastMessage("§fEnd is " + end + ".");
            Bukkit.broadcastMessage("§fThe map radius is " + radius + ".");
            Bukkit.broadcastMessage("§fWe are using the seed " + seed + ".");
            Bukkit.broadcastMessage("§fThe world type is " + worldtype + ".");
            Bukkit.broadcastMessage("§c§lDO NOT MOVE");

            manager.createWorld(worldname, radius, seed, World.Environment.NORMAL, worldtype);

            if (nether) {
                manager.createWorld(worldname + "_nether", radius, seed, World.Environment.NETHER, worldtype);
            }

            if (end) {
                manager.createWorld(worldname + "_end", radius, seed, World.Environment.THE_END, worldtype);
            }

            Bukkit.broadcastMessage("§8> §fCreating world §3" + worldname + " §fcompleted.");
            return true;
        }

        if (args[0].equalsIgnoreCase("delete")) {
            if (args.length == 1) {
                sender.sendMessage("§cUsage: /world delete <world>");
                return true;
            }

            World world = Bukkit.getServer().getWorld(args[1]);

            if (world == null) {
                sender.sendMessage(ChatColor.RED + args[1] + " does not exist.");
                return true;
            }

            if (world == Bukkit.getWorlds().get(0)) {
                sender.sendMessage(ChatColor.RED + "You cannot delete the main world.");
                return true;
            }

            if (manager.deleteWorld(world)) {
                sender.sendMessage("§8> §fDeleted world " + world.getName() + ".");
            } else {
                sender.sendMessage("§cCouldn't delete " + world.getName() + ".");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("tp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can teleport to worlds.");
                return true;
            }

            if (args.length == 1) {
                player.sendMessage("§cUsage: /world tp <world>");
                return true;
            }

            World world = Bukkit.getServer().getWorld(args[1]);

            if (world == null) {
                player.sendMessage(ChatColor.RED + args[1] + " does not exist.");
                return true;
            }

            player.sendMessage("§8> §fTeleporting you to world " + world.getName() + ".");
            player.teleport(world.getSpawnLocation());
            return true;
        }

        if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage("§3World §lList§8:");

            for (World world : Bukkit.getWorlds()) {
                ChatColor color;

                switch (world.getEnvironment()) {
                    case NETHER:
                        color = ChatColor.RED;
                        break;
                    case NORMAL:
                        color = ChatColor.GREEN;
                        break;
                    case THE_END:
                        color = ChatColor.AQUA;
                        break;
                    default:
                        return true;
                }

                sender.sendMessage("§8> §f" + world.getName() + " §8- " + color + world.getEnvironment().name());
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("load")) {
            if (args.length == 1) {
                sender.sendMessage("§cUsage: /world unload <world>");
                return true;
            }

            World world = Bukkit.getServer().getWorld(args[1]);

            if (world != null) {
                sender.sendMessage(ChatColor.RED + args[1] + " is already loaded.");
                return true;
            }

            try {
                manager.loadWorld(args[1]);
                sender.sendMessage("§8> §fLoaded world " + args[1] + ".");
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "This world doesn't exist.");
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("unload")) {
            if (args.length == 1) {
                sender.sendMessage("§cUsage: /world unload <world>");
                return true;
            }

            World world = Bukkit.getServer().getWorld(args[1]);

            if (world == null) {
                sender.sendMessage(ChatColor.RED + args[1] + " does not exist.");
                return true;
            }

            if (world == Bukkit.getWorlds().get(0)) {
                sender.sendMessage(ChatColor.RED + "You cannot unload the main world.");
                return true;
            }

            sender.sendMessage("§8> §fUnloaded world " + world.getName() + ".");
            manager.unloadWorld(world);
            return true;
        }
        return true;
    }
}
