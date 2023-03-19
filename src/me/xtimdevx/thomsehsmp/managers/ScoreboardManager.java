package me.xtimdevx.thomsehsmp.managers;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.minigames.pushbattle.PushbattleMain;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.PermsUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.*;

public class ScoreboardManager {

    public static EconomyManager economyManager = new EconomyManager();

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
        obj.setDisplayName(MessageUtils.format("#01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP"));

        Score season = obj.getScore("§7§oSeason: 3   Patch: B-0.1");
        season.setScore(15);

        Score line1 = obj.getScore("§f§8§m-----------------");
        line1.setScore(14);

        Score questName = obj.getScore("§8> §3§lRank");
        questName.setScore(13);

        Team questMain = scoreboard.registerNewTeam("playerRank");
        questMain.addEntry(ChatColor.RED + "" + ChatColor.WHITE + "");
        questMain.setPrefix("  " + rankName(player));
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE + "").setScore(12);


        Score balanceName = obj.getScore("§8> §3§lBalance");
        balanceName.setScore(11);

        Team balanceMain = scoreboard.registerNewTeam("playerBalance");
        balanceMain.addEntry(ChatColor.AQUA + "" + ChatColor.WHITE + "");
        balanceMain.setPrefix("  " + economyManager.getBalance(player) + " ⛀");
        obj.getScore(ChatColor.AQUA + "" + ChatColor.WHITE + "").setScore(10);

        Score empty1 = obj.getScore(" ");
        empty1.setScore(3);
        
        Score onlineName = obj.getScore("§8> §3§lOnline");
        onlineName.setScore(2);

        Team onlineCounter = scoreboard.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE + "");
        if (Bukkit.getOnlinePlayers().size() == 0) {
            onlineCounter.setPrefix(ChatColor.WHITE + "  0§8/§f" + Bukkit.getMaxPlayers());
        } else {
            onlineCounter.setPrefix(ChatColor.WHITE + "  " + Bukkit.getOnlinePlayers().size() + "§8/§f" + Bukkit.getMaxPlayers());
        }
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE + "").setScore(1);

        Score line2 = obj.getScore("§8§m-----------------");
        line2.setScore(0);

        Team ip = scoreboard.registerNewTeam("ip");
        ip.addEntry(ChatColor.DARK_RED + "" + ChatColor.WHITE + "");
        ip.setPrefix("§fThomseh§8.§flive");
        obj.getScore(ChatColor.DARK_RED + "" + ChatColor.WHITE + "").setScore(-1);



        int taskIDR = -1;
        final int taskID = -1;

        taskIDR = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§f§3§lT§fhomseh§8.§flive");
                    }
                }, 10);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fT§3§lh§fomseh§8.§flive");
                    }
                }, 20);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fTh§3§lo§fmseh§8.§flive");
                    }
                }, 30);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fTho§3§lm§fseh§8.§flive");
                    }
                }, 40);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThom§3§ls§feh§8.§flive");
                    }
                }, 50);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThoms§3§le§fh§8.§flive");
                    }
                }, 60);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomse§3§lh§8.§flive");
                    }
                }, 70);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§f§3§ll§five");
                    }
                }, 80);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fl§3§li§fve");
                    }
                }, 90);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fli§3§lv§fe");
                    }
                }, 100);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fliv§3§le");
                    }
                }, 110);

            }
        }, 0,110);

        player.setScoreboard(scoreboard);
    }

    public static BukkitTask updateTaskID;

    public static void updateScoreBoard(Player player) {

        updateTaskID = Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
                Scoreboard scoreboard = player.getScoreboard();
                User user = User.get(player);

                try {
                    if(Bukkit.getOnlinePlayers().size() == 0) {
                        scoreboard.getTeam("onlineCounter").setPrefix(ChatColor.WHITE + "  0§8/§f" + Bukkit.getMaxPlayers());
                    }else {
                        scoreboard.getTeam("onlineCounter").setPrefix(ChatColor.WHITE + "  " + Bukkit.getOnlinePlayers().size() + "§8/§f" + Bukkit.getMaxPlayers());
                    }

                    scoreboard.getTeam("playerRank").setPrefix("  " + rankName(player));

                    scoreboard.getTeam("playerBalance").setPrefix("  " + economyManager.getBalance(player) + " ⛀");
                }catch (NullPointerException ignored) {
                }
        }, 20);

    }



    public static int dueltaskIDR = -1;

    public static void createDuelBoard(Player player, String oponentname, String mode) {
        User user = User.get(player);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("duelboard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(MessageUtils.format("#01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP"));

        Score season = obj.getScore("§7§oSeason: 3   Patch: B-0.1");
        season.setScore(15);

        Score line1 = obj.getScore("§f§8§m-----------------");
        line1.setScore(14);

        Score questName = obj.getScore("§8> §3§lDuel! §8<");
        questName.setScore(13);

        Score MT = obj.getScore(" ");
        Score MT2 = obj.getScore("§f ");
        MT.setScore(12);
        MT2.setScore(9);

        Score tegenstander = obj.getScore("§8> §3§lTegenstander");
        tegenstander.setScore(11);

        Score tegenstandernaam = obj.getScore("§f" + oponentname);
        tegenstandernaam.setScore(10);


        Score balanceName = obj.getScore("§8> §3§lModus");
        balanceName.setScore(8);

        Score modus = obj.getScore("§f" + mode);
        modus.setScore(7);

        Score line2 = obj.getScore("§8§m-----------------");
        line2.setScore(0);

        Team ip = scoreboard.registerNewTeam("ip");
        ip.addEntry(ChatColor.DARK_RED + "" + ChatColor.WHITE + "");
        ip.setPrefix("§fThomseh§8.§flive");
        obj.getScore(ChatColor.DARK_RED + "" + ChatColor.WHITE + "").setScore(-1);




        dueltaskIDR = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§f§3§lT§fhomseh§8.§flive");
                    }
                }, 10);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fT§3§lh§fomseh§8.§flive");
                    }
                }, 20);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fTh§3§lo§fmseh§8.§flive");
                    }
                }, 30);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fTho§3§lm§fseh§8.§flive");
                    }
                }, 40);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThom§3§ls§feh§8.§flive");
                    }
                }, 50);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThoms§3§le§fh§8.§flive");
                    }
                }, 60);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomse§3§lh§8.§flive");
                    }
                }, 70);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§f§3§ll§five");
                    }
                }, 80);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fl§3§li§fve");
                    }
                }, 90);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fli§3§lv§fe");
                    }
                }, 100);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fliv§3§le");
                    }
                }, 110);

            }
        }, 0,110);

        player.setScoreboard(scoreboard);
    }

    public static int pusbattleTask = -1;

    public static void createPushbattleLobbyBoard(Player player) {
        User user = User.get(player);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("pbboard", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(MessageUtils.format("#825AFE§lPushbattle"));

        Score season = obj.getScore("§7§oLobby");
        season.setScore(15);

        Score line1 = obj.getScore("§f§8§m-----------------");
        line1.setScore(14);

        Score questName = obj.getScore(MessageUtils.format("§8> §9§lSpelers"));
        questName.setScore(13);

        Team questMain = scoreboard.registerNewTeam("players");
        questMain.addEntry(ChatColor.RED + "" + ChatColor.WHITE + "");
        questMain.setPrefix("§8> §f" + PushbattleMain.inLobby.size() + "§8/§f8");
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE + "").setScore(12);

        Score team = obj.getScore(MessageUtils.format("§8> §9§lTeam"));
        team.setScore(11);

        if(PushbattleMain.teamBlue.contains(player)) {
            Score teamnaam = obj.getScore("§8> " + "§9Blauw");
            teamnaam.setScore(10);
        }else {
            Score teamnaam = obj.getScore("§8> " + "§cRood");
            teamnaam.setScore(10);

        }

        Score MT = obj.getScore(" ");
        MT.setScore(9);

        Score balanceName = obj.getScore(MessageUtils.format("§8> §9§lStart in"));
        balanceName.setScore(8);

        Team start = scoreboard.registerNewTeam("start");
        start.addEntry(ChatColor.BLUE + "" + ChatColor.WHITE + "");
        start.setPrefix("§8> §f" + "Wachten...");
        obj.getScore(ChatColor.BLUE + "" + ChatColor.WHITE + "").setScore(7);

        Score line2 = obj.getScore("§8§m-----------------");
        line2.setScore(0);

        Team ip = scoreboard.registerNewTeam("ip");
        ip.addEntry(ChatColor.DARK_RED + "" + ChatColor.WHITE + "");
        ip.setPrefix("§fThomseh§8.§flive");
        obj.getScore(ChatColor.DARK_RED + "" + ChatColor.WHITE + "").setScore(-1);




        pusbattleTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§f§9§lT§fhomseh§8.§flive");
                    }
                }, 10);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fT§9§lh§fomseh§8.§flive");
                    }
                }, 20);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fTh§9§lo§fmseh§8.§flive");
                    }
                }, 90);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fTho§9§lm§fseh§8.§flive");
                    }
                }, 40);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThom§9§ls§feh§8.§flive");
                    }
                }, 50);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThoms§9§le§fh§8.§flive");
                    }
                }, 60);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomse§9§lh§8.§flive");
                    }
                }, 70);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§f§9§ll§five");
                    }
                }, 80);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fl§9§li§fve");
                    }
                }, 90);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fli§9§lv§fe");
                    }
                }, 100);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fliv§9§le");
                    }
                }, 110);

            }
        }, 0,110);

        player.setScoreboard(scoreboard);
    }

    public static BukkitTask updatePBTaskID;
    public static BukkitTask repeatingTimerTask;

    public static void updatePBLobbyScoreBoard(Player player) {

        updatePBTaskID = Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
                Scoreboard scoreboard = player.getScoreboard();
                User user = User.get(player);

                try {

                }catch (NullPointerException ignored) {
                    scoreboard.getTeam("players").setPrefix("§8> §f" + PushbattleMain.inLobby.size() + "§8/§f8");

                }
                }, 20);


        if(PushbattleMain.starting) {
            repeatingTimerTask = Bukkit.getScheduler().runTaskTimerAsynchronously(Main.plugin, () -> {
                    Scoreboard scoreboard = player.getScoreboard();
                    User user = User.get(player);

                    try{
                        scoreboard.getTeam("start").setPrefix("§8> §f" + s);
                    }catch (Exception ignored) {
                    }
            }, 0, 20);

        }
    }

    public static void createPushbattleGameBoard(Player player) {
        User user = User.get(player);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("pbboardgame", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(MessageUtils.format("#825AFE§lPushbattle"));

        Score season = obj.getScore("§7§oIngame");
        season.setScore(15);

        Score line1 = obj.getScore("§f§8§m-----------------");
        line1.setScore(14);

        Score questName = obj.getScore(MessageUtils.format("§8> §9§lSpelers"));
        questName.setScore(13);

        Team questMain = scoreboard.registerNewTeam("players");
        questMain.addEntry(ChatColor.RED + "" + ChatColor.WHITE + "");
        questMain.setPrefix("§8> §f" + PushbattleMain.inGame.size());
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE + "").setScore(12);

        Score MT = obj.getScore("§9 ");
        MT.setScore(9);

        Score balanceName = obj.getScore(MessageUtils.format("§8> §9§lSolo Kills"));
        balanceName.setScore(8);

        Team start = scoreboard.registerNewTeam("solokills");
        start.addEntry(ChatColor.BLUE + "" + ChatColor.WHITE + "");
        start.setPrefix("§8> §f" + user.getFile().getInt("pb.solokills"));
        obj.getScore(ChatColor.BLUE + "" + ChatColor.WHITE + "").setScore(7);


        Score solokills = obj.getScore(MessageUtils.format("§8> §9§lTeam Kills"));
        solokills.setScore(5);

        Team bluekills = scoreboard.registerNewTeam("bluekills");
        bluekills.addEntry(ChatColor.DARK_PURPLE + "" + ChatColor.WHITE + "");
        if(PushbattleMain.teamBlue.contains(player)) {
            bluekills.setPrefix("§8> §9Blauw: §f" + PushbattleMain.bluekills + " §7§o(Jouw Team)");
        }else {
            bluekills.setPrefix("§8> §9Blauw §f" + PushbattleMain.bluekills);
        }
        obj.getScore(ChatColor.DARK_PURPLE + "" + ChatColor.WHITE + "").setScore(4);


        Team redkills = scoreboard.registerNewTeam("redkills");
        redkills.addEntry(ChatColor.GRAY + "" + ChatColor.WHITE + "");
        if(PushbattleMain.teamRed.contains(player)) {
            redkills.setPrefix("§8> §cRood: §f" + PushbattleMain.redkills + " §7§o(Jouw Team)");
        }else {
            redkills.setPrefix("§8> §cRood: §f" + PushbattleMain.redkills);
        }
        obj.getScore(ChatColor.GRAY + "" + ChatColor.WHITE + "").setScore(3);


        Score MT6 = obj.getScore("§c ");
        MT6.setScore(2);

        Score timerName = obj.getScore(MessageUtils.format("§8> §9§lTimer"));
        timerName.setScore(1);

        String timernode = "0" + m + "§8:§f" + ((s < 10) ? "0" + s : s);

        Team timer = scoreboard.registerNewTeam("timer");
        timer.addEntry(ChatColor.GOLD + "" + ChatColor.WHITE + "");
        timer.setPrefix("§8> §f" + timernode);
        obj.getScore(ChatColor.GOLD + "" + ChatColor.WHITE + "").setScore(0);


        Score line2 = obj.getScore("§8§m-----------------");
        line2.setScore(-1);

        Team ip = scoreboard.registerNewTeam("ip");
        ip.addEntry(ChatColor.DARK_RED + "" + ChatColor.WHITE + "");
        ip.setPrefix("§fThomseh§8.§flive");
        obj.getScore(ChatColor.DARK_RED + "" + ChatColor.WHITE + "").setScore(-2);




        pusbattleTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§f§9§lT§fhomseh§8.§flive");
                    }
                }, 10);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fT§9§lh§fomseh§8.§flive");
                    }
                }, 20);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fTh§9§lo§fmseh§8.§flive");
                    }
                }, 90);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fTho§9§lm§fseh§8.§flive");
                    }
                }, 40);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThom§9§ls§feh§8.§flive");
                    }
                }, 50);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThoms§9§le§fh§8.§flive");
                    }
                }, 60);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomse§9§lh§8.§flive");
                    }
                }, 70);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§f§9§ll§five");
                    }
                }, 80);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fl§9§li§fve");
                    }
                }, 90);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fli§9§lv§fe");
                    }
                }, 100);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ip.setPrefix("§fThomseh§8.§fliv§9§le");
                    }
                }, 110);

            }
        }, 0,110);

        player.setScoreboard(scoreboard);
    }

    public static BukkitTask updatePBGTaskID;

    public static void updatePBGameScoreBoard(Player player) {

        updatePBGTaskID = Bukkit.getScheduler().runTaskTimerAsynchronously(Main.plugin, () -> {
                Scoreboard scoreboard = player.getScoreboard();
                User user = User.get(player);

                String timernode = "0" + m + "§8:§f" + ((s < 10) ? "0" + s : s);

                try {
                    scoreboard.getTeam("timer").setPrefix("§8> §f" + timernode);

                    scoreboard.getTeam("timer").setPrefix("§8> §f" + timernode);

                    scoreboard.getTeam("solokills").setPrefix("§8> §f" + user.getFile().getInt("pb.solokills"));

                    if(PushbattleMain.teamBlue.contains(player)) {
                        scoreboard.getTeam("bluekills").setPrefix("§8> §9Blauw: §f" + PushbattleMain.bluekills + " §7§o(Jouw Team)");
                    }else {
                        scoreboard.getTeam("bluekills").setPrefix("§8> §9Blauw: §f" + PushbattleMain.bluekills);
                    }
                    if(PushbattleMain.teamRed.contains(player)) {
                        scoreboard.getTeam("redkills").setPrefix("§8> §cRood: §f" + PushbattleMain.redkills + " §7§o(Jouw Team)");
                    }else {
                        scoreboard.getTeam("redkills").setPrefix("§8> §cRood: §f" + PushbattleMain.redkills);
                    }
                }catch (Exception ignored) {

                }
        }, 0, 20);

    }

    public static BukkitTask timerTaskID;
    public static BukkitTask lobbyTimerTaskID;

    public static int s;
    public static int m;
    public static void startTimer(String type) {
        if(type.equalsIgnoreCase("ingame")) {
            s = 0;
            m = 0;
            Bukkit.getScheduler().runTaskTimer(Main.plugin, timerTaskID -> {
                    s++;
                    if(s == 60) {
                        s = 0;
                        m++;
                    }
            }, 0, 20);

        }

        if(type.equalsIgnoreCase("lobby")) {
            s = 31;
            Bukkit.getScheduler().runTaskTimer(Main.plugin, lobbyTimerTaskID -> {

                    s--;
            }, 0, 20);
        }
    }
}
