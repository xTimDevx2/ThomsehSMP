package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CheckHomesCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(!player.hasPermission("smp.command.checkhomes")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("§cUsage: /checkhomes <player>");
            return true;
        }

        if(args.length == 1) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            User tuser = User.get(target);

            if(tuser.isNew()) {
                player.sendMessage("§cThis player has not joined before.");
                return true;
            }

            List<String> strings = tuser.getFile().getStringList("homelist");

            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lHome List");
            for(String entry : strings) {
                if(entry.isEmpty()) {
                    player.sendMessage("§fNo homes yet..");
                }else {
                    ComponentBuilder builder2 = new ComponentBuilder("");
                    builder2.append("§8- §f" + entry);
                    builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/checkhomes " + target.getName() + " " + entry));
                    builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aClick hier voor meer info over home §n" + entry)}));
                    player.spigot().sendMessage(builder2.create());
                }
            }
            player.sendMessage("§8§m----------------------------------------------------");
        }

        if(args.length == 2 ){
            String homename = args[1];
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            User tuser = User.get(target);

            List<String> strings = tuser.getFile().getStringList("homelist");

            if(!strings.contains(homename)) {
                player.sendMessage("§cError: This player does not have this home.");
                return true;
            }


            double x = tuser.getFile().getDouble("home." + homename + ".x");
            double y = tuser.getFile().getDouble("home." + homename + ".y");
            double z = tuser.getFile().getDouble("home." + homename + ".z");
            String pitchstring = tuser.getFile().getString("home." + homename + ".pitch");
            String yawstring = tuser.getFile().getString("home." + homename + ".yaw");
            String world = tuser.getFile().getString("home." + homename + ".world");

            float pitch = Float.parseFloat(pitchstring);
            float yaw = Float.parseFloat(yawstring);

            Location homeloc = new Location(Bukkit.getWorld(world), x ,y, z);
            homeloc.setPitch(pitch);
            homeloc.setYaw(yaw);

            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§f§l" + args[0] + "'s home");
            player.sendMessage(" ");
            player.sendMessage("§8» §fName: §o" + homename);
            player.sendMessage("§8» §fCoords: §ox:" + Math.round(x) + " y:" + Math.round(y) + " z:" + Math.round(z));
            ComponentBuilder builder2 = new ComponentBuilder("");
            builder2.append("§8» §aClick here to teleport in gamemode 3")
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gamemode spectator"))
                    .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/atp " + x + " " + y + " " + z));
            player.spigot().sendMessage(builder2.create());
            player.sendMessage("§8§m----------------------------------------------------");


        }

        return true;
    }
}
