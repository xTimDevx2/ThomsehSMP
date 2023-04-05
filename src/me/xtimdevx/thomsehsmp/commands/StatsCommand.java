package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);

        if(args.length == 0) {
            if(user.getFile().get("stats.deaths") == null) {
                user.getFile().set("stats.deaths", 0);
                user.saveFile();
            }
            if(user.getFile().get("stats.mobkills") == null) {
                user.getFile().set("stats.mobkills", 0);
                user.saveFile();
            }
            if(user.getFile().get("stats.blocksbroken") == null) {
                user.getFile().set("stats.blocksbroken", 0);
                user.saveFile();
            }if(user.getFile().get("stats.blocksplaced") == null) {
                user.getFile().set("stats.blocksplaced", 0);
                user.saveFile();
            }if(user.getFile().get("stats.diamondmined") == null) {
                user.getFile().set("stats.diamondmined", 0);
                user.saveFile();
            }
            if(user.getFile().get("stats.duelwins") == null) {
                user.getFile().set("stats.duelwins", 0);
                user.saveFile();
            }
            if(user.getFile().get("stats.cratesopened") == null) {
                user.getFile().set("stats.cratesopened", 0);
                user.saveFile();
            }
            if(user.getFile().get("stats.witherkills") == null) {
                user.getFile().set("stats.witherkills", 0);
                user.saveFile();
            }

            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lStats");
            player.sendMessage(" ");
            player.sendMessage("§8» §fDeaths: §3§o" + user.getFile().getInt("stats.deaths"));
            player.sendMessage("§8» §fMob Kills: §3§o" + user.getFile().getInt("stats.mobkills"));
            player.sendMessage("§8» §fBlocks gemined: §3§o" + user.getFile().getInt("stats.blocksbroken"));
            player.sendMessage("§8» §fBlocks geplaatst: §3§o" + user.getFile().getInt("stats.blocksplaced"));
            player.sendMessage("§8» §fDiamonds gemined: §3§o" + user.getFile().getInt("stats.diamondmined"));
            player.sendMessage("§8» §fDuels gewonnen: §3§o" + user.getFile().getInt("stats.duelwins"));
            player.sendMessage("§8» §fCrates geopend: §3§o" + user.getFile().getInt("stats.cratesopened"));
            player.sendMessage("§8» §fWithers gekilled: §3§o" + user.getFile().getInt("stats.witherkills"));
            player.sendMessage("§8§m----------------------------------------------------");
        }
        if(args.length == 1) {
            OfflinePlayer target = (OfflinePlayer) Bukkit.getOfflinePlayer(args[0]);
            User tuser = User.get(target);
            
            if(tuser.isNew()) {
                return true;
            }

            if(tuser.getFile().get("stats.deaths") == null) {
                tuser.getFile().set("stats.deaths", 0);
                tuser.saveFile();
            }
            if(tuser.getFile().get("stats.mobkills") == null) {
                tuser.getFile().set("stats.mobkills", 0);
                tuser.saveFile();
            }
            if(tuser.getFile().get("stats.blocksbroken") == null) {
                tuser.getFile().set("stats.blocksbroken", 0);
                tuser.saveFile();
            }if(tuser.getFile().get("stats.blocksplaced") == null) {
                tuser.getFile().set("stats.blocksplaced", 0);
                tuser.saveFile();
            }if(tuser.getFile().get("stats.diamondmined") == null) {
                tuser.getFile().set("stats.diamondmined", 0);
                tuser.saveFile();
            }
            if(tuser.getFile().get("stats.duelwins") == null) {
                tuser.getFile().set("stats.duelwins", 0);
                tuser.saveFile();
            }
            if(tuser.getFile().get("stats.cratesopened") == null) {
                tuser.getFile().set("stats.cratesopened", 0);
                tuser.saveFile();
            }
            if(tuser.getFile().get("stats.witherkills") == null) {
                tuser.getFile().set("stats.witherkills", 0);
                tuser.saveFile();
            }
            
            
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§f§l" + args[0] + "'s §3§lStats");
            player.sendMessage(" ");
            player.sendMessage("§8» §fDeaths: §3§o" + tuser.getFile().getInt("stats.deaths"));
            player.sendMessage("§8» §fMob Kills: §3§o" + tuser.getFile().getInt("stats.mobkills"));
            player.sendMessage("§8» §fBlocks gemined: §3§o" + tuser.getFile().getInt("stats.blocksbroken"));
            player.sendMessage("§8» §fBlocks geplaatst: §3§o" + tuser.getFile().getInt("stats.blocksplaced"));
            player.sendMessage("§8» §fDiamonds gemined: §3§o" + tuser.getFile().getInt("stats.diamondmined"));
            player.sendMessage("§8» §fDuels gewonnen: §3§o" + tuser.getFile().getInt("stats.duelwins"));
            player.sendMessage("§8» §fCrates geopend: §3§o" + tuser.getFile().getInt("stats.cratesopened"));
            player.sendMessage("§8» §fWithers gekilled: §3§o" + tuser.getFile().getInt("stats.witherkills"));
            player.sendMessage("§8§m----------------------------------------------------");
        }
        return true;
    }
}
