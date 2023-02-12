package me.xtimdevx.thomsehsmp.managers;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TriviaManager implements Listener {

    private static TriviaManager instance = new TriviaManager();

    private static final Map<String, String> questions = new HashMap<>();
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
        task.runTaskTimer(Main.plugin, 72000, 72000);

        questions.put("Maak de volgende zin in één keer af: Ork, ork, ork, soep eet je met een…", "lepel");
        questions.put("In welk land is het gebruikelijk om met borden in een restaurant te gooien?", "griekenland");
        questions.put("Hoeveel tanden heeft een volwassen mens normaal?", "32");
        questions.put("Mean Motherfucker 50 cent werd tijdens zijn leven heel wat beschoten, welk is zijn meest bizarre beschoten lichaamsdeel?", "tong");
        questions.put("Voor de euro betaalde men in Finland met de ...", "mark");
        questions.put("Wat is de merknaam van het verzorgingsproduct bij uitstek voor baby's?", "zwitsal");
        questions.put("Over welke schouder gooien ze zout om geluk te wensen", "linker");
        questions.put("Waarvan is H&M de afkorting?", "hennes en mauritz");
        questions.put("In welk land gooit men uit gewoonte het glas kapot wanneer het leeg is?", "rusland");
        questions.put("Wat wordt in alle Europese landen op 1 mei gevierd? Dag van de ....", "arbeid");
        questions.put("Voor welk land staat de autosticker met de letter E?", "spanje");
        questions.put("In welke Europese stad vindt jaarlijks de grootste Love Parade van de wereld plaats?", "berlijn");
        questions.put("Hoeveel nullen heeft een biljoen?", "12");
        questions.put("Wie zegt hoe laat het is?", "sprekende klok");
        questions.put("Wat is de wortel uit 121?", "11");
        questions.put("In welk land rijden de wagens met het internationale kenteken SF", "finland");
        questions.put("Juist of Fout: Kalksteen zinkt als men het in het water legt", "fout");
        questions.put("Welke kleur hebben de meeste taxi s in New York", "geel");
        questions.put("Wanneer is Minecraft officieel gereleased?", "2011");
        questions.put("Wanneer werd Netherite Toegevoegd aan minecraft?", "1.16");
        questions.put("Wanneer Werd PVP veranderd, zodat je op de computer niet meer snel kon klikken?", "1.9");
        questions.put("Hoeveel water buckets heb je nodig voor een oneindige waterbron?", "2");
        questions.put("Hoe heet het bedrijf die Minecraft maakt?", "mojang");
        questions.put("Notch had een keer de big verkeerd gemaakt en dat zit nu nog in het spel hoe heet dat beest?", "creeper");
        questions.put("Hoe heet de Biome met klei en (heel veel) goud in de grond?", "messa");
        questions.put("Van welke Amerikaanse staat was Arnold Schwarzenegger gouverneur van 2003 tot 2011?", "californie");
        questions.put("Welk automerk is bekend om zijn snelle, exclusieve en peperdure wagens zoals de Veyron en de Chiron?", "bugatti");
        questions.put("In welke politieke ideologie streeft men naar een maatschappij zonder hogere macht of autoriteit?", "anarchisme");
        questions.put("Hoe noemen we de onweerstaanbare behoefte om brand te stichten, ook wel \"vuurzucht\" genoemd.", "pyromanie");
        questions.put("Welke partydrug wordt in straattaal o.m. \"Kit Kat\", \"Super K\", \"Special K\" of \"Vitamine K\" genoemd?", "ketamine");
        questions.put("Welke kleur heeft de teletubbie Laa-Laa?", "geel");
        questions.put("Welk nummer van The Rolling Stones begint met de tekst \"Ik zie een rode deur en ik wil die zwart geverfd zien\".", "paint it black");
        questions.put("Hoe heet de boosaardige tovenaar uit de klassieke Disney animatiefilm Aladdin?", "jafar");
        questions.put("Welke Amerikaanse rapster en ex-stripper is bekend van hits als \"WAP\", \"Girls Like You\" en \"Bodak Yellow\"?", "cardi b");
        questions.put("§c§lMoeilijk §fGeef mij het eerste couplet van het nederlandse volkslied, het Wilhelmus.", "wilhelmus van nassouwe ben ik, van duitsen bloed, den vaderland getrouwe blijf ik tot in den dood. een prinse van oranje ben ik, vrij, onverveerd, den koning van hispanje heb ik altijd geëerd.");
        //34



        Main.plugin.getLogger().info("The trivia has been setup.");

    }

    private String randomQuestion() {
        Random rand = new Random();

        Object randquest = questions.keySet().toArray()[new Random().nextInt(questions.keySet().toArray().length)];

        if(Settings.getInstance().getData().getStringList("awnsers").contains(randquest.toString())) {
            if(Settings.getInstance().getData().getStringList("awnsers").size() == 34) {
                Settings.getInstance().getData().getStringList("awnsers").clear();
                return randomQuestion();
            }
            return randomQuestion();
        }

        return randquest.toString();
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        EconomyManager economyManager = new EconomyManager();

        if(playing) {


            String anwser = event.getMessage().toLowerCase();
            Player player = event.getPlayer();
            User user = User.get(player);

            if(questions.get(question).equalsIgnoreCase(anwser)) {

                Bukkit.getScheduler().cancelTask(taskID);

                List<String> answerA = Settings.getInstance().getData().getStringList("awnsers");
                answerA.add(question);
                Settings.getInstance().getData().set("awnsers", answerA);
                Settings.getInstance().saveData();


                Bukkit.broadcastMessage("§3§lTRIVIA! §3§o" + event.getPlayer().getName() + " §fheeft het juiste antwoord gegeven!");
                economyManager.addBalance(player, 200);
                player.sendMessage("§8> §fJe hebt §3§o200 ⛀ §fontvangen voor het winnen van trivia!");
                playing = false;
            }
        }
    }
}
