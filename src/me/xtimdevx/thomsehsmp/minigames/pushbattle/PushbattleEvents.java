package me.xtimdevx.thomsehsmp.minigames.pushbattle;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PushbattleEvents implements Listener {

    public ArrayList inlobby = PushbattleMain.inLobby;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(PushbattleMain.inLobby.contains(player)) {
                event.setCancelled(true);
            }

            if(PushbattleMain.inGame.contains(player)) {
                if(event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK && event.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamagebyPlayer(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();

                if(PushbattleMain.inGame.contains(player) && PushbattleMain.inGame.contains(damager)) {
                    if(PushbattleMain.teamRed.contains(player) && PushbattleMain.teamRed.contains(damager)) {
                       return;
                    }
                    if(PushbattleMain.teamBlue.contains(player) && PushbattleMain.teamBlue.contains(damager)) {
                        return;
                    }

                    event.setCancelled(false);
                    User user = User.get(player);
                    User duser = User.get(damager);

                    user.getFile().set("pb.lastdamager", damager.getName());
                    user.saveFile();

                    player.setHealth(20);
                    player.setFoodLevel(20);
                }
            }else {
                if(PushbattleMain.inGame.contains(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void walkonBlock(PlayerMoveEvent event){
        Player player = event.getPlayer();
        User user = User.get(player);
        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

        if(PushbattleMain.inGame.contains(player)) {
            if(block.getY() < 56 ) {
                if(PushbattleMain.teamRed.contains(player)) {
                    player.teleport(PushbattleMain.redSpawnMap);

                    if(user.getFile().get("pb.lastdamager") == null) {
                        PushbattleMain.broadcastPushbattle("§8> §c" + player.getName() + " §fis zelf van de map gesprongen? Oeps!", false);
                    }else {
                        Player killer = Bukkit.getPlayer(user.getFile().getString("pb.lastdamager"));
                        PushbattleMain.broadcastPushbattle("§8> §c" + player.getName() + " §fis door §9" + killer.getName() + " §fvan de map gehit!", false);

                        User ukiller = User.get(killer);
                        ukiller.getFile().set("pb.solokills", ukiller.getFile().getInt("pb.solokills") + 1);
                        ukiller.saveFile();

                        PushbattleMain.bluekills++;

                        if(PushbattleMain.tie) {
                            for(Player online : Bukkit.getOnlinePlayers()) {
                                if(PushbattleMain.inGame.contains(online)) {

                                    online.sendMessage("§8§m----------------------------------------------------");
                                    MessageUtils.sendCenteredMessage(online, PushbattleMain.color + "§lPushbattle");
                                    online.sendMessage(" ");
                                    MessageUtils.sendCenteredMessage(online, "§9§lBlauw heeft gewonnen!");
                                    MessageUtils.sendCenteredMessage(online, "§fZe wonnen deze game met " + PushbattleMain.color + PushbattleMain.bluekills + " §fkills!");
                                    online.sendMessage(" ");
                                    MessageUtils.sendCenteredMessage(online, PushbattleMain.color + "§oBedankt om te spelen!");
                                    online.sendMessage("§8§m----------------------------------------------------");
                                    online.sendMessage("§7§oJe wordt over 5 seconden terug uit het spel gezet...");

                                }
                            }
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    PushbattleMain.endGame("blue");

                                }
                            },100);
                        }

                    }

                    user.getFile().set("pb.lastdamager", null);
                    user.saveFile();
                }else {
                    PushbattleMain.blueSpawnMap.setYaw(180);
                    player.teleport(PushbattleMain.blueSpawnMap);

                    if(user.getFile().get("pb.lastdamager") == null) {
                        PushbattleMain.broadcastPushbattle("§8> §9" + player.getName() + " §fis zelf van de map gesprongen? Oeps!", false);
                    }else {
                        Player killer = Bukkit.getPlayer(user.getFile().getString("pb.lastdamager"));
                        PushbattleMain.broadcastPushbattle("§8> §9" + player.getName() + " §fis door §c" + killer.getName() + " §fvan de map gehit!", false);

                        User ukiller = User.get(killer);
                        ukiller.getFile().set("pb.solokills", ukiller.getFile().getInt("pb.solokills") + 1);
                        ukiller.saveFile();

                        PushbattleMain.redkills++;

                        if(PushbattleMain.tie) {
                            for(Player online : Bukkit.getOnlinePlayers()) {
                                if(PushbattleMain.inGame.contains(online)) {

                                    online.sendMessage("§8§m----------------------------------------------------");
                                    MessageUtils.sendCenteredMessage(online, PushbattleMain.color + "§lPushbattle");
                                    online.sendMessage(" ");
                                    MessageUtils.sendCenteredMessage(online, "§c§lRood heeft gewonnen!");
                                    MessageUtils.sendCenteredMessage(online, "§fZe wonnen deze game met " + PushbattleMain.color + PushbattleMain.redkills + " §fkills!");
                                    online.sendMessage(" ");
                                    MessageUtils.sendCenteredMessage(online, PushbattleMain.color + "§oBedankt om te spelen!");
                                    online.sendMessage("§8§m----------------------------------------------------");
                                    online.sendMessage("§7§oJe wordt over 5 seconden terug uit het spel gezet...");
                                }
                            }
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    PushbattleMain.endGame("red");

                                }
                            },100);
                        }

                    }
                    user.getFile().set("pb.lastdamager", null);
                    user.saveFile();
                }
            }
        }
    }

    @EventHandler
    public void tpEvent(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        User user = User.get(player);

        if(event.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
            if(PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = (Player) event.getPlayer();

        if(PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
            if(PushbattleMain.tutorial) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryInteractEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(MessageUtils.format("§8> " + PushbattleMain.color +"Klik op mij om uit de lobby te gaan §8<"))) {
                PushbattleMain.leaveLobby(player);
            }
        }
    }

    @EventHandler
    public void inventoryManipulateEvent(InventoryInteractEvent event) {
        Player player = (Player) event.getInventory().getViewers();
        if(PushbattleMain.inLobby.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = (Player) event.getPlayer();
        if(PushbattleMain.inLobby.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();
        Player player = (Player) event.getPlayer();
        User user = User.get(player);

        String command = message.split(" ")[0].substring(1);

        if (PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
            if (!command.equalsIgnoreCase("pushbattle") && !command.equalsIgnoreCase("pb")) {
                player.sendMessage("§cJe kan enkel pushbattle commands gebruiken wanneer je in het spel zit.");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(PushbattleMain.inLobby.contains(player)) {
            PushbattleMain.leaveLobby(player);
        }

        if(PushbattleMain.inGame.contains(player)) {
            if(PushbattleMain.teamRed.contains(player)) {
                PushbattleMain.teamRed.remove(player);
            }else {
                PushbattleMain.teamBlue.remove(player);
            }

            User user = User.get(player);
            player.teleport(user.getFile().getLocation("pblocation"));
            player.getInventory().clear();

            Utils.restoreInventory(player);
            PushbattleMain.inGame.remove(player);
            if(PushbattleMain.teamBlue.size() == 0) {
                PushbattleMain.endAbrupt("red");
            }
            if(PushbattleMain.teamRed.size() == 0) {
                PushbattleMain.endAbrupt("blue");
            }


        }
    }
    @EventHandler
    public void onSaturation(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();

        if(PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
            event.setCancelled(true);
        }
    }
}
