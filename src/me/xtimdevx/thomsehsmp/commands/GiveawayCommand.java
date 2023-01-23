package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class GiveawayCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, final String[] args) {
        Player player = (Player) sender;

        if(!player.hasPermission("smp.command.giveaway")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("§cUsage: /giveaway <type>");
            player.sendMessage("§cTypes: Rank, Crate");
            return true;
        }

        if(args[0].equalsIgnoreCase("rank")) {
            Player online = Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random())).findFirst().orElse(null);

            for(Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage("§9§lGiveaway §8> §9" + online.getName() + " §fheeft de §6§lGold §frank gewonen met een giveaway!");
            }
        }
        if(args[0].equalsIgnoreCase("crate")) {
            Player online = Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random())).findFirst().orElse(null);

            for(Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage("§9§lGiveaway §8> §9" + online.getName() + " §fheeft de §6§lDaily §fcrate key gewonen met een giveaway!");
            }

            ItemStack questkey = new ItemStack(Material.GOLDEN_CARROT);
            ItemMeta questkeyMeta = questkey.getItemMeta();
            questkeyMeta.setDisplayName("§8> §6§lDaily Crate Key §8<");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("§8§m-------------------------");
            lore.add("§fGebruik rechtermuisknop op §3Daily Crate");
            lore.add("§ftOm je rewards te krijgen.");
            lore.add("§3§o/crates §fom te teleporteren.");
            lore.add("§8§m-------------------------");

            questkeyMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);

            questkeyMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            questkeyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            questkeyMeta.setLore(lore);
            questkey.setItemMeta(questkeyMeta);
            Utils.giveItem(online, questkey);
            lore.clear();



        }
        return true;
    }
}
