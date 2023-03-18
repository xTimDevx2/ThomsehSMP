package me.xtimdevx.thomsehsmp.minigames.pushbattle;

import com.lkeehl.tagapi.TagBuilder;
import com.lkeehl.tagapi.api.Tag;
import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.managers.EconomyManager;
import me.xtimdevx.thomsehsmp.managers.ScoreboardManager;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.NameUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import net.citizensnpcs.npc.ai.speech.Chat;
import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Objects;

public class PushbattleMain {

    public static boolean gameRunning;
    public static boolean starting;
    public static boolean tutorial;
    public static boolean cancel;
    public static ArrayList inGame = new ArrayList();
    public static ArrayList teamRed = new ArrayList();
    public static ArrayList teamBlue = new ArrayList();
    public static ArrayList inLobby = new ArrayList();


    public static Location blueSpawn = new Location(Bukkit.getWorld("SMP"), 108.5, 63, -216.5);
    public static Location redSpawn = new Location(Bukkit.getWorld("SMP"), 108.5, 63, -221.5);
    public static BukkitScheduler countdownTask = Bukkit.getScheduler();
    public static BukkitTask task;


    /*
    Pushbattle:
        Spelers kunnen de game joinen via de command /pb join of de sign bij de arena.
        Er zijn 2 teams in de game. Rood en blauw
        Er zijn minstens 2 spelers nodig om de game te starten (maximum 8)
        De arena is voorzien om later te kunnen spelen met 4 teams.
        De spelers starten in de lobby tribune. Na 30 seconden met de minimum aantal spelers start de game.

          Player loadout:
           - Stick
           - Chestplate in kleur team
           - Candle in kleur team
           - nametag in kleur team.



           important:
           - Disable commands while ingame
           - Make sure inventories work.
           - Geen item drops
           - Geen echte deaths

        Game start.
     */

    public static void registerPushbattleEvents() {
        Bukkit.getPluginManager().registerEvents(new PushbattleEvents(), Main.plugin);
    }


