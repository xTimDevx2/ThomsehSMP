package me.xtimdevx.thomsehsmp.minigames.pushbattle;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PushbattleCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("pushbattle")) {
            if(args[0].equalsIgnoreCase("test")) {
                Player player = (Player) sender;
                PushbattleMain.joinLobby(player);
            }
        }

        return true;
    }

}
