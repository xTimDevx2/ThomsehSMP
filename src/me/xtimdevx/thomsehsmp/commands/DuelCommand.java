package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class DuelCommand implements CommandExecutor {

    public static ArrayList<Player> duel = new ArrayList<>();
    public static ArrayList<Player> duelInvite = new ArrayList<>();
    public static int taskID = -1;
    public static int taskID2 = -1;
    public static int taskID3 = -1;


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("duel")) {
            Player player = (Player) sender;
            User user = User.get(player);

            if (args.length == 0) {
                player.sendMessage("§c? /duel <player>");
                return true;
            }

            if(args[0].equalsIgnoreCase("accept")) {
                if (user.getFile().get("duelincoming").equals("none")) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cERROR: No incoming duel requests.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cERROR: Geen inkomende duel aanvraag.");
                    }
                    return true;
                }

                Player target = Bukkit.getPlayer(user.getFile().getString("duelincoming"));
                if (target == null) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cERROR: This player is no longer online.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cERROR: Deze speler is niet meer online.");
                    }
                    return true;
                }
                Bukkit.getScheduler().cancelTask(taskID);

                User tuser = User.get(target);
                tuser.getFile().set("duelrequest", "none");
                tuser.getFile().set("duelincoming", "none");
                user.getFile().set("duelincoming", "none");
                user.getFile().set("duelrequest", "none");
                user.saveFile();
                tuser.saveFile();

                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fYou accepted the teleport request.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fJe hebt de teleport aanvraag geaccepteerd.");
                }

                if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    target.sendMessage("§8> §fYour duel will start in §35 §fseconds. Get ready!");
                }
                if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                    target.sendMessage("§8> §fJe duel start in §35 §fseconden. Maak jezelf klaar!");
                }

                taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            target.sendTitle("§3§lDuel Starting in", " §4§l5");
                            player.sendTitle("§3§lDuel Starting in", " §4§l5");
                        }
                        if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                            target.sendTitle("§3§lDuel Start in", "§4§l5");
                            player.sendTitle("§3§lDuel Start in", "§4§l5");
                        }
                        target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                    }
                }, 0L);
                taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            target.sendTitle("§3§lDuel Starting in", " §44");
                            player.sendTitle("§3§lDuel Starting in", " §44");
                        }
                        if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                            target.sendTitle("§3§lDuel Start in", "§44");
                            player.sendTitle("§3§lDuel Start in", "§44");
                        }
                        target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                    }
                }, 20L);
                taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            target.sendTitle("§3§lDuel Starting in", " §c3");
                            player.sendTitle("§3§lDuel Starting in", " §c3");
                        }
                        if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                            target.sendTitle("§3§lDuel Start in", "§c3");
                            player.sendTitle("§3§lDuel Start in", "§c3");
                        }
                        target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                    }
                }, 40L);
                taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            target.sendTitle("§3§lDuel Starting in", " §e2");
                            player.sendTitle("§3§lDuel Starting in", " §e2");
                        }
                        if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                            target.sendTitle("§3§lDuel Start in", "§e2");
                            player.sendTitle("§3§lDuel Start in", "§e2");
                        }
                        target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                    }
                }, 60L);
                taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            target.sendTitle("§3§lDuel Starting in", " §a1");
                            player.sendTitle("§3§lDuel Starting in", " §a1");
                        }
                        if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                            target.sendTitle("§3§lDuel Start in", "§a1");
                            player.sendTitle("§3§lDuel Start in", "§a1");
                        }
                        target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                    }
                }, 80L);
                taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            target.sendTitle("§3§lDuel Starting", " §a§lNOW");
                            target.sendMessage("§8> §fYou have §c5 §fminutes to finish your duel!");
                        }
                        if(user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            player.sendTitle("§3§lDuel Starting", " §a§lNOW");
                            player.sendMessage("§8> §fYou have §c5 §fminutes to finish your duel!");

                        }
                        if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                            target.sendTitle("§3§lDuel Start", "§a§lNU");
                            target.sendMessage("§8> §fJe hebt §c5 §fminuten om je duel af te maken!");

                        }
                        if(user.getLanguage().equalsIgnoreCase("DUTCH")) {
                            player.sendTitle("§3§lDuel Start", "§a§lNU");
                            player.sendMessage("§8> §fJe hebt §c5 §fminuten om je duel af te maken!");
                        }
                        target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                        duel.add(player);
                        duel.add(target);
                        player.setHealth(20);
                        target.setHealth(20);
                        player.setFoodLevel(20);
                        target.setFoodLevel(20);
                        user.getFile().set("DuelTarget", target.getName());
                        tuser.getFile().set("DuelTarget", player.getName());
                        tuser.saveFile();
                        user.saveFile();


                        taskID3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                    target.sendMessage(MessageUtils.PREFIX + "Ending your duel!");
                                    target.sendMessage("§8> §fYou took too long to finish your duel.");
                                }
                                if(user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                                    player.sendMessage(MessageUtils.PREFIX + "Ending your duel!");
                                    player.sendMessage("§8> §fYou took too long to finish your duel.");
                                }
                                if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                                    target.sendMessage(MessageUtils.PREFIX + "We beëindigen u duel!");
                                    target.sendMessage("§8> §fU heeft te lang over u duel gedaan.");
                                }
                                if(user.getLanguage().equalsIgnoreCase("DUTCH")) {
                                    player.sendMessage(MessageUtils.PREFIX + "We beëindigen u duel!");
                                    player.sendMessage("§8> §fU heeft te lang over u duel gedaan.");
                                }

                                DuelCommand.duel.remove(player);
                                DuelCommand.duel.remove(target);
                                DuelCommand.duelInvite.remove(player);
                                DuelCommand.duelInvite.remove(target);
                                user.getFile().set("DuelTarget", null);
                                tuser.getFile().set("DuelTarget", null);
                                player.setHealth(20);
                                target.setHealth(20);
                                player.setFoodLevel(20);
                                target.setFoodLevel(20);
                            }
                        },6000);

                    }
                }, 100L);
                return true;
            }
            if(args[0].equalsIgnoreCase("deny")) {
                if (user.getFile().get("duelincoming").equals("none")) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cERROR: No incoming duel request.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cERROR: Geen inkomende duel aanvraag.");
                    }
                    return true;
                }

                Player target = Bukkit.getPlayer(user.getFile().getString("duelincoming"));
                if (target == null) {
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§cERROR: This player is no longer online.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§cERROR: Deze speler is niet meer online.");
                    }
                    return true;
                }
                Bukkit.getScheduler().cancelTask(taskID);

                User tuser = User.get(target);
                tuser.getFile().set("duelrequest", "none");
                tuser.getFile().set("duelincoming", "none");
                user.getFile().set("duelincoming", "none");
                user.getFile().set("duelrequest", "none");
                duelInvite.remove(player);
                user.saveFile();
                tuser.saveFile();

                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fYou denied the duel request.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fJe hebt de duel aanvraag geweigerd.");
                }

                if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    target.sendMessage("§8> §3" + player.getName() + " §fdenied your duel request.");
                }
                if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                    target.sendMessage("§8> §3" + player.getName() + " §fheeft je duel aanvraag geweigerd.");
                }
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cError: This player is not online.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cError: Deze speler is niet online.");
                }
                return true;
            }
            if (duelInvite.contains(target)) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cError: This player has already been invited to a duel.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cError: Deze speler is al geinviteerd voor een duel.");
                }
                return true;
            }
            if(target == player) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cERROR: You can't duel yourself.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cERROR: Je kan jezelf niet dueleren.");
                }
                return true;
            }

            duelInvite.add(target);

            User tuser = User.get(target);

            if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                target.sendMessage("§8> §3§lDuel request..");
                target.sendMessage("§8> §3" + player.getName() + " §fwants to duel you!");

                ComponentBuilder builder2 = new ComponentBuilder("");
                builder2.append("§8> §aAccept");
                builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept"));
                builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aClick here to accept this request.")}));
                target.spigot().sendMessage(builder2.create());

                ComponentBuilder builder1 = new ComponentBuilder("");
                builder1.append("§8> §cDeny");
                builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel deny"));
                builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick here to deny this request.")}));
                target.spigot().sendMessage(builder1.create());
            }
            if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                target.sendMessage("§8> §3§lDuel Aanvraag..");
                target.sendMessage("§8> §3" + player.getName() + " §fwil met je dueleren!");

                ComponentBuilder builder2 = new ComponentBuilder("");
                builder2.append("§8> §aAccepteren");
                builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept"));
                builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aKlik Hier om het aanvraag te accepteren.")}));
                target.spigot().sendMessage(builder2.create());

                ComponentBuilder builder1 = new ComponentBuilder("");
                builder1.append("§8> §cWeigeren");
                builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel deny"));
                builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om het aanvraag te weigeren.")}));
                target.spigot().sendMessage(builder1.create());
            }
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8> §fDuel request sent, they have §330 §fseconds to respond to it.");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8> §fDuel aanvraag verstuurd, ze hebben §330 §fseconden om te accepteren.");
            }

            user.getFile().set("duelrequest", target.getName());
            user.saveFile();
            tuser.getFile().set("duelincoming", player.getName());
            tuser.saveFile();

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    tuser.getFile().set("duelrequest", "none");
                    tuser.getFile().set("duelincoming", "none");
                    user.getFile().set("duelincoming", "none");
                    user.getFile().set("duelrequest", "none");
                    user.saveFile();
                    tuser.saveFile();
                    duelInvite.remove(player);
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§8> §fDuel request was not accepted.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§8> §fDuel aanvraag was niet geaccepteerd.");
                    }
                }
            }, 600);
        }
        return true;
    }
}
