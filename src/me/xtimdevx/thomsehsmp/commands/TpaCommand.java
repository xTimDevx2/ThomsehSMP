package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand implements CommandExecutor {

    public static int taskID = -1;
    public static int taskID2 = -1;
    public static int taskID3 = -1;
    public static int taskID4 = -1;
    public static int taskID5 = -1;
    public static int taskID6 = -1;
    public static int taskIDtp = -1;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("tpa")) {
            Player player = (Player) commandSender;
            User user = User.get(player);

            if (args.length == 0) {
                player.sendMessage("§cERROR: Missing arguments: /tpa <player>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cERROR: This player is not online.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cERROR: Deze speler is niet online.");
                }
                return true;
            }

            if(target.getName().equalsIgnoreCase(player.getName())) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cERROR: You can't teleport to yourself.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cERROR: Je kan niet naar jezelf teleporteren.");
                }
                return true;
            }

            User tuser = User.get(target);


            tuser.getFile().set("tpaincoming", player.getName());
            tuser.saveFile();
            if (!user.getFile().get("tparequest").equals("none")) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cERROR: You already sent a teleport request, you have to wait 30 more seconds before sending a new one.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cERROR: Je hebt al een teleport request gestuurd, je moet 30 seconden wachten voor je een nieuwe stuurt.");
                }
                return true;
            }
            if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                target.sendMessage("§8> §3§lTeleport request..");
                target.sendMessage("§8> §3" + player.getName() + " §fis asking to teleport to you.");

                ComponentBuilder builder2 = new ComponentBuilder("");
                builder2.append("§8> §aAccept");
                builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));
                builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aClick here to accept this request.")}));
                target.spigot().sendMessage(builder2.create());

                ComponentBuilder builder1 = new ComponentBuilder("");
                builder1.append("§8> §cDeny");
                builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));
                builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick here to deny this request.")}));
                target.spigot().sendMessage(builder1.create());
            }
            if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                target.sendMessage("§8> §3§lTeleport Aanvraag..");
                target.sendMessage("§8> §3" + player.getName() + " §fvraagt om naar jou te teleporteren.");

                ComponentBuilder builder2 = new ComponentBuilder("");
                builder2.append("§8> §aAccepteren");
                builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));
                builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aKlik Hier om het aanvraag te accepteren.")}));
                target.spigot().sendMessage(builder2.create());

                ComponentBuilder builder1 = new ComponentBuilder("");
                builder1.append("§8> §cWeigeren");
                builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));
                builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om het aanvraag te weigeren.")}));
                target.spigot().sendMessage(builder1.create());
            }

            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8> §fTeleport request sent, they have §330 §fseconds to respond to it.");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8> §fTeleport aanvraag verstuurd, ze hebben §330 §fseconden om te accepteren.");
            }
            user.getFile().set("tparequest", target.getName());
            user.saveFile();

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    tuser.getFile().set("tparequest", "none");
                    tuser.getFile().set("tpaincoming", "none");
                    user.getFile().set("tpaincoming", "none");
                    user.getFile().set("tparequest", "none");
                    user.saveFile();
                    tuser.saveFile();
                    if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        player.sendMessage("§8> §fTeleport request was not accepted.");
                    }
                    if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                        player.sendMessage("§8> §fTeleport aanvraag was niet geaccepteerd.");
                    }
                }
            }, 600);
        }

        if (command.getName().equalsIgnoreCase("tpaccept")) {
            Player player = (Player) commandSender;
            User user = User.get(player);

            if (user.getFile().get("tpaincoming").equals("none")) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cERROR: No incoming teleport requests.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cERROR: Geen inkomende teleport aanvraag.");
                }
                return true;
            }

            Player target = Bukkit.getPlayer(user.getFile().getString("tpaincoming"));
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
            tuser.getFile().set("tparequest", "none");
            tuser.getFile().set("tpaincoming", "none");
            user.getFile().set("tpaincoming", "none");
            user.getFile().set("tparequest", "none");
            user.saveFile();
            tuser.saveFile();

            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8> §fYou accepted the teleport request.");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8> §fJe hebt de teleport aanvraag geaccepteerd.");
            }

            if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                target.sendMessage("§8> §fWe are teleporting you in §35 §fseconds. Do not move!");
            }
            if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                target.sendMessage("§8> §fWe teleporteren je in §35 §fseconden. Niet bewegen!");
            }

            taskID6 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lTeleport", "§8> §45 §8<");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 0L);
            taskID5 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lTeleport", "§8> §44 §8<");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 20L);
            taskID4 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lTeleport", "§8> §c3 §8<");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 40L);
            taskID3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lTeleport", "§8> §e2 §8<");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 60L);
            taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lTeleport", "§8> §a1 §8<");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                }
            }, 80L);
            SpawnCommand.moving.add(target);
            taskIDtp = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (target == null) {
                        return;
                    }

                    Location location = target.getLocation();
                    target.sendTitle("", "");


                    double bx = location.getX();
                    double by = location.getY();
                    double bz = location.getZ();
                    float bPitch = location.getPitch();
                    float bYaw = location.getYaw();
                    String bWorld = location.getWorld().getName();

                    tuser.getFile().set("back.x", bx);
                    tuser.getFile().set("back.y", by);
                    tuser.getFile().set("back.z", bz);
                    tuser.getFile().set("back.pitch", bPitch);
                    tuser.getFile().set("back.yaw", bYaw);
                    tuser.getFile().set("back.world", bWorld);
                    tuser.saveFile();


                    if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                        target.sendMessage("§8> §fWe are teleporting you to §3" + player.getName() + "§f.");
                    }
                    if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                        target.sendMessage("§8> §fWe teleporteren je naar §3" + player.getName() + "§f.");
                    }
                    target.teleport(player.getLocation());
                    SpawnCommand.moving.remove(target);
                }
            }, 100L);


        }
        if (command.getName().equalsIgnoreCase("tpdeny")) {
            Player player = (Player) commandSender;
            User user = User.get(player);

            if (user.getFile().get("tpaincoming").equals("none")) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cERROR: No incoming teleport request.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cERROR: Geen inkomende teleport aanvraag.");
                }
                return true;
            }

            Player target = Bukkit.getPlayer(user.getFile().getString("tpaincoming"));
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
            tuser.getFile().set("tparequest", "none");
            tuser.getFile().set("tpaincoming", "none");
            user.getFile().set("tpaincoming", "none");
            user.getFile().set("tparequest", "none");
            user.saveFile();
            tuser.saveFile();

            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8> §fYou denied the teleport request.");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8> §fJe hebt de teleport aanvraag geweigerd.");
            }

            if (tuser.getLanguage().equalsIgnoreCase("ENGLISH")) {
                target.sendMessage("§8> §3" + player.getName() + " §fdenied your teleport request.");
            }
            if (tuser.getLanguage().equalsIgnoreCase("DUTCH")) {
                target.sendMessage("§8> §3" + player.getName() + " §fheeft je teleport aanvraag geweigerd.");
            }
        }
        return true;
    }
}
