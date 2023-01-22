package me.xtimdevx.thomsehsmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SettingsCommand implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("settings")) {
            Player player = (Player) sender;
        }
        return true;
    }

    public Inventory openSettingmenu(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, "§3§nPersoonlijke Settings:");
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack deathcount = new ItemStack(Material.PAPER);
        ItemMeta deathcountMeta = deathcount.getItemMeta();
        deathcountMeta.setDisplayName("§8> §3§oDeath aantal in de death message §8<");
        lore.add(" ");
        lore.add("§8> §7§oRight click to use.");
        lore.add(" ");
        deathcountMeta.setLore(lore);
        deathcount.setItemMeta(deathcountMeta);
        inv.setItem(0, deathcount);
        lore.clear();
        
        player.openInventory(inv);
        return inv;
    }
}
