package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SettingsCommand implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("settings")) {
            Player player = (Player) sender;

            openSettingmenu(player);
        }
        return true;
    }

    public Inventory openSettingmenu(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, "§3§nPersonal Settings:");
        ArrayList<String> lore = new ArrayList<String>();

        User user = User.get(player);

        if(user.getFile().get("settings.deathcounter") == null) {
            user.getFile().set("settings.deathcounter", true);
            user.saveFile();
        }

        ItemStack deathcount = new ItemStack(Material.PAPER);
        ItemMeta deathcountMeta = deathcount.getItemMeta();
        deathcountMeta.setDisplayName("§8> §3§oDeath count in death message §8<");
        lore.add(" ");
        lore.add("§8> §f§lTurn on or off showing your death counter in death messages.");
        lore.add(" ");
        lore.add("§8> §fStatus: " + (user.getFile().getBoolean("deathcounter") ? "§aEnabled" : "§cDisabled"));
        lore.add(" ");

        deathcountMeta.setLore(lore);
        deathcount.setItemMeta(deathcountMeta);
        inv.setItem(4, deathcount);
        lore.clear();
        
        player.openInventory(inv);
        return inv;
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        User user = User.get(player);

        if (player.getOpenInventory().getTitle().equalsIgnoreCase("§3§nPersonal Settings:")) {
            event.setCancelled(true);

            if (event.getSlot() == 4) {
                if (user.getFile().getBoolean("settings.deathcounter")) {
                    user.getFile().set("settings.deathcounter", false);
                    user.saveFile();

                    player.closeInventory();

                    player.sendMessage(MessageUtils.PREFIX + "You have disabled the death counter!");
                } else {
                    user.getFile().set("settings.deathcounter", true);
                    user.saveFile();

                    player.closeInventory();

                    player.sendMessage(MessageUtils.PREFIX + "You have enabled the death counter!");
                }
            }
        }
    }
}
