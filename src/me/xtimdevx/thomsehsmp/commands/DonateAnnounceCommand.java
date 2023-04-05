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
                        Bukkit.broadcastMessage("§8§m----------------------------------------------------");
                        MessageUtils.broadcastCenteredMessage("§3§lDonation!");
                        Bukkit.broadcastMessage(" ");
                        MessageUtils.broadcastCenteredMessage("§3§o" + player.getName() + " §fhas donated to the server!");
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§fThey bought the #FFD786§lG#FFCB62§lo#FCBB39§ll#FFAC06§ld §frank!"));
                        Bukkit.broadcastMessage(" ");
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§3§nstore.thomseh.live"));
                        MessageUtils.broadcastCenteredMessage(MessageUtils.format("§d§l<3"));
                        Bukkit.broadcastMessage("§8§m----------------------------------------------------");

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
                                player.sendMessage("§6§lThank you for donating!");
                                player.sendMessage(" ");
                                player.sendMessage("§fThank you, §6§o" + player.getName() + "§f, for your donation!");
                                player.sendMessage(MessageUtils.format("§fYou have bought the #FFD786§lG#FFCB62§lo#FCBB39§ll#FFAC06§ld §frank."));
                        }
                    },40L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {

                            player.sendMessage(" ");
                            player.sendMessage("Thank you for your donation! It's because of donations like yours that we can continue to exist as a server.");
                            player.sendMessage("We are truly grateful, and want to emphasize just how much we appreciate your support. Your contribution helps us to provide a better experience for all of our players. Thank you again for your generosity!");

                        }
                    },100L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                                player.sendMessage(" ");
                            player.sendMessage("Congratulations on being a badass! Here are the features that you have unlocked:");
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
                            player.sendMessage("Great news! As a donor, you are now allowed to build in the donor zone around spawn.");

                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },280L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage("As a donor, you now receive 1 extra crate key per day!");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },320L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage("As a donor, you will get early access to season releases!");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },360L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage("As a donor, you have the ability to heal yourself once every 60 minutes!");
                            player.sendMessage("§7§oTip: /heal");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },400L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage("As a donor, you can now access a virtual crafting table once every 2 minutes!");
                            player.sendMessage("§7§oTip: /craft");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },440L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage("Most importantly, by donating, you are supporting our server! We couldn't do it without you, and we appreciate your contribution.");
                            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_1, 1, 1);
                        }
                    },480L);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.sendMessage("That's all for now. We'll let you get back to playing. Once again, a huge thank you from the entire Origami team!");

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
