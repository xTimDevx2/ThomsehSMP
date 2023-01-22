package me.xtimdevx.thomsehsmp.crates;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Random;

public class CratesEvents implements Listener {

    private int taskID = -1;

    public Location questcrate = new Location(Bukkit.getWorld("SMP"), 35.5, 69, -116.5);
    
    private final CratesManager cratesManager = new CratesManager();
    public static boolean openingDaily = false;

    @EventHandler
    public void rightClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTitle().equalsIgnoreCase("§3§nCrate Rewards:")) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onRightclick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        User user = User.get(player);

        Action action = event.getAction();

        if(action.equals(Action.LEFT_CLICK_BLOCK)) {
            Block block = event.getClickedBlock();
            if (block.getLocation().getBlockX() == questcrate.getBlockX() && block.getLocation().getBlockZ() == questcrate.getBlockZ() && block.getLocation().getBlockY() == questcrate.getBlockY()) {
                cratesManager.openCratemenu(player);
                event.setCancelled(true);
            }

        }

        if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = event.getClickedBlock();

            if(block.getLocation().getBlockX() == questcrate.getBlockX() && block.getLocation().getBlockZ() == questcrate.getBlockZ() && block.getLocation().getBlockY() == questcrate.getBlockY()) {
                event.setCancelled(true);
                if(openingDaily) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cSomeone is already using this crate, wait until they are done.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cIemand is deze crate al aan het gebruiken, wacht even tot ze klaar zijn.");
                    }
                    return;
                }
                if(player.getItemInHand().getType().equals(Material.AIR)) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§3§lCrates §8> §fYou need to use a §3§lDaily §fcrate key to open this crate.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§3§lCrates §8> §fJe moet een §3Daily §fcrate key gebruiken om dit te openen.");
                    }
                    return;
                }
                if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8> §6§lDaily Crate Key §8<")){
                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                    openingDaily = true;

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            chestloc.setY(chestloc.getY() + 4);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1 ,1);
                            createHelix(player);

                            cratesManager.placeCarpet("Red");

                            CratesManager.spawnRedstone(chestloc);

                        }
                    }, 20L);
                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            chestloc.setY(chestloc.getY() + 3);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1 ,1);
                            createHelix(player);

                            cratesManager.placeCarpet("Green");

                            CratesManager.spawnRedstone(chestloc);

                        }
                    }, 30L);
                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            chestloc.setY(chestloc.getY() + 2);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1 ,1);
                            createHelix(player);


                            cratesManager.placeCarpet("Red");

                            CratesManager.spawnRedstone(chestloc);

                        }
                    }, 40L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            chestloc.setY(chestloc.getY() + 1);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1 ,1);
                            createHelix(player);


                            cratesManager.placeCarpet("Green");

                            CratesManager.spawnRedstone(chestloc);

                        }
                    }, 50L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            chestloc.setY(chestloc.getY());

                            CratesManager.spawnRedstone(chestloc);
                            player.playSound(player, Sound.BLOCK_CHEST_OPEN, 1 ,1);
                            cratesManager.openCrate(CratesManager.CrateType.QUEST, player);
                            if(user.getFile().get("stats.cratesopened") == null) {
                                user.getFile().set("stats.cratesopened", 1);
                                user.saveFile();
                            }else {
                                user.getFile().set("stats.cratesopened", user.getFile().getInt("stats.cratesopened") + 1);
                                user.saveFile();
                            }
                            createHelix(player);


                            cratesManager.placeCarpet("Red");

                        }
                    }, 60L);
                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            cratesManager.removeCarpet();

                            openingDaily = false;
                        }
                    }, 80L);

                }else {

                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§3§lCrates §8> §fYou need to use a §3§lDaily §fcrate key to open this crate.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§3§lCrates §8> §fJe moet een §3Daily §fcrate key gebruiken om dit te openen.");
                    }
                }
                return;
            }
        }
    }

    public void createHelix(Player player) {
        Location loc = questcrate;
        int radius = 1;
        for(double y = 0; y <= 16; y+=0.05) {
            double x = radius * Math.cos(y);
            double z = radius * Math.sin(y);
            for(Player online : Bukkit.getOnlinePlayers()) {
                online.spawnParticle(randParticle(), (float) (loc.getX() + x), (float) (loc.getY() + y), (float) (loc.getZ() + z), 0, 0, 0, 0, 1);
            }
        }
    }

    public Particle randParticle() {
        Random r = new Random();
        int rNumb = r.nextInt(5) + 1;
        if(rNumb == 1)
            return Particle.FIREWORKS_SPARK;
        else if(rNumb == 2)
            return Particle.VILLAGER_HAPPY;
        else if(rNumb == 3)
            return Particle.SPELL_WITCH;
        else if(rNumb == 4)
            return Particle.FLAME;
        else if(rNumb == 5) {
            return Particle.ASH;
        }
        return null;
    }
}
