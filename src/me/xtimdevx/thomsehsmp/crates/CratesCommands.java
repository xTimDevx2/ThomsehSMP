package me.xtimdevx.thomsehsmp.crates;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;

public class CratesCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("givekey")) {
            Player player = (Player) sender;
            if(!player.hasPermission("smp.command.givekey")) {
                player.sendMessage("§CYou don't have permissions to use this.");
                return true;
            }
            if (args.length == 0 ) {
                player.sendMessage("§cERROR: Missing arguments: /givekey <player>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if(target == null) {
                player.sendMessage("§cERROR: Player is not online.");
                return true;
            }

            ItemStack questkey = new ItemStack (Material.GOLDEN_CARROT);
            ItemMeta questkeyMeta = questkey.getItemMeta();
            questkeyMeta.setDisplayName("§8> §6§lDaily Crate Key §8<");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("§8§m-------------------------");
            lore.add("§fRight click §3Daily Crate");
            lore.add("§fto recieve your rewards.");
            lore.add("§3§o/warp crates");
            lore.add("§8§m-------------------------");

            questkeyMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);

            questkeyMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            questkeyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            questkeyMeta.setLore(lore);
            questkey.setItemMeta(questkeyMeta);
            target.getInventory().addItem(questkey);
            lore.clear();
            
            player.sendMessage("§8> §fYou sent a §3daily §fkey to §3" + target.getName() + "§f.");
            target.sendMessage("§8> §fYou recieved a §3daily §fkey§f.");

        }
        return true;
    }
}
