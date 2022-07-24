package me.xtimdevx.thomsehsmp.events;

import jdk.jshell.execution.Util;
import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.commands.HomeCommand;
import me.xtimdevx.thomsehsmp.commands.SethomeCommand;
import me.xtimdevx.thomsehsmp.managers.ScoreboardManager;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

public class ConnectionEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        User user = User.get(player);
        user.getFile().set("username", player.getName());
        user.getFile().set("uuid", player.getUniqueId().toString());
        user.getFile().set("ip", player.getAddress().getAddress().getHostAddress());
        user.getFile().set("tparequest", "none");
        user.getFile().set("tpaincoming", "none");

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Date date = new Date();

        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
                ScoreboardManager.createMainBoard(player);

                for(Player online : Bukkit.getOnlinePlayers()) {
                    ScoreboardManager.updateScoreBoard(online);
                }
            }
        }, 20);

        user.getFile().set("lastlogin", date.getTime());
        user.saveFile();

        if(user.getFile().getBoolean("new") == true) {
            Bukkit.broadcastMessage("§8§m----------------------------------------------------");
            MessageUtils.broadcastCenteredMessage("§3§lNEW Player!");
            Bukkit.broadcastMessage(" ");
            MessageUtils.broadcastCenteredMessage("§f" + player.getName());
            MessageUtils.broadcastCenteredMessage("§fWelkom bij Origami SMP!");
            Bukkit.broadcastMessage("§8§m----------------------------------------------------");
            event.setJoinMessage(null);

            //
            Utils.clearChat(player);
            int taskID = -1;

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    Utils.clearChat(player);

                }
            },20L);

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendMessage("§3§lWelkom bij Origami SMP!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1726272000, 128));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1726272000, 128));
                }
            },40L);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendMessage(" ");
                    player.sendMessage("§fDit is een §3§oSurvival Multiplayer Server");
                    player.sendMessage("§fGe-Owned door twitch streamer §3Thomseh");
                    player.sendMessage("§fGemanaged en geprogrameerd door §3Team Scyle§f.");
                }
            },100L);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendMessage(" ");
                    player.sendMessage("§fGebruik §3§o/help §fvoor onze custom guide.");
                    player.sendMessage("§fIedereen krijgt 3 homes om te beginnen");
                    player.sendMessage("§fJe kan §3§o/rtp §fgebruiken om op een random locatie te komen.");
                }
            },160L);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendMessage(" ");
                    player.sendMessage("§fDoe ook zeker elke dag §3§o/reward §fvoor je gratis crate.");
                    player.sendMessage("§fKijk ook zeker in de §3§o/rules §fvoor je begint met spelen.");
                    player.sendMessage("§fVeel plezier en success!");
                }
            },220L);

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    user.getFile().set("new", false);
                    user.saveFile();
                    player.teleport(LocationUtils.spawn);
                    player.getInventory().addItem(new ItemStack(Material.BREAD, 5));
                    player.getInventory().addItem(new ItemStack(Material.GOLDEN_SHOVEL));
                    player.removePotionEffect(PotionEffectType.SLOW);
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                }
            },280L);

            return;
        }

        event.setJoinMessage("§a§l✔ §f" + player.getName() + " has joined.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = User.get(player);

        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
                for(Player online : Bukkit.getOnlinePlayers()) {
                    ScoreboardManager.updateScoreBoard(online);
                }
            }
        }, 20);

        event.setQuitMessage("§c§l❌ §f" + player.getName() + " has quit.");

    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
            if (player.isOp()) {
                event.allow();
                return;
            }

            event.setKickMessage("§8§m----------------------------- \n" +
                    "§c§lYou are not whitelisted! \n" +
                    "§fThis server has not been released yet.\n" +
                    "§fFor more updates follow Thomseh on twitch.\n" +
                    "§7§otwitch.tv/thomseh\n" +
                    "§8§m-----------------------------");

            return;
        }
    }
}
