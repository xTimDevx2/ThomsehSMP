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
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cYou need to type a number for this to work.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cJe moet een nummer ingeven om geld af te halen.");
                }
                return true;
            }

            EconomyManager manager = new EconomyManager();

            if(!manager.hasEnoughBalance(player, num)) {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§cThere is not enough money on your account.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§cEr staat niet genoeg geld op je rekening om dit af te halen.");
                }
                return true;
            }

            ArrayList<String> lore = new ArrayList<String>();
            ItemStack money500 = new ItemStack(Material.PAPER);
            ItemMeta money500meta = money500.getItemMeta();
            money500meta.setDisplayName("§8> §3§l" + num + " ⛀ Cheque §8< §7§o(Right click)");
            lore.add("§8§m-------------------------");
            lore.add("§3§lMoney Cheque");
            lore.add("§f" + num + " ⛀");
            lore.add("§7§oRight click to claim.");
            lore.add("§8§m-------------------------");

            money500meta.setLore(lore);

            money500.setItemMeta(money500meta);

            lore.clear();

            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8> §fYou withdrew §3§o" + num + " §f⛀ from your account.");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8> §fJe hebt §3§o" + num + " §f⛀ afgehaald.");
            }

            Utils.giveItem(player, money500);

            manager.removeBalance(player, num);

        }
        return true;
    }
}
