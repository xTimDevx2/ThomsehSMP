package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.managers.EconomyManager;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class WithdrawCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("withdraw")) {
            Player player = (Player) sender;
            User user = User.get(player);

            if(args.length == 0) {
                player.sendMessage("§cUsage: /withdraw <aantal>");
                return true;
            }

            int num;
            try {
                num = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex){
                player.sendMessage("§cYou need to enter a number for this to work.");
                return true;
            }

            EconomyManager manager = new EconomyManager();

            if (!manager.hasEnoughBalance(player, num)) {
                player.sendMessage("§cThere is not enough money in your account.");
                return true;
            }

            ArrayList<String> lore = new ArrayList<>();
            ItemStack moneyCheque = new ItemStack(Material.PAPER);
            ItemMeta moneyChequeMeta = moneyCheque.getItemMeta();
            moneyChequeMeta.setDisplayName("§8> §3§l" + num + " ⛀ Cheque §8< §7§o(Right-click to claim)");
            lore.add("§8§m-------------------------");
            lore.add("§3§lMoney Cheque");
            lore.add("§f" + num + " ⛀");
            lore.add("§7§oRight-click to claim.");
            lore.add("§8§m-------------------------");

            moneyChequeMeta.setLore(lore);

            moneyCheque.setItemMeta(moneyChequeMeta);

            lore.clear();

            player.sendMessage("§8> §fYou withdrew §3§o" + num + " §f⛀ from your account.");





            Utils.giveItem(player, moneyCheque);

            manager.removeBalance(player, num);

        }
        return true;
    }
}
