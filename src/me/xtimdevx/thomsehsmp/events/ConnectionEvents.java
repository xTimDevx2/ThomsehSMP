package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.commands.HomeCommand;
import me.xtimdevx.thomsehsmp.commands.SethomeCommand;
import me.xtimdevx.thomsehsmp.managers.ScoreboardManager;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

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
            MessageUtils.broadcastCenteredMessage("§fWelcome to the server!");
            Bukkit.broadcastMessage("§8§m----------------------------------------------------");
            user.getFile().set("new", false);
            user.saveFile();
            event.setJoinMessage(null);

            player.teleport(LocationUtils.spawn);
            player.getInventory().addItem(new ItemStack(Material.BREAD, 5));
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_SHOVEL));

            return;
        }

        event.setJoinMessage("§e" + player.getName() + " joined the game pog");
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
