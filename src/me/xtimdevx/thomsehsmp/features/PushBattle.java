package me.xtimdevx.thomsehsmp.features;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class PushBattle implements CommandExecutor, Listener {

    public static boolean gameRunning;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("pushbattle")) {

        }
        return true;
    }
}