    public static void startCountdown() {
        ScoreboardManager.startTimer("lobby");
        broadcastPushbattle("§8> §fDe game start in " + color + "30 §fseconden!", false);
        starting = true;

        //Set slowness
        //3sec
        //Gamemode 3

        for(Player online : Bukkit.getOnlinePlayers()) {
            if(inLobby.contains(online)) {
                User user = User.get(online);
                user.getFile().set("pb.lastdamager", null);

                user.getFile().set("pb.solokills", 0);

                bluekills = 0;
                redkills = 0;

                user.saveFile();

                online.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1726272000, 128));
                online.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1726272000, 6));
                online.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1726272000, 10));
                online.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1726272000, 6));
                online.setInvisible(true);
                tutorial = true;


                ScoreboardManager.updatePBLobbyScoreBoard(online);
            }
        }

        countdownTask.runTaskLater(Main.plugin, task -> {
    if(inLobby.size() == 1)
 {
                task.cancel();
                return;
            }
            for(Player online : Bukkit.getOnlinePlayers()) {
                if(inLobby.contains(online)) {
                    online.setGameMode(GameMode.SPECTATOR);
                }
            }
        }, 60);
        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(inLobby.contains(online)) {
                        Location loc1 = new Location(Bukkit.getWorld("SMP"), 86.5, 83, -231.5);
                        loc1.setPitch(90);
                        loc1.setYaw(-180);
                        online.teleport(loc1);

                        online.sendTitle(MessageUtils.format(color + "§lPushbattle"), MessageUtils.format("§fDit zijn de regels van " + color + "Pushbattle§f!"));
                    }
                }
        }, 80);

        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(inLobby.contains(online)) {
                        Location loc1 = new Location(Bukkit.getWorld("SMP"), 86.5, 68, -247.5);
                        loc1.setPitch(20);
                        loc1.setYaw(0);
                        online.teleport(loc1);

                        online.sendTitle(MessageUtils.format(color + "§lPushbattle"), MessageUtils.format("§fHit spelers van het andere team van de map voor " + color + "punten§f!"));
                }
            }
        }, 180);

        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(inLobby.contains(online)) {
                        Location loc1 = new Location(Bukkit.getWorld("SMP"), 108.5, 66.5, -209.5);
                        loc1.setPitch(13);
                        loc1.setYaw(135);
                        online.teleport(loc1);

                        online.sendTitle(MessageUtils.format(color + "§lPushbattle"), MessageUtils.format("§fVeel succes en veel plezier."));
                    }
                }
                broadcastPushbattle("§8> §fDe game start in " + color + "15 §fseconden!", false);
        }, 300);

        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(inLobby.contains(online)) {
                        online.setGameMode(GameMode.SURVIVAL);
                        online.setInvisible(false);
                        online.removePotionEffect(PotionEffectType.SLOW);
                        online.removePotionEffect(PotionEffectType.JUMP);
                        online.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        online.removePotionEffect(PotionEffectType.SLOW_DIGGING);

                    }
                    tutorial = false;
                }
        }, 320);

        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                broadcastPushbattle("§8> §fDe game start in " + color + "5 §fseconden!", false);
        }, 500);
        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                broadcastPushbattle("§8> §fDe game start in " + color + "4 §fseconden!", false);
        }, 520);
        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                broadcastPushbattle("§8> §fDe game start in " + color + "3 §fseconden!", false);
        }, 540);
        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                broadcastPushbattle("§8> §fDe game start in " + color + "2 §fseconden!", false);
        }, 560);
        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                broadcastPushbattle("§8> §fDe game start in " + color + "1 §fseconden!", false);
        }, 580);
        countdownTask.runTaskLater(Main.plugin, task -> {
            if(inLobby.size() == 1)

 {
                task.cancel();
                return;
            }
                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(inLobby.contains(online)) {
                        inLobby.remove(online);
                        inGame.add(online);


                    }
                }
                starting = false;
                Bukkit.getScheduler().cancelTask(102);
                Bukkit.getScheduler().cancelTask(ScoreboardManager.lobbyTimerTaskID.getTaskId());
                startGame();
        }, 600);

    }

    public static int redkills;
    public static int bluekills;
    public static boolean tie;
    public static Location redSpawnMap = new Location(Bukkit.getWorld("SMP"), 86.5, 63, -248.5);
    public static Location blueSpawnMap = new Location(Bukkit.getWorld("SMP"), 86.5, 63, -214.5);

    public static int gameTask = 6;

    public static int returnTime(){
        if(inGame.size() < 4) {
            return 3600;
        }

        if(inGame.size() < 6 && inGame.size() > 3) {
            return 4800;
        }

        if(inGame.size() == 8) {
            return 6000;
        }
        return 6000;
    }
    public static void startGame() {
        Bukkit.getScheduler().cancelTask(ScoreboardManager.repeatingTimerTask.getTaskId());
        Bukkit.getScheduler().cancelTask(ScoreboardManager.lobbyTimerTaskID.getTaskId());
        for(Player online : Bukkit.getOnlinePlayers()) {
            if(teamRed.contains(online)) {
                online.teleport(redSpawnMap);
            }
            if(teamBlue.contains(online)) {
                blueSpawnMap.setYaw(180);
                online.teleport(blueSpawnMap);
            }
            if(inGame.contains(online)) {

                online.getInventory().clear();

                ItemStack stick = new ItemStack(Material.STICK);
                ItemMeta stickMeta = stick.getItemMeta();
                stickMeta.setDisplayName(MessageUtils.format("§8> " + color + "§lTak van een boom §8<"));
                stickMeta.addEnchant(Enchantment.KNOCKBACK, 1, false);
                stick.setItemMeta(stickMeta);

                online.getInventory().addItem(stick);

                online.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(online, color + "§lPushbattle");
                online.sendMessage(" ");
                MessageUtils.sendCenteredMessage(online, "§fDeze " + color + "Pushbattle §fgame is begonnen!");
                MessageUtils.sendCenteredMessage(online, "§fJullie spelen met §c" + teamRed.size() +  " §frode en §9" + teamBlue.size() + " §fblauwe spelers.");
                online.sendMessage(" ");
                if(inGame.size() < 4) {
                    MessageUtils.sendCenteredMessage(online, "§fDeze game zal " + color +  "§l3 §fminuten duren.");
                }

                if(inGame.size() < 6 && inGame.size() > 3) {
                    MessageUtils.sendCenteredMessage(online, "§fDeze game zal " + color +  "§l4 §fminuten duren.");
                }

                if(inGame.size() == 8) {
                    MessageUtils.sendCenteredMessage(online, "§fDeze game zal " + color +  "§l5 §fminuten duren.");
                }
                online.sendMessage(" ");
                MessageUtils.sendCenteredMessage(online, color + "Vriendschappelijk vuur §fstaat §cuit§f!");
                MessageUtils.sendCenteredMessage(online, "§fHit het andere team van de map om punten te scoren.");
                MessageUtils.sendCenteredMessage(online, color + "§oGLHF");
                online.sendMessage("§8§m----------------------------------------------------");

                redkills = 0;
                bluekills = 0;


                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        ScoreboardManager.createPushbattleGameBoard(online);
                        try {
                            ScoreboardManager.updatePBGameScoreBoard(online);
                        }catch (Exception ignored) {

                        }
                    }
                }, 20L);
            }
        }
        ScoreboardManager.startTimer("ingame");

        gameTask = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if(redkills == bluekills) {
                    //tie
                    broadcastPushbattle("§lWe zitten met een gelijkspel, de volgende kill wint de game!", true);
                    tie = true;
                    return;
                }

                if(redkills < bluekills) {
                    for(Player online : Bukkit.getOnlinePlayers()) {
                        if(inGame.contains(online)) {

                            online.sendMessage("§8§m----------------------------------------------------");
                            MessageUtils.sendCenteredMessage(online, color + "§lPushbattle");
                            online.sendMessage(" ");
                            MessageUtils.sendCenteredMessage(online, "§9§lBlauw heeft gewonnen!");
                            MessageUtils.sendCenteredMessage(online, "§fZe wonnen deze game met " + color + bluekills + " §fkills!");
                            online.sendMessage(" ");
                            MessageUtils.sendCenteredMessage(online, color + "§oBedankt om te spelen!");
                            online.sendMessage("§8§m----------------------------------------------------");
                            online.sendMessage("§7§oJe wordt over 5 seconden terug uit het spel gezet...");
                        }

                    }
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            endGame("blue");

                        }
                    },100);
                }else {
                    //red
                    for(Player online : Bukkit.getOnlinePlayers()) {
                        if(inGame.contains(online)) {

                            online.sendMessage("§8§m----------------------------------------------------");
                            MessageUtils.sendCenteredMessage(online, color + "§lPushbattle");
                            online.sendMessage(" ");
                            MessageUtils.sendCenteredMessage(online, "§c§lRood heeft gewonnen!");
                            MessageUtils.sendCenteredMessage(online, "§fZe wonnen deze game met " + color + redkills + " §fkills!");
                            online.sendMessage(" ");
                            MessageUtils.sendCenteredMessage(online, color + "§oBedankt om te spelen!");
                            online.sendMessage("§8§m----------------------------------------------------");
                            online.sendMessage("§7§oJe wordt over 5 seconden terug uit het spel gezet...");
                        }
                    }
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            endGame("red");

                        }
                    },100);
                }
            }
        }, returnTime());

    }

    public static void endAbrupt(String winner) {
        if(winner == "red") {
            for(Player online : Bukkit.getOnlinePlayers()) {
                if(inGame.contains(online)) {

                    online.sendMessage("§8§m----------------------------------------------------");
                    MessageUtils.sendCenteredMessage(online, color + "§lPushbattle");
                    online.sendMessage(" ");
                    MessageUtils.sendCenteredMessage(online, "§c§lRood heeft gewonnen!");
                    MessageUtils.sendCenteredMessage(online, "§fZe hebben gewonnen door een forfeit.");
                    online.sendMessage(" ");
                    MessageUtils.sendCenteredMessage(online, color + "§oBedankt om te spelen!");
                    online.sendMessage("§8§m----------------------------------------------------");
                    online.sendMessage("§7§oJe wordt over 5 seconden terug uit het spel gezet...");
                }
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    endGame("red");

                }
            },100);
        }
        if(winner == "blue") {
            for(Player online : Bukkit.getOnlinePlayers()) {
                if(inGame.contains(online)) {

                    online.sendMessage("§8§m----------------------------------------------------");
                    MessageUtils.sendCenteredMessage(online, color + "§lPushbattle");
                    online.sendMessage(" ");
                    MessageUtils.sendCenteredMessage(online, "§9§lBlue heeft gewonnen!");
                    MessageUtils.sendCenteredMessage(online, "§fZe hebben gewonnen door een forfeit.");
                    online.sendMessage(" ");
                    MessageUtils.sendCenteredMessage(online, color + "§oBedankt om te spelen!");
                    online.sendMessage("§8§m----------------------------------------------------");
                    online.sendMessage("§7§oJe wordt over 5 seconden terug uit het spel gezet...");
                }
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    endGame("blue");

                }
            },100);
        }
    }
    public static void endGame(String winner) {
        Bukkit.getScheduler().cancelTask(ScoreboardManager.updatePBGTaskID.getTaskId());
        Bukkit.getScheduler().cancelTask(ScoreboardManager.timerTaskID.getTaskId());
        Bukkit.getScheduler().cancelTask(ScoreboardManager.lobbyTimerTaskID.getTaskId());
        Bukkit.getScheduler().cancelTask(ScoreboardManager.repeatingTimerTask.getTaskId());
        Bukkit.getScheduler().cancelTask(ScoreboardManager.updatePBTaskID.getTaskId());

        for(Player online : Bukkit.getOnlinePlayers()) {
            if(inGame.contains(online)) {
                online.getInventory().clear();
                Utils.restoreInventory(online);

                User user = User.get(online);
                online.teleport(user.getFile().getLocation("pblocation"));

                ScoreboardManager.createMainBoard(online);
            }

            EconomyManager economyManager = new EconomyManager();

            if(teamRed.contains(online)) {
                if(winner.equals("red")) {
                    economyManager.addBalance(online, 120);

                    online.sendMessage("§8- §f+120 Coins §7§o(100 Winst, 20 Speelbonus)");
                }else {
                    economyManager.addBalance(online, 20);

                    online.sendMessage("§8- §f+20 Coins §7§o(20 Speelbonus)");
                }
            }
            if(teamBlue.contains(online)) {
                if(winner.equals("blue")) {
                    economyManager.addBalance(online, 120);

                    online.sendMessage("§8- §f+120 Coins §7§o(100 Winst, 20 Speelbonus)");
                }else {
                    economyManager.addBalance(online, 20);

                    online.sendMessage("§8- §f+20 Coins §7§o(20 Speelbonus)");
                }
            }
        }

        inGame.clear();
        teamBlue.clear();
        teamRed.clear();
        inLobby.clear();
        gameRunning = false;
        tutorial = false;
        starting = false;
        tie = false;

        redkills = 0;
        bluekills = 0;

    }

    public static void joinLobby(Player player) {
        if(conditionsMet(player)) {
            inLobby.add(player);
            decideTeam(player);

            User user = User.get(player);

            String team;

            user.getFile().set("pblocation", player.getLocation());
            user.saveFile();


            if(teamBlue.contains(player)) {
                blueSpawn.setPitch(0);
                blueSpawn.setYaw(110);

                player.teleport(blueSpawn);

                team = "blue";
            }else {
                redSpawn.setPitch(0);
                redSpawn.setYaw(104);

                player.teleport(redSpawn);

                team = "red";
            }


            Utils.saveInventory(player);

            player.getInventory().clear();

            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            if(team.equals("red")) {
                chestplateMeta.setColor(Color.RED);
                chestplate.setItemMeta(chestplateMeta);

                player.getInventory().setHelmet(new ItemStack(Material.RED_CANDLE));
                player.getInventory().setChestplate(new ItemStack(chestplate));

                player.setPlayerListName(player.getPlayerListName().replace(player.getName(), ChatColor.RED + player.getName()));

                Tag tag = Tag.create(player); // Create a new Tag
                tag.addTagLine(10).setText(pl->"§c" + player.getName());
                tag.addTagLine(9).setText(pl->"§4§lRood");
                tag.giveTag();
            }else {
                chestplateMeta.setColor(Color.BLUE);
                chestplate.setItemMeta(chestplateMeta);

                player.getInventory().setHelmet(new ItemStack(Material.BLUE_CANDLE));
                player.getInventory().setChestplate(chestplate);

                player.setPlayerListName(player.getPlayerListName().replace(player.getName(), ChatColor.BLUE + player.getName()));

                com.lkeehl.tagapi.api.Tag tag = Tag.create(player); // Create a new Tag
                tag.addTagLine(10).setText(pl->"§3" + player.getName());
                tag.addTagLine(9).setText(pl->"§9§lBlauw");
                tag.giveTag();

            }

            ItemStack leaveitem = new ItemStack(Material.CYAN_BED);
            ItemMeta leaveitemMeta = leaveitem.getItemMeta();
            leaveitemMeta.setDisplayName(MessageUtils.format("§8> " + color +"Klik op mij om uit de lobby te gaan §8<"));
            leaveitem.setItemMeta(leaveitemMeta);

            player.getInventory().setItem(8, leaveitem);


            broadcastPushbattle(color + "§o" + player.getName() + " §fis de lobby gejoined. §7§o(" + inLobby.size() + "/8)", true);
            if(inLobby.size() == 1) {
                broadcastPushbattle("§8> §fNog " + color + "1 §fspeler nodig om te game te starten...", false);
            }

            ScoreboardManager.createPushbattleLobbyBoard(player);
            for(Player online : Bukkit.getOnlinePlayers()) {
                if(inLobby.contains(online)) {
                    ScoreboardManager.updatePBLobbyScoreBoard(online);
                }
            }

            if(inLobby.size() == 2) {
                starting = true;

                ComponentBuilder builder2 = new ComponentBuilder("");
                builder2.append(MessageUtils.format("§8> §fKlik " + color + " hier §fom de lobby te joinen!"));
                builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/pb join"));
                builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent(MessageUtils.format("§fKlik op mij om te " + color +"joinen§f."))}));

                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(!inLobby.contains(online)) {
                        online.sendMessage(MessageUtils.format(prefix + "Een " + color + "Pushbattle §fgame start in " + color + " 30 §fseconden."));
                        online.spigot().sendMessage(builder2.create());
                    }
                }

                startCountdown();
            }

        }
    }

    public static void leaveLobby(Player player){
        User user = User.get(player);
        player.teleport(user.getFile().getLocation("pblocation"));
        player.getInventory().clear();

        Utils.restoreInventory(player);
        inLobby.remove(player);

        if(teamRed.contains(player)) {
            teamRed.remove(player);
        }else {
            teamBlue.remove(player);
        }

        broadcastPushbattle(color + "§o" + player.getName() + " §fis de lobby geleaved. §7§o(" + inLobby.size() + "/8)", true);
if(inLobby.size() == 1)
 {
            broadcastPushbattle("§8> §fStart van de game is afgebroken. Wachten op meer spelers...", false);
            Bukkit.getScheduler().cancelTask(ScoreboardManager.lobbyTimerTaskID.getTaskId());
            starting = false;
            tutorial = false;
            for(Player online : Bukkit.getOnlinePlayers()) {
                if (inLobby.contains(online)) {
                    online.teleport(redSpawn);
                    online.setGameMode(GameMode.SURVIVAL);
                }
            }

        }

        Bukkit.getServer().getScheduler().cancelTask(ScoreboardManager.pusbattleTask);
        ScoreboardManager.createMainBoard(player);
        for(Player online : Bukkit.getOnlinePlayers()) {
            if (inLobby.contains(online)) {
                ScoreboardManager.updatePBLobbyScoreBoard(online);
            }
        }

        NameUtils.giveTags(player);
    }

    public static void decideTeam(Player player) {

        String team = sortTeams();

        if(team.equalsIgnoreCase("red")) {
            teamRed.add(player);
        }else {
            teamBlue.add(player);
        }
    }

    public static boolean conditionsMet(Player player) {
        if(gameRunning) {
            player.sendMessage("§cEr is al een pushbattle game bezig. Wacht tot de game afgelopen is.");
            return false;
        }

        if(player.getGameMode() != GameMode.SURVIVAL) {
            player.sendMessage("§cJe moet gamemode survival hebben om te kunnen joinen");
            return false;
        }
        if(inLobby.contains(player)) {
            player.sendMessage("§cJe zit al in de pushbattle lobby.");
            return false;
        }
        if(inGame.contains(player)) {
            player.sendMessage("§cJe zit al in de game.");
            return false;
        }

        if(inLobby.size() == 8) {
            player.sendMessage("§cDe pushbattle lobby zit vol.");
        }

        return true;
    }

    public static String color = "#825AFE";
    public static String prefix = "#825AFE§lPushbattle §8> §f";


    public static void broadcastPushbattle(String message, boolean prefix) {
        for(Player inpb : Bukkit.getOnlinePlayers()) {
            if(inLobby.contains(inpb) || inGame.contains(inpb)) {
                if(prefix) {
                    inpb.sendMessage(MessageUtils.format("#825AFE§lPushbattle §8> §f" +  message));
                }else {
                    inpb.sendMessage(MessageUtils.format(message));

                }
            }
        }
    }

    public static String sortTeams() {
        if(teamRed.size() == 0) {
            return "red";
        }
        if(teamRed.size() == 1 && teamBlue.size() < 1) {
            return "blue";
        }
        if(teamBlue.size() == 1 && teamRed.size() < 2) {
            return "red";
        }
        if(teamRed.size() == 2 && teamBlue.size() < 2) {
            return "blue";
        }
        if(teamBlue.size() == 2 && teamRed.size() < 3) {
            return "red";
        }
        if(teamRed.size() == 3 && teamBlue.size() < 3) {
            return "blue";
        }
        if(teamBlue.size() == 3 && teamRed.size() < 4) {
            return "red";
        }
        if(teamRed.size() == 4 && teamBlue.size() < 4) {
            return "blue";
        }
        return null;
    }

}

