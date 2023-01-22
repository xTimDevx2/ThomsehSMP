package me.xtimdevx.thomsehsmp.quests;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.NameUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class QuestCommands implements CommandExecutor {

    private final QuestManager manager = new QuestManager();

    public List<String> ongoing = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("quest")) {
            if (args.length == 0) {
                manager.openQuestmenu(player);
                return true;
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("accept")) {
                    if (ongoing.contains(player.getName())) {
                        return true;
                    }
                    if (QuestManager.TimberRequest16LOG.contains(player.getUniqueId())) {
                        ongoing.add(player.getName());
                        int taskID = -1;
                        manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Ja?! Dat hoor ik graag!");
                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Je eerste taak zal zeer eenvoudig zijn:");
                                player.sendMessage("§8> §3§lTask Recieved:");
                                player.sendMessage("§8> §fVerzamel 16 logs en breng ze terug naar aaron.");
                            }
                        }, 50);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                            manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Dat is het! Oh en vergeet niet dat je §3§l/quest §fkan doen om je actieve tasks te bekijken.");
                            }
                        }, 100);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Oke veel success en dan zie ik je snel weer!");
                            }
                        }, 150);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                User user = User.get(player);
                                player.sendMessage("§8§m----------------------------------------------------");
                                MessageUtils.sendCenteredMessage(player, "§3§lQuest Started!");
                                MessageUtils.sendCenteredMessage(player, "§fAaron De Houthakker ");
                                player.sendMessage(" ");
                                MessageUtils.sendCenteredMessage(player, "§fHoofdstuk §31§8/§33");
                                MessageUtils.sendCenteredMessage(player, "§fVerzamel 16 logs en breng ze naar Aaron.");
                                player.sendMessage(" ");
                                MessageUtils.sendCenteredMessage(player, "§fGebruik §3§o/quest §fom je actieve tasks te bekijken.");
                                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendTitle("§3§lHoofdstuk 1", "§fHet Magische Hout");
                                player.sendMessage("§8§m----------------------------------------------------");

                                user.getFile().set("quest.TIMBER.started", true);
                                user.getFile().set("quest.TIMBER.TIMBER_16LOGS.active", true);
                                user.saveFile();

                                QuestManager.TimberRequest16LOG.remove(player.getUniqueId());

                                QuestManager.NPCTalking.remove(player.getName());

                                ongoing.remove(player.getName());
                            }

                        }, 190);
                    }
                    if (QuestManager.TimberRequestDiaAXE.contains(player.getUniqueId())) {
                        ongoing.add(player.getName());
                        int taskID = -1;
                        manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Okay laten we dan meteen beginnen.");

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "De man die je zoekt heet Baldemar De Smid, je vind hem in de blacksmith ten zuiden van ons.");
                            }
                        }, 50L);
                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Hij zal een paar items van je vragen om het gereedschap te maken. Je zal hem §3§o4 Diamonds§f,");
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "§3§o2 Sticks§f en een §3§obookshelf §fmoeten geven.");
                            }
                        }, 100L);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Als je al deze voorwerpen verzamelt hebt zoek Baldur dan op en zeg hem dat ik je gestuurd heb,");
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Als je de bijl hebt kom dan terug met mij praten en dan kijken we de volgende stap.");
                            }
                        }, 150L);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                User user = User.get(player);
                                player.sendMessage("§8§m----------------------------------------------------");
                                MessageUtils.sendCenteredMessage(player, "§3§lQuest Updated!");
                                MessageUtils.sendCenteredMessage(player, "§fAaron De Houthakker");
                                player.sendMessage(" ");
                                MessageUtils.sendCenteredMessage(player, "§fHoofdstuk §32§8/§33");
                                MessageUtils.sendCenteredMessage(player, "§fVerzamel 4 Diamonds, 2 Sticks en een bookshelf");
                                MessageUtils.sendCenteredMessage(player, "§fen breng deze naar Baldemar De Smid");
                                player.sendMessage(" ");
                                MessageUtils.sendCenteredMessage(player, "§fGebruik §3/quest §fom je actieve tasks te bekijken.");
                                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendTitle("§3§lHoofdstuk 2", "§fBaldemar De Smid!");
                                player.sendMessage("§8§m----------------------------------------------------");

                                user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.active", true);
                                user.getFile().set("quest.TIMBER.TIMBER_DIAMONDAXE.stage", 1);
                                user.saveFile();

                                QuestManager.TimberRequestDiaAXE.remove(player.getUniqueId());

                                QuestManager.NPCTalking.remove(player.getName());

                                ongoing.remove(player.getName());
                            }

                        }, 190);
                    }
                    if (QuestManager.TimberRequestSapling.contains(player.getUniqueId())) {
                        ongoing.add(player.getName());
                        int taskID = -1;
                        manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Perfect! De biomes zal je zelf nog moeten zoeken maar dat komt vast wel goed.");
                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Verzamel een Jungle, Accacia en Spruce sappling en breng deze terug naar mij.");
                            }
                        }, 50);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Veel success reiziger.");
                            }
                        }, 100);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                User user = User.get(player);
                                player.sendMessage("§8§m----------------------------------------------------");
                                MessageUtils.sendCenteredMessage(player, "§3§lQuest Updated!");
                                MessageUtils.sendCenteredMessage(player, "§fAaron De Houthakker ");
                                player.sendMessage(" ");
                                MessageUtils.sendCenteredMessage(player, "§fHoofdstuk §33§8/§33");
                                MessageUtils.sendCenteredMessage(player, "§fVerzamel een jungle, accacia en spruce sappling");
                                player.sendMessage(" ");
                                MessageUtils.sendCenteredMessage(player, "§fGebruik §3/quest §fom je actieve tasks te bekijken.");
                                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                player.sendTitle("§3§lHoofdstuk 3", "§f3 Verschillende Bossen");
                                player.sendMessage("§8§m----------------------------------------------------");

                                user.getFile().set("quest.TIMBER.TIMBER_3SAPLING.active", true);
                                user.saveFile();

                                QuestManager.TimberRequestSapling.remove(player.getUniqueId());

                                QuestManager.NPCTalking.remove(player.getName());

                                ongoing.remove(player.getName());
                            }

                        }, 150);
                    }
                }
                if (args[0].equalsIgnoreCase("deny")) {
                    if (ongoing.contains(player.getName())) {
                        return true;
                    }
                    if (!QuestManager.TimberRequest16LOG.contains(player.getUniqueId()) && !QuestManager.TimberRequestDiaAXE.contains(player.getUniqueId())) {
                        return true;
                    } else {
                        ongoing.add(player.getName());
                        manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Dat is jammer, als je je bedenkt kom dan zeker terug.");

                        int taskID = -1;

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                manager.sendNPCMessage(QuestManager.NPCS.AARON, player, "Tot nog eens reiziger.");
                            }
                        }, 40);

                        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                player.sendMessage("§8> §fJe hebt Aaron zijn request afgewezen.");
                            }
                        }, 80);
                        QuestManager.NPCTalking.remove(player.getName());
                        QuestManager.TimberRequestSapling.remove(player.getUniqueId());
                        QuestManager.TimberRequest16LOG.remove(player.getUniqueId());
                        QuestManager.TimberRequestDiaAXE.remove(player.getUniqueId());
                        ongoing.remove(player.getName());

                        return true;
                    }
                }
            }

        }
        return true;
    }
}


