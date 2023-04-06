package me.xtimdevx.thomsehsmp.events;

import jdk.jshell.execution.Util;
import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.commands.DuelCommand;
import me.xtimdevx.thomsehsmp.commands.HomeCommand;
import me.xtimdevx.thomsehsmp.commands.SethomeCommand;
import me.xtimdevx.thomsehsmp.features.Bossbar;
import me.xtimdevx.thomsehsmp.features.Tutorial;
import me.xtimdevx.thomsehsmp.managers.DuelsManager;
import me.xtimdevx.thomsehsmp.managers.ScoreboardManager;
import me.xtimdevx.thomsehsmp.utils.*;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Boss;
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

    public Bossbar bar;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        User user = User.get(player);
        user.getFile().set("username", player.getName());
        user.getFile().set("uuid", player.getUniqueId().toString());
        user.getFile().set("ip", player.getAddress().getAddress().getHostAddress());
        user.getFile().set("tparequest", "none");
        user.getFile().set("tpaincoming", "none");

        if(user.getLanguage() == null) {
            user.setLanguage(User.Languages.ENGLISH);
        }

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        PermsUtils permsUtils = new PermsUtils();


        if(user.getFile().getBoolean("tutorial")) {
            user.getFile().set("tutorial", false);
            user.saveFile();

            player.setGameMode(GameMode.SURVIVAL);
            player.teleport(LocationUtils.spawn);
            player.sendMessage("§c§lYou logged out during the tutorial, we will teleport you to spawn.");

            Utils.inServerMessage.remove(player.getName());
        }

        player.setPlayerListHeader(MessageUtils.format("#01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP") + "\n§7§oSeason: 3");
        player.setPlayerListFooter("§8> §7§oCreated by Team Scyle §8< \n§7§oOnline: " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
        player.setPlayerListName(MessageUtils.returnTabPrefix(player) + player.getName());

        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
                ScoreboardManager.createMainBoard(player);

                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(!DuelsManager.duel.contains(online)) {
                        ScoreboardManager.updateScoreBoard(online);
                    }
                    online.setPlayerListFooter("§8> §7§oCreated by Team Scyle §8<\n§7§oOnline: " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
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
            MessageUtils.broadcastCenteredMessage("§fWelcome to the Origami SMP!");
            Bukkit.broadcastMessage("§8§m----------------------------------------------------");
            event.setJoinMessage(null);

            //
            Utils.clearChat(player);
            int taskID = -1;

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    Utils.clearChat(player);
                    Utils.inServerMessage.add(player.getName());

                }
            },20L);

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                        player.sendMessage("§3§lWelcome to Origami SMP!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1726272000, 128));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1726272000, 128));
                }
            },40L);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                        player.sendMessage(" ");
                        player.sendMessage("§fThis is a §3§oSurvival Multiplayer Server");
                        player.sendMessage("§fOwned, managed and developed by §3Team Scyle");
                }
            },100L);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage(" ");
                        player.sendMessage("§fUse §3§o/help §ffor our custom guide.");
                        player.sendMessage("§fEach player starts with three home locations.");
                        player.sendMessage("§fUse §3§o/rtp §fto teleport to a random location.");
                    }
                }
            },160L);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage(" ");
                        player.sendMessage("§fDon't forget to use §3§o/reward §fto recieve your daily free crate.");
                        player.sendMessage("§fAdditionally, remember to review our §3§o/rules §fbefore you start playing.");
                        player.sendMessage("§fGood luck and have fun!");
                    }
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
                    Utils.inServerMessage.remove(player.getName());
                }
            },280L);

            return;
        }

        NameUtils.giveTags(player);
        event.setJoinMessage("§a§l✔ §f" + player.getName() + " joined.");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = User.get(player);

        if(user.getFile().getBoolean("tutorial")) {
            Bukkit.getScheduler().cancelTask(Tutorial.taskID);
        }


        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(!DuelsManager.duel.contains(online)) {
                        ScoreboardManager.updateScoreBoard(online);
                    }
                    online.setPlayerListFooter("§8> §7§oCreated by Team Scyle §8< \n§7§oOnline: " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
                }
            }
        }, 20);

        if(user.getFile().getBoolean("builder")) {
            PermsUtils permsUtils = PermsUtils.getInstance();

            LuckPerms api = LuckPermsProvider.get();
            net.luckperms.api.model.user.User lpu = api.getPlayerAdapter(Player.class).getUser(player);
            lpu.data().add(PermissionNode.builder("-worldguard.region.bypass.SMP").build());
            lpu.data().remove(PermissionNode.builder("worldguard.region.bypass.SMP").build());
            lpu.data().remove(PermissionNode.builder("minecraft.command.gamemode").build());
            lpu.data().remove(PermissionNode.builder("fawe.admin").build());
            lpu.data().remove(PermissionNode.builder("fawe.*").build());
            lpu.data().remove(PermissionNode.builder("worldedit.*").build());
            api.getUserManager().saveUser(lpu);


            player.sendMessage(MessageUtils.format("§8> §fYou have disabled the #00DC99§lB#00D091§lu#00BF85§li#00AE79§ll#019B6C§ld#008A60§le#007F58§lr §fmode §csuccessfully§f."));

            for(Player online : Bukkit.getOnlinePlayers()) {
                if(online.hasPermission("smp.staff")) {
                    online.sendMessage("§4§lOP §8> §c" + player.getName() + " §flogged out and turned off his builder mode.");
                }
            }

            user.getFile().set("builder", false);
            user.saveFile();
        }

        event.setQuitMessage("§c§l❌ §f" + player.getName() + " has quit.");

    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = (Player) event.getPlayer();
        if (Settings.getInstance().getData().getBoolean("maintenance")) {
            if (player.isOp()) {
                event.allow();
                return;
            }
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§8§m----------------------------- \n" +
                    "§c§lServer Maintenance! \n" +
                    "§fOur server is currently undergoing maintenance.\n" +
                    "§fFor more updates, follow Thomseh on Twitch at \n" +
                    "§7§otwitch.tv/thomseh\n" +
                    "§8§m-----------------------------");
            return;
        }
        if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
            if (player.isOp()) {
                event.allow();
                return;
            }

            event.setKickMessage("§8§m----------------------------- \n" +
                    "§c§lYou are not whitelisted! \n" +
                    "§fThis server has not been released yet.\n" +
                    "§fFor more updates, follow Thomseh on Twitch at \n" +
                    "§7§otwitch.tv/thomseh\n" +
                    "§8§m-----------------------------");
            return;
        }

    }
}
