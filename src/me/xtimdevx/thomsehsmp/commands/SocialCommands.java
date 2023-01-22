package me.xtimdevx.thomsehsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SocialCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("discord")) {
            //https://discord.gg/gquF6tkb3F

            player.sendMessage("§8> §fDiscord Invite: §odiscord.gg/gquF6tkb3F");
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("twitch")) {
            player.sendMessage("§8> §fTwitch: §otwitch.tv/thomseh");
            return true;
        }
        return true;
    }
}
