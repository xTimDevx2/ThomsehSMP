package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.commands.*;
import me.xtimdevx.thomsehsmp.crates.CratesManager;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;

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
            if(DuelCommand.duel.contains(reciever)) {
                if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDamageByArrow(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow) {
            Projectile arrow = (Projectile) event.getDamager();
            arrow.getShooter();
            if(arrow.getShooter() instanceof Player && event.getEntity() instanceof Player) {
                if (!DuelCommand.duel.contains(arrow.getShooter())) {
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
                Player damager = (Player) arrow.getShooter();
                if (DuelCommand.duel.contains(damager) && DuelCommand.duel.contains(reciever)) {
                    if(arrow.getShooter() instanceof Player) {

                        User Duser = User.get(damager);
                        User Ruser = User.get(reciever);
                        if (Duser.getFile().get("DuelTarget").equals(reciever.getName()) && Ruser.getFile().get("DuelTarget").equals(damager.getName())) {
                            event.setCancelled(false);
                            if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {
                                if(Duser.getFile().get("stats.duelwins") == null) {
                                    Duser.getFile().set("stats.duelwins", 1);
                                    Duser.saveFile();
                                }else {
                                    Duser.getFile().set("stats.duelwins", Duser.getFile().getInt("stats.duelwins") + 1);
                                    Duser.saveFile();
                                }
                                if (Duser.getLanguage().equalsIgnoreCase( "ENGLISH")) {
                                    damager.sendMessage("§8§m----------------------------------------------------");
                                    MessageUtils.sendCenteredMessage(damager, "§3§lDuel ended");
                                    MessageUtils.sendCenteredMessage(damager, "§a§lYou Won!");
                                    damager.sendMessage(MessageUtils.format("Your HP: §c§o" + Math.round(damager.getHealth()) + "#810000 ❤"));
                                    damager.sendMessage("§8§m----------------------------------------------------");

                                }
                                if (Duser.getLanguage().equalsIgnoreCase("DUTCH")) {
                                    damager.sendMessage("§8§m----------------------------------------------------");
                                    MessageUtils.sendCenteredMessage(damager, "§3§lDuel beëindigd!");
                                    MessageUtils.sendCenteredMessage(damager, "§a§lU heeft gewonnen!");
                                    damager.sendMessage(MessageUtils.format("Jou HP: §c§o" + Math.round(damager.getHealth()) + "#810000 ❤"));
                                    damager.sendMessage("§8§m----------------------------------------------------");
                                }

                                if (Ruser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                    reciever.sendMessage("§8§m----------------------------------------------------");
                                    MessageUtils.sendCenteredMessage(reciever, "§3§lDuel ended");
                                    MessageUtils.sendCenteredMessage(reciever, "§c§lYou Lost!");
                                    reciever.sendMessage(MessageUtils.format(damager.getName() + "'s HP: §c§o" + Math.round(damager.getHealth()) + "#810000 ❤"));
                                    reciever.sendMessage("§8§m----------------------------------------------------");

                                }
                                if (Ruser.getLanguage().equalsIgnoreCase("DUTCH")) {
                                    reciever.sendMessage("§8§m----------------------------------------------------");
                                    MessageUtils.sendCenteredMessage(reciever, "§3§lDuel beëindigd!");
                                    MessageUtils.sendCenteredMessage(reciever, "§c§lU bent verloren!");
                                    reciever.sendMessage(MessageUtils.format(damager.getName() + "'s HP: §c§o" + Math.round(damager.getHealth()) + "#810000 ❤"));
                                    reciever.sendMessage("§8§m----------------------------------------------------");
                                }

                                reciever.setHealth(20);

                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                                    @Override
                                    public void run() {
                                        damager.setHealth(20);
                                        damager.setFoodLevel(20);
                                        damager.setFireTicks(0);
                                    }
                                }, 20);

                                Bukkit.getScheduler().cancelTask(DuelCommand.taskID3);
                                DuelCommand.duel.remove(damager);
                                DuelCommand.duel.remove(reciever);
                                DuelCommand.duelInvite.remove(reciever);
                                DuelCommand.duelInvite.remove(damager);
                                Duser.getFile().set("DuelTarget", null);
                                Ruser.getFile().set("DuelTarget", null);
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
                Player damager = (Player) event.getDamager();
                Player reciever = (Player) event.getEntity();
                if (DuelCommand.duel.contains(damager) && DuelCommand.duel.contains(reciever)) {
                    User Duser = User.get(damager);
                    User Ruser = User.get(reciever);
                    if (Duser.getFile().get("DuelTarget").equals(reciever.getName()) && Ruser.getFile().get("DuelTarget").equals(damager.getName())) {
                        event.setCancelled(false);
                        if (((reciever.getHealth() - event.getFinalDamage()) <= 0)) {

                            if (Duser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                damager.sendMessage("§8§m----------------------------------------------------");
                                MessageUtils.sendCenteredMessage(damager, "§3§lDuel ended");
                                MessageUtils.sendCenteredMessage(damager, "§a§lYou Won!");
                                damager.sendMessage(MessageUtils.format("Your HP: §c§o" + Math.round(damager.getHealth()) + "#810000 ❤"));
                                damager.sendMessage("§8§m----------------------------------------------------");

                            }
                            if (Duser.getLanguage().equalsIgnoreCase("DUTCH")) {
                                damager.sendMessage("§8§m----------------------------------------------------");
                                MessageUtils.sendCenteredMessage(damager, "§3§lDuel beëindigd!");
                                MessageUtils.sendCenteredMessage(damager, "§a§lU heeft gewonnen!");
                                damager.sendMessage(MessageUtils.format("Jou HP: §c§o" + Math.round(damager.getHealth()) + "#810000 ❤"));
                                damager.sendMessage("§8§m----------------------------------------------------");
                            }

                            if (Ruser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                reciever.sendMessage("§8§m----------------------------------------------------");
                                MessageUtils.sendCenteredMessage(reciever, "§3§lDuel ended");
                                MessageUtils.sendCenteredMessage(reciever, "§c§lYou Lost!");
                                reciever.sendMessage(MessageUtils.format(damager.getName() + "'s HP: §c§o" + Math.round(damager.getHealth()) + "#810000 ❤"));
                                reciever.sendMessage("§8§m----------------------------------------------------");

                            }
                            if (Ruser.getLanguage().equalsIgnoreCase("DUTCH")) {
                                reciever.sendMessage("§8§m----------------------------------------------------");
                                MessageUtils.sendCenteredMessage(reciever, "§3§lDuel beëindigd!");
                                MessageUtils.sendCenteredMessage(reciever, "§c§lU bent verloren!");
                                reciever.sendMessage(MessageUtils.format(damager.getName() + "'s HP: §c§o" + Math.round(damager.getHealth()) + "#810000 ❤"));
                                reciever.sendMessage("§8§m----------------------------------------------------");
                            }

                            reciever.setHealth(20);

                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    damager.setHealth(20);
                                }
                            }, 20);

                            Bukkit.getScheduler().cancelTask(DuelCommand.taskID3);
                            DuelCommand.duel.remove(damager);
                            DuelCommand.duel.remove(reciever);
                            DuelCommand.duelInvite.remove(reciever);
                            DuelCommand.duelInvite.remove(damager);
                            Duser.getFile().set("DuelTarget", null);
                            Ruser.getFile().set("DuelTarget", null);
                        }
                    } else {
                        event.setCancelled(true);
                    }
                }else {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        Player player = (Player) event.getPlayer();
        User user = User.get(player);

        if(event.getItem().getType() == Material.GOLDEN_CARROT) {
            if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8> §6§lDaily Crate Key §8<")) {
                event.setCancelled(true);
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cMeant for playing, not for eating! (/crates)");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cBedoelt om mee te spelen, niet om op te eten! (/crates)");
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

        if(user.getFile().get("stats.blocksplaced") == null) {
            user.getFile().set("stats.blocksplaced", 0);
            user.saveFile();
        }

        user.getFile().set("stats.blocksplaced", user.getFile().getInt("stats.blocksplaced") + 1);
        user.saveFile();
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
