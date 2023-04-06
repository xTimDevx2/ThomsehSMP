package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.commands.*;
import me.xtimdevx.thomsehsmp.crates.CratesManager;
import me.xtimdevx.thomsehsmp.managers.DuelsManager;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        User user = User.get(player);
        if(user.getFile().getBoolean("tutorial")) {
            if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
                player.teleport(event.getFrom());
            }
        }
        if(SpawnCommand.moving.contains(player)) {
            if (event.getFrom().getBlockX() != event.getTo().getBlockX() || event.getFrom().getBlockZ() != event.getTo().getBlockZ()){
                Bukkit.getScheduler().cancelTask(HomeCommand.taskID);
                Bukkit.getScheduler().cancelTask(HomeCommand.taskID2);
                Bukkit.getScheduler().cancelTask(HomeCommand.taskID3);
                Bukkit.getScheduler().cancelTask(HomeCommand.taskID4);
                Bukkit.getScheduler().cancelTask(HomeCommand.taskID5);
                Bukkit.getScheduler().cancelTask(HomeCommand.taskID6);
                Bukkit.getScheduler().cancelTask(SpawnCommand.taskID);
                Bukkit.getScheduler().cancelTask(SpawnCommand.taskID2);
                Bukkit.getScheduler().cancelTask(SpawnCommand.taskID3);
                Bukkit.getScheduler().cancelTask(SpawnCommand.taskID4);
                Bukkit.getScheduler().cancelTask(SpawnCommand.taskID5);
                Bukkit.getScheduler().cancelTask(SpawnCommand.taskID6);
                Bukkit.getScheduler().cancelTask(TpaCommand.taskIDtp);
                Bukkit.getScheduler().cancelTask(TpaCommand.taskID2);
                Bukkit.getScheduler().cancelTask(TpaCommand.taskID3);
                Bukkit.getScheduler().cancelTask(TpaCommand.taskID4);
                Bukkit.getScheduler().cancelTask(TpaCommand.taskID5);
                Bukkit.getScheduler().cancelTask(TpaCommand.taskID6);
                Bukkit.getScheduler().cancelTask(BackCommand.taskID);
                Bukkit.getScheduler().cancelTask(BackCommand.taskID2);
                Bukkit.getScheduler().cancelTask(BackCommand.taskID3);
                Bukkit.getScheduler().cancelTask(BackCommand.taskID4);
                Bukkit.getScheduler().cancelTask(BackCommand.taskID5);
                Bukkit.getScheduler().cancelTask(BackCommand.taskID6);
                Bukkit.getScheduler().cancelTask(CratesCommand.taskID);
                Bukkit.getScheduler().cancelTask(CratesCommand.taskID2);
                Bukkit.getScheduler().cancelTask(CratesCommand.taskID3);
                Bukkit.getScheduler().cancelTask(CratesCommand.taskID4);
                Bukkit.getScheduler().cancelTask(CratesCommand.taskID5);
                Bukkit.getScheduler().cancelTask(CratesCommand.taskID6);
                Bukkit.getScheduler().cancelTask(ArenaCommand.taskID);
                Bukkit.getScheduler().cancelTask(ArenaCommand.taskID2);
                Bukkit.getScheduler().cancelTask(ArenaCommand.taskID3);
                Bukkit.getScheduler().cancelTask(ArenaCommand.taskID4);
                Bukkit.getScheduler().cancelTask(ArenaCommand.taskID5);
                Bukkit.getScheduler().cancelTask(ArenaCommand.taskID6);
                player.sendTitle("", "");
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fYou are not allowed to move while teleporting.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fU mag niet bewegen tijdens een teleport.");
                }
                SpawnCommand.moving.remove(player);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player reciever = (Player) event.getEntity();
            User user = User.get(reciever);
            if(DuelsManager.duel.contains(reciever)) {
                if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setCancelled(true);
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.FIRE) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.MAGIC) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.WITHER) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.THORNS) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
                if(event.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
                    if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void interactEntityEvent(PlayerInteractEntityEvent e) {
        if(e.getRightClicked()  instanceof ItemFrame) {
        Player player = (Player) e.getPlayer();
        User user = User.get(player);
            if (user.getFile().get("DuelTarget") != null) {
                e.setCancelled(true);
            }
        }
        if(e.getRightClicked() instanceof Minecart) {
            Player player = (Player) e.getPlayer();
            User user = User.get(player);
            if (user.getFile().get("DuelTarget") != null) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamageByArrow(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow || event.getDamager() instanceof Trident) {
            Projectile arrow = (Projectile) event.getDamager();
            arrow.getShooter();
            if(arrow.getShooter() instanceof Player && event.getEntity() instanceof Player) {
                if (!DuelsManager.duel.contains(arrow.getShooter())) {
                    if(!DuelsManager.duel.contains(((Player) event.getEntity()).getPlayer())) {
                        event.setCancelled(true);
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onMobKills(EntityDeathEvent event) {
        if(!(event.getEntity() instanceof Player)) {
            if(event.getEntity().getKiller() != null) {
                Player killer = (Player) event.getEntity().getKiller();

                User user = User.get(killer);

                if(user.getFile().get("stats.mobkills") == null) {
                    user.getFile().set("stats.mobkills", 0);
                    user.saveFile();
                }
                user.getFile().set("stats.mobkills", user.getFile().getInt("stats.mobkills") + 1);
                user.saveFile();
             }
        }

        if(event.getEntity() instanceof Wither && event.getEntity().getKiller() != null) {
            Player killer = (Player) event.getEntity().getKiller();

            User user = User.get(killer);

            if (user.getFile().get("stats.witherkills") == null) {
                user.getFile().set("stats.witherkills", 1);
                user.saveFile();
            } else {
                user.getFile().set("stats.witherkills", user.getFile().getInt("stats.witherkills") + 1);
                user.saveFile();
            }
        }
    }

    @EventHandler
    public void  onDamageByPlayer(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if(event.getDamager() instanceof Arrow) {
                Projectile arrow = (Projectile) event.getDamager();
                Player reciever = (Player) event.getEntity();
                if(!((arrow.getShooter() instanceof Player))) {
                    if(DuelsManager.duel.contains(reciever)) {
                        event.setCancelled(true);
                    }
                    return;
                }
                Player damager = (Player) arrow.getShooter();
                if (DuelsManager.duel.contains(damager) && DuelsManager.duel.contains(reciever)) {
                    if(arrow.getShooter() instanceof Player) {

                        User Duser = User.get(damager);
                        User Ruser = User.get(reciever);
                        if (Duser.getFile().get("DuelTarget").equals(reciever.getName()) && Ruser.getFile().get("DuelTarget").equals(damager.getName())) {
                            event.setCancelled(false);
                            if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                                DuelsManager duelsManager = new DuelsManager();

                                duelsManager.endDuel(damager, reciever, Duser.getFile().getString("duelmode"), false, false);
                                return;
                            }
                        } else {
                            event.setCancelled(true);
                        }
                    }else {
                        event.setCancelled(true);
                    }
                    return;
                }
            }
            if (event.getDamager() instanceof Player) {
                Player reciever = (Player) event.getEntity();
                if(!DuelsManager.duel.contains(reciever)) {
                    event.setCancelled(true);
                    return;
                }
                    Player damager = (Player) event.getDamager();
                if (DuelsManager.duel.contains(damager) && DuelsManager.duel.contains(reciever)) {
                    User Duser = User.get(damager);
                    User Ruser = User.get(reciever);
                    if (Duser.getFile().get("DuelTarget").equals(reciever.getName()) && Ruser.getFile().get("DuelTarget").equals(damager.getName())) {
                        event.setCancelled(false);
                        if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {

                            DuelsManager duelsManager = new DuelsManager();

                            duelsManager.endDuel(damager, reciever, Duser.getFile().getString("duelmode"), false, false);
                        }
                    } else {
                        event.setCancelled(true);
                    }
                }else {
                    event.setCancelled(true);
                }
            }
            if(DuelsManager.duel.contains(event.getEntity())) {
                if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    if(event.getDamager() instanceof Player) {
                        event.setCancelled(false);
                    }else {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        Player player = (Player) event.getPlayer();
        User user = User.get(player);

        ItemStack item = event.getItem();

        if(event.getItem().getType() == Material.GOLDEN_CARROT) {
            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8> §6§lDaily Crate Key §8<")) {
                event.setCancelled(true);
                    player.sendMessage("§cMeant for playing, not for eating! (/crates)");
            }
        }
        if (item.getType() == Material.GOLDEN_APPLE) {
            if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§6Mini Golden Apple")) {
                event.setCancelled(true);
                if(player.getInventory().getItemInMainHand().getAmount() == 1) {
                    player.getInventory().getItemInMainHand().setType(Material.AIR);
                }else {
                    player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 25 * (4), 1));
            }
        }
    }

    @EventHandler
    public void regenEvent(EntityRegainHealthEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            User user = User.get(player);
            if(DuelsManager.duel.contains(player) && user.getFile().get("duelmode").equals("uhc")) {
                if(event.getRegainReason() == EntityRegainHealthEvent.RegainReason.EATING || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if(event.getPlayer().getBedSpawnLocation() != null) {
            return;
        }
        event.setRespawnLocation(LocationUtils.spawn);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();
        User user = User.get(player);

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

        if(user.getFile().get("stats.deaths") == null) {
            user.getFile().set("stats.deaths", 1);
            user.saveFile();
        }else {
            user.getFile().set("stats.deaths", user.getFile().getInt("stats.deaths") + 1);
            user.saveFile();
        }

        if(user.getFile().getBoolean("settings.deathcounter")) {
            event.setDeathMessage("§8> §f" + event.getDeathMessage().replace(player.getName(), "§3§o" + player.getName() + "§f") + " §7§o(#" + user.getFile().getInt("stats.deaths") + ")");
        }else {
            event.setDeathMessage("§8> §f" + event.getDeathMessage().replace(player.getName(), "§3§o" + player.getName() + "§f"));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        User user = User.get(player);

        if (user.getFile().get("stats.blocksplaced") == null) {
            user.getFile().set("stats.blocksplaced", 0);
            user.saveFile();
        }

        user.getFile().set("stats.blocksplaced", user.getFile().getInt("stats.blocksplaced") + 1);
        user.saveFile();

        if (DuelsManager.duel.contains(player)) {
            if(user.getFile().get("duelmode").equals("uhc")) {
                if(event.getBlockPlaced().getType() != Material.COBBLESTONE) {
                    event.setCancelled(true);
                    return;
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if(event.getBlockPlaced().getType() == Material.COBBLESTONE) {
                            event.getBlockPlaced().setType(Material.AIR);
                        }
                    }
                }, 200);
                return;
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        User user = User.get(player);

        if(user.getFile().get("stats.blocksbroken") == null) {
            user.getFile().set("stats.blocksbroken", 0);
            user.saveFile();
        }

        user.getFile().set("stats.blocksbroken", user.getFile().getInt("stats.blocksbroken") + 1);
        user.saveFile();

        if(event.getBlock().getType() == Material.DIAMOND_ORE) {
            if(user.getFile().get("stats.diamondmined") == null) {
                user.getFile().set("stats.diamondmined", 0);
                user.saveFile();
            }

            user.getFile().set("stats.diamondmined", user.getFile().getInt("stats.diamondmined") + 1);
            user.saveFile();
        }
    }

}
