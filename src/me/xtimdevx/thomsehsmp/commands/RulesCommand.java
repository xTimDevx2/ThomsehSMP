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

        if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lServer Rules!");
            MessageUtils.sendCenteredMessage(player, "§fGriefing is §cnot §fallowed.");
            MessageUtils.sendCenteredMessage(player, "§fPvP is §cdisabled§f!");
            MessageUtils.sendCenteredMessage(player, "§fUsing fire, lava, gravel damage is §cnot §fallowed.");
            MessageUtils.sendCenteredMessage(player, "§fThe moderators are §aalways §fright.");
            MessageUtils.sendCenteredMessage(player, "§fBe respectfull to other players.");
            MessageUtils.sendCenteredMessage(player, "§fExcessive swearing is §cnot §fallowed.");
            MessageUtils.sendCenteredMessage(player, "§fSexism is §cnot§f allowed.");
            MessageUtils.sendCenteredMessage(player, "§fRacism is §cnot§f allowed.");
            MessageUtils.sendCenteredMessage(player, "§fScamming other players is §cnot§f allowed.");
            MessageUtils.sendCenteredMessage(player, "§fDon't advertise for your own server.");
            player.sendMessage("§8§m----------------------------------------------------");
        }
        if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lServer Regels!");
            MessageUtils.sendCenteredMessage(player, "§fGriefen is §cniet §ftoegestaan.");
            MessageUtils.sendCenteredMessage(player, "§fPvP staat §cuit§f!");
            MessageUtils.sendCenteredMessage(player, "§fHet gebruiken van vuur, lava, gravel damage is §cniet §ftoegestaan.");
            MessageUtils.sendCenteredMessage(player, "§fDe moderators hebben §aaltijd §fgelijk.");
            MessageUtils.sendCenteredMessage(player, "§fWees respectvol tegen andere spelers.");
            MessageUtils.sendCenteredMessage(player, "§fExcessief schelden is §cniet §ftoegestaan.");
            MessageUtils.sendCenteredMessage(player, "§fSexism is §cniet§f toegestaan.");
            MessageUtils.sendCenteredMessage(player, "§fRacisme is §cniet§f toegestaan.");
            MessageUtils.sendCenteredMessage(player, "§fScammen bij andere spelers is §cniet§f toegestaan.");
            MessageUtils.sendCenteredMessage(player, "§fGeen reclame maken van eigen servers of sociale media.");
            player.sendMessage("§8§m----------------------------------------------------");
        }
        return true;
    }
}
