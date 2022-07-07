package me.xtimdevx.thomsehsmp.managers;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.PermsUtils;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardManager {

    public static String rankName(Player player) {
        String groupname = PermsUtils.getInstance().getUser(player).getPrimaryGroup().toString();
        switch(groupname) {
            case "owner":
                return "Owner";
            case "developer":
                return "Developer";
            case "mod":
                return "Mod";
            case "builder":
                return "Builder";
            case "diamond":
                return "Diamond";
            case "emerald":
                return "Emerald";
            case "gold":
                return "Gold";
            default:
                return "Default";
        }
    }

    public static void createMainBoard(Player player) {
        User user = User.get(player);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("MainBoard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§3§lSMP");

        Score onlineName = obj.getScore("§8> §3§lOnline");
        onlineName.setScore(15);

        Team onlineCounter = scoreboard.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE + "");
        if (Bukkit.getOnlinePlayers().size() == 0) {
            onlineCounter.setPrefix(ChatColor.WHITE + "0§8/§f" + Bukkit.getMaxPlayers());
        } else {
            onlineCounter.setPrefix(String.valueOf(ChatColor.WHITE) + "" + Bukkit.getOnlinePlayers().size() + "§8/§f" + Bukkit.getMaxPlayers());
        }
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE + "").setScore(14);

        Score questName = obj.getScore("§8> §3§lRank");
        questName.setScore(13);

        Team questMain = scoreboard.registerNewTeam("playerRank");
        questMain.addEntry(ChatColor.RED + "" + ChatColor.WHITE + "");
        questMain.setPrefix("" + rankName(player));
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE + "").setScore(12);

        player.setScoreboard(scoreboard);
    }

    public static void updateScoreBoard(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        User user = User.get(player);

        if(Bukkit.getOnlinePlayers().size() == 0) {
            scoreboard.getTeam("onlineCounter").setPrefix(ChatColor.WHITE + "0§8/§f" + Bukkit.getMaxPlayers());
        }else {
            scoreboard.getTeam("onlineCounter").setPrefix(String.valueOf(ChatColor.WHITE) + "" + Bukkit.getOnlinePlayers().size() + "§8/§f" + Bukkit.getMaxPlayers());
        }

        scoreboard.getTeam("playerRank").setPrefix("" + rankName(player));
    }
}
