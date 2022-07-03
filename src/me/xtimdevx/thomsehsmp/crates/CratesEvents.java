package me.xtimdevx.thomsehsmp.crates;

import me.xtimdevx.thomsehsmp.Main;
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

public class CratesEvents implements Listener {

    private int taskID = -1;

    public Location questcrate = new Location(Bukkit.getWorld("world"), -68.5 ,85, -116.5);
    
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
                    player.sendMessage("§cSomeone is already opening a crate, please wait until they are done.");
                    return;
                }
                if(player.getItemInHand().getType().equals(Material.AIR)) {
                    player.sendMessage("§3§lCrates §8> §fYou have to use the §3Daily §fcrate key to open this crate.");
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

                            CratesManager.spawnRedstone(chestloc);

                        }
                    }, 20L);
                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            chestloc.setY(chestloc.getY() + 3);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1 ,1);


                            CratesManager.spawnRedstone(chestloc);

                        }
                    }, 30L);
                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            chestloc.setY(chestloc.getY() + 2);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1 ,1);

                            CratesManager.spawnRedstone(chestloc);

                        }
                    }, 40L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();
                            chestloc.setY(chestloc.getY() + 1);
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1 ,1);

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

                        }
                    }, 60L);
                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Location chestloc = block.getLocation();


                            openingDaily = false;
                        }
                    }, 80L);

                }else {
                    player.sendMessage("§3§lCrates §8> §fYou have to use the §3Daily §fcrate key to open this crate.");
                }
                return;
            }
        }
    }
}
