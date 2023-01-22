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
import java.util.Random;

public class TriviaManager implements Listener {

    private static TriviaManager instance = new TriviaManager();

    private ArrayList<String> questions = new ArrayList<String>();
    public static ArrayList<String> answers = new ArrayList<String>();
    private BukkitRunnable task;

    public static int taskID = -1;


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
                Bukkit.broadcastMessage("§3§lTRIVIA! §f" + randomQuestion());
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
        task.runTaskTimer(Main.plugin, 700, 36000);

        //task.runTaskTimer(Main.plugin, 72000, 72000);

        questions.add("Maak de volgende zin in één keer af: Ork, ork, ork, soep eet je met een…");
        answers.add("lepel");

        questions.add("In welk land is het gebruikelijk om met borden in een restaurant te gooien?");
        answers.add("griekenland");

        questions.add("Hoe noem je het kind van je nicht?");
        answers.add("achternicht");

        questions.add("Welk dier zwemt achteruit?");
        answers.add("garnaal");

        questions.add("Wat is de meest gesproken taal ter wereld? Arabisch, Engels, Spaans of Mandarijn Chinees?");
        answers.add("mandarijn chinees");

        questions.add("Hoeveel zijden heeft een paralellogram?");
        answers.add("4");
        answers.add("4 zijden");

        questions.add("Wat wordt wel de sterkste spier van het menselijk lichaam genoemd?\n" +
                "– Het hart\n" +
                "– De tong\n" +
                "– De sluitspier");
        answers.add("tong");
        answers.add("de tong");

        questions.add("Waar of niet waar: Koeien geven meer melk als er een relax muziekje opstaat in de stal?");
        answers.add("waar");

        questions.add("Welk land ontbreekt in het onderstaande rijtje:\n" +
                "Sint Maarten, Saba, Bonaire, Nederland, Aruba, Curacao");
        answers.add("sint eustatius");

        questions.add("Kan een giraf zijn oren schoonlikken met zijn tong? Ja of Nee");
        answers.add("ja");

        questions.add("Wat is de langste rivier ter wereld?");
        answers.add("de nijl");

        questions.add("Maak het volgende spreekwoord af: Als de nood het hoogst is, ……");
        answers.add("is de redding nabij");

        questions.add("Wie knipperen er vaker met hun ogen, mannen of vrouwen?");
        answers.add("vrouwen");

        questions.add("Welk land wordt ook wel het land van de glimlach genoemd?");
        answers.add("thailand");

        questions.add("Hoeveel van onderstaande gebieden is geen land?\n" +
                "San Marino – Vaticaanstad – Liechtenstein – Monaco");
        answers.add("geen");

        questions.add("Welke kleur krijg je als je blauw en groen mengt?");
        answers.add("turquoise");

        questions.add("Wat is het grootste land ter wereld qua oppervlakte?");
        answers.add("rusland");

        questions.add("Hoe moet je je kleren vooral niet wassen als je niet wilt dat ze krimpen?");
        answers.add("te heet");

        questions.add("Wat is een ander woord voor gemalen vlees?");
        answers.add("gehakt");

        questions.add("Wie is de oppergod in de Griekse mythologie?");
        answers.add("zeus");

        questions.add("In welk land wonen de langste mensen?");
        answers.add("nederland");

        questions.add("Noem één van de bijnamen die Ronald Koeman kreeg als speler in Barcelona?");
        answers.add("sneeuwvlokje");
        answers.add("kuifje");

        questions.add("Wat is een duur woord voor kotsen of braken?");
        answers.add("vomeren");

        questions.add("In welk jaar schafte Nederland de slavernij af?");
        answers.add("1863");

        questions.add("Hoe noem je het speciaal voor blinden ontwikkelde lees- en schrijfalfabet?");
        answers.add("braille");

        questions.add("Wat is de hoofdstad van Gelderland?");
        answers.add("arnhem");

        questions.add("Welke voetballer is als enige Nederlander ooit tot wereldvoetballer van het jaar gekroond?");
        answers.add("marco van basten");

        questions.add("Wie vangt de meeste prooien in het wild, de mannetjesleeuw of de vrouwtjesleeuw?");
        answers.add("vrouwtjesleeuw");
        answers.add("de vrouwtjesleeuw");

        questions.add("In welk jaar werd de euro ingevoerd?");
        answers.add("2002");

        questions.add("Geef een ander woord voor consternatie?");
        answers.add("ontsteltenis");

        questions.add("Welk orgaan ligt tussen je longen en achter je borstbeen in?");
        answers.add("het hart");
        answers.add("hart");

        questions.add("Welk sterrenbeeld hoort bij de periode van 20 januari tot 18 februari?");
        answers.add("waterman");




        Main.plugin.getLogger().info("The trivia has been setup.");

    }

    private String randomQuestion() {
        Random rand = new Random();

        return questions.get(rand.nextInt(questions.size()));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        EconomyManager economyManager = new EconomyManager();

        if(playing) {

            String anwser = event.getMessage().toLowerCase().replace(".", " ");
            Player player = event.getPlayer();
            User user = User.get(player);

            if(answers.contains(anwser)) {

                Bukkit.getScheduler().cancelTask(taskID);

                Bukkit.broadcastMessage("§3§lTRIVIA! §3§o" + event.getPlayer().getName() + " §fheeft het juiste antwoord gegeven!");
                economyManager.addBalance(player, 200);
                player.sendMessage("§8> §fJe hebt §3§o200 ⛀ §fontvangen voor het winnen van trivia!");
                playing = false;
            }
        }
    }
}
