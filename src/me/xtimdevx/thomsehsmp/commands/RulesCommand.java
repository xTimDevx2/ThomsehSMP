package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);

        player.sendMessage("§8§m----------------------------------------------------");
        MessageUtils.sendCenteredMessage(player, "§3§lServer Rules!");
        MessageUtils.sendCenteredMessage(player, "§fGriefing is §cnot§f allowed.");
        MessageUtils.sendCenteredMessage(player, "§fPvP is §cdisabled§f!");
        MessageUtils.sendCenteredMessage(player, "§fUsing fire, lava, gravel damage is §cnot§f allowed.");
        MessageUtils.sendCenteredMessage(player, "§fThe moderators are §always§f right.");
        MessageUtils.sendCenteredMessage(player, "§fBe respectful to other players.");
        MessageUtils.sendCenteredMessage(player, "§fExcessive cursing is §cnot§f allowed.");
        MessageUtils.sendCenteredMessage(player, "§fSexism is §cnot§f allowed.");
        MessageUtils.sendCenteredMessage(player, "§fRacism is §cnot§f allowed.");
        MessageUtils.sendCenteredMessage(player, "§fScamming other players is §cnot§f allowed.");
        MessageUtils.sendCenteredMessage(player, "§fNo advertising of own servers or social media.");
        player.sendMessage("§8§m----------------------------------------------------");
        return true;
    }
}
