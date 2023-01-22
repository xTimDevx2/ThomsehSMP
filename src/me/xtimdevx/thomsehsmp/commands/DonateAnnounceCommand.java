package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class DonateAnnounceCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("donateannounce")) {
            if(sender.hasPermission("smp.command.donateannounce")) {
                if(args.length < 2) {
                    sender.sendMessage("Usage: /donateannounce <package> <player>");
                    sender.sendMessage("Packages: gold");
                    return true;
                }

                if(args[0].equalsIgnoreCase("gold")) {
                    Player player = (Player) Bukkit.getPlayer(args[1]);
                    User user = User.get(player);
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

                    for(Player online : Bukkit.getOnlinePlayers()) {
                        online.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 20, 1);
                    }
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        Bukkit.broadcastMessage("§8§m----------------------------------------------------");
                        MessageUtils.broadcastCenteredMessage("§3§lDonation!");
                        Bukkit.broadcastMessage(" ");
                        MessageUtils.broadcastCenteredMessage("§3§o" + player.getName() + " §fhas donated on the server!");
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§fThey bought the #FFD786§lG#FFCB62§lo#FCBB39§ll#FFAC06§ld §frank!"));
                        Bukkit.broadcastMessage(" ");
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§3§nstore.thomseh.live"));
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§d§l<3"));
                        Bukkit.broadcastMessage("§8§m----------------------------------------------------");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        Bukkit.broadcastMessage("§8§m----------------------------------------------------");
                        MessageUtils.broadcastCenteredMessage("§3§lDonatie!");
                        Bukkit.broadcastMessage(" ");
                        MessageUtils.broadcastCenteredMessage("§3§o" + player.getName() + " §fheeft op de server gedoneerd!");
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§fDie kocht de #FFD786§lG#FFCB62§lo#FCBB39§ll#FFAC06§ld §frank!"));
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§fGeef hun een welverdiende §3§LGG §fin de chat!"));
                        Bukkit.broadcastMessage(" ");
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§3§nstore.thomseh.live"));
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§d§l<3"));
                        Bukkit.broadcastMessage("§8§m----------------------------------------------------");
                    }

                    Bukkit.dispatchCommand(console, "setrank " + player.getName() + " Gold -s");

                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1726272000, 128));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1726272000, 128));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1726272000, 128));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1726272000, 128));
                    Utils.inServerMessage.add(player.getName());

                    int taskID = -1;

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            Utils.clearChat(player);

                        }
                    },20L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage("§6§lThank you for donating!");
                                player.sendMessage(" ");
                                player.sendMessage("§fThank you §6§o" + player.getName() + " §ffor your donation!");
                                player.sendMessage(MessageUtils.format("§fYou bought the #FFD786§lG#FFCB62§lo#FCBB39§ll#FFAC06§ld §frank."));
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage("§6§lBedankt voor het donaten!");
                                player.sendMessage(" ");
                                player.sendMessage("§fBedankt §6§o" + player.getName() + " §fvoor je donatie!");
                                player.sendMessage(MessageUtils.format("§fJe hebt de rank #FFD786§lG#FFCB62§lo#FCBB39§ll#FFAC06§ld §fgekocht."));
                            }
                        }
                    },40L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {

                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage(" ");
                                player.sendMessage("§fBecause of donations like these we can keep excisting as a server!");
                                player.sendMessage("§fAnd we are  §6§lExtremely §fgratefull for that.");
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage(" ");
                                player.sendMessage("§fDankzij donaties zoals deze kunnen wij blijven bestaan als server!");
                                player.sendMessage("§fEn daar zijn we je §6§lEnorm §fdankbaar voor.");
                            }
                        }
                    },100L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage(" ");
                                player.sendMessage("§fThese are the features that you have unlocked by being a badass:");
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage(" ");
                                player.sendMessage("§fVerder zijn dit de dingen die jij hebt vrijgespeeld:");
                            }
                        }
                    },160L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage(" ");
                            player.sendMessage("§6§lFEATURES UNLOCKED");
                            player.sendMessage(" ");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 1, 1);
                        }
                    },200L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage("§f2 §6§lExtra §fhomes!");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },240L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage("§fFrom now on you are allowed to build in the §adonor zone §faround spawn.");

                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage("§fVanaf nu kan jij bouwen in de §adonor zone §frond spawn.");

                            }
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },280L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage("§fYou get §6§l1 §fextra crate key a day.");
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage("§fJe krijgt §6§l1 §fextra crate key per dag.");
                            }
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },320L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage("§fYou get §6§oEarly Access §fon season releases.");
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage("§fJe krijgt een §6§oEarly Access §fbij nieuwe updates.");
                            }
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },360L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage("§fYou are able to §6§lheal §fyourself. §7§o(Every 60 minutes)");
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage("§fJe kan jezelf §6§lhealen. §7§o(Elke 60 minuten)");
                            }
                            player.sendMessage("§7§oTip: /heal");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },400L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage("§fYou are able to open a virtual crafting table. §7§o(Every 2 minutes)");
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage("§fJe kan een virtuele crafting table openen. §7§o(Elke 2 minuten)");
                            }
                            player.sendMessage("§7§oTip: /craft");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },440L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage("§fThe most important! §d§lYou are supporting the server!");
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage("§fHet belangrijkste! §d§lJe support de server!");
                            }
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },480L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                player.sendMessage("§fThat was it §lfor now§f, we will let you continue on.");
                                player.sendMessage("§fOne last huge thank you from the entire §3§lOrigami §fteam!");
                            }
                            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                player.sendMessage("§fDit was het dan §lvoorlopig§f, we gaan je laten verder spelen.");
                                player.sendMessage("§fEnorm bedankt namens het hele §3§lOrigami §fteam!");
                            }

                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },540L);



                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.removePotionEffect(PotionEffectType.SLOW);
                            player.removePotionEffect(PotionEffectType.BLINDNESS);
                            player.removePotionEffect(PotionEffectType.INVISIBILITY);
                            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 1);
                            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);

                            Utils.inServerMessage.remove(player.getName());
                        }
                    },560L);
                }
            }
        }
        return true;
    }
}
