package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand implements CommandExecutor {

    public static int taskID = -1;
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
                player.sendMessage("§cERROR: This player is not online.");
                return true;
            }

            if(target.getName().equalsIgnoreCase(player.getName())) {
                player.sendMessage("§cERROR: You can't teleport to yourself.");
                return true;
            }

            User tuser = User.get(target);


            tuser.getFile().set("tpaincoming", player.getName());
            tuser.saveFile();
            if (!user.getFile().get("tparequest").equals("none")) {
                player.sendMessage("§cERROR: You already sent a teleport request, you need to wait 30 seconds before sending another one.");
                return true;
            }
            target.sendMessage("§8> §3§lTPA Request incoming..");
            target.sendMessage("§8> §3" + player.getName() + " §fis asking to be teleported to you.");

            ComponentBuilder builder2 = new ComponentBuilder("");
            builder2.append("§8> §aAccept");
            builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));
            builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aClick to accept the teleport request.")}));
            target.spigot().sendMessage(builder2.create());

            ComponentBuilder builder1 = new ComponentBuilder("");
            builder1.append("§8> §cDeny");
            builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));
            builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick to deny the teleport request.")}));
            target.spigot().sendMessage(builder1.create());

            player.sendMessage("§8> §fTeleport request sent, they have §330 §fseconds to accept.");
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
                    player.sendMessage("§8> §fTeleport request was not accepted.");
                }
            }, 600);
        }

        if (command.getName().equalsIgnoreCase("tpaccept")) {
            Player player = (Player) commandSender;
            User user = User.get(player);

            if (user.getFile().get("tpaincoming").equals("none")) {
                player.sendMessage("§cERROR: No incoming teleport requests.");
                return true;
            }

            Player target = Bukkit.getPlayer(user.getFile().getString("tpaincoming"));
            if (target == null) {
                player.sendMessage("§cERROR: This player is no longer online.");
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

            player.sendMessage("§8> §fYou accepted the teleport request.");
            target.sendMessage("§8> §fTeleporting you in §35 §fseconds. Do not move!");
            SpawnCommand.moving.add(target);
            taskIDtp = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (target == null) {
                        return;
                    }
                    target.sendMessage("§8> §fTeleporting you to §3" + player.getName() + "§f.");
                    target.teleport(player.getLocation());
                    SpawnCommand.moving.remove(target);
                }
            }, 100L);


        }
        if (command.getName().equalsIgnoreCase("tpdeny")) {
            Player player = (Player) commandSender;
            User user = User.get(player);

            if (user.getFile().get("tpaincoming").equals("none")) {
                player.sendMessage("§cERROR: No incoming teleport requests.");
                return true;
            }

            Player target = Bukkit.getPlayer(user.getFile().getString("tpaincoming"));
            if (target == null) {
                player.sendMessage("§cERROR: This player is no longer online.");
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

            player.sendMessage("§8> §fYou dennied the teleport request.");
            target.sendMessage("§8> §3" + player.getName() + " §fhas denied your teleport request");
        }
        return true;
    }
}
