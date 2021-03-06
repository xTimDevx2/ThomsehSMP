package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelhomeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("delhome")) {
            if(args.length == 0) {
                sender.sendMessage("§cUsage: /delhome <home>");
                return true;
            }
            String name = args[0];

            Player player = (Player) sender;
            User user = User.get(player);

            if(user.getFile().get("home." + name) == null) {
                player.sendMessage("§cError: Deze home bestaat niet.");
                return true;
            }

            user.getFile().set("home." + name, null);
            user.saveFile();

            Main.home.clear();
            Main.home.addAll(user.getFile().getStringList("homelist"));
            Main.home.remove(name);
            user.getFile().set("homelist", Main.home);
            user.saveFile();

            player.sendMessage("§8» §fJe hebt je home verwijdert. §7§o(Naam: " + name + ")");
        }
        return true;
    }
}
