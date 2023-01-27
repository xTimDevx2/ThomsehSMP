package me.xtimdevx.thomsehsmp.managers;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TriviaManager implements Listener {

    private static TriviaManager instance = new TriviaManager();

    private HashMap<String, String> questions = new HashMap<>();
    public static ArrayList<String> answers = new ArrayList<String>();
    private BukkitRunnable task;

    public static int taskID = -1;

    public static String question;


    public static boolean playing;

    public static TriviaManager getInstance() {
        return instance;
    }

    public void startTrivia() {

        task = new BukkitRunnable() {
            public void run() {
                if(Bukkit.getOnlinePlayers().size() < 2) {
                    return;
                }
                question = randomQuestion();

                Bukkit.broadcastMessage("§3§lTRIVIA! §f" + question);
                Bukkit.broadcastMessage("§8> §fIedereen heeft §3§o30 §fseconden om het juiste antwoord te geven! §7§o(200 ⛀ prijs)");
                for(Player online : Bukkit.getOnlinePlayers()) {
                    online.playSound(online, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                }


                playing = true;


                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        playing = false;
                        Bukkit.broadcastMessage("§3§lTRIVIA! §f" + "Iedereen was te laat met antwoorden. Volgende keer beter!");
                    }
                }, 600);
            }
        };
        task.runTaskTimer(Main.plugin, 620, 620);

        //task.runTaskTimer(Main.plugin, 72000, 72000);

        questions.put("Maak de volgende zin in één keer af: Ork, ork, ork, soep eet je met een…", "lepel");
        questions.put("In welk land is het gebruikelijk om met borden in een restaurant te gooien?", "griekenland");


        Main.plugin.getLogger().info("The trivia has been setup.");

    }

    private String randomQuestion() {
        Random rand = new Random();

        Object randquest = questions.keySet().toArray()[new Random().nextInt(questions.keySet().toArray().length)];

        return randquest.toString();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        EconomyManager economyManager = new EconomyManager();

        if(playing) {


            String anwser = event.getMessage().toLowerCase().replace(".", " ");
            Player player = event.getPlayer();
            User user = User.get(player);


            if(questions.get(question).equalsIgnoreCase(anwser)) {

                Bukkit.getScheduler().cancelTask(taskID);

                Bukkit.broadcastMessage("§3§lTRIVIA! §3§o" + event.getPlayer().getName() + " §fheeft het juiste antwoord gegeven!");
                economyManager.addBalance(player, 200);
                player.sendMessage("§8> §fJe hebt §3§o200 ⛀ §fontvangen voor het winnen van trivia!");
                playing = false;
            }
        }
    }
}
