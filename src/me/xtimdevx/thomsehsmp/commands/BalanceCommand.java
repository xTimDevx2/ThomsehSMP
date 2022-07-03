package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.managers.EconomyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    private final EconomyManager economyManager = new EconomyManager();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage("§8> §fYour Balance: §3" + economyManager.getBalance(player) + "§f$");
        return true;
    }
}
