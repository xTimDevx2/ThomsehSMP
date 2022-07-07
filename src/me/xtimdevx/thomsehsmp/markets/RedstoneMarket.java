package me.xtimdevx.thomsehsmp.markets;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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

public class RedstoneMarket implements Listener {

    MarketMethods marketMethods = new MarketMethods();

    public static Inventory openMarketRedstone(Player player) {
        Inventory inv = Bukkit.createInventory(player,  36, "§c§nAline's Redstone Market");
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack glass = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        
        inv.setItem(0, glass);
        inv.setItem(9, glass);
        inv.setItem(18, glass);
        inv.setItem(27, glass);
        inv.setItem(28, glass);
        inv.setItem(29, glass);
        inv.setItem(30, glass);
        inv.setItem(31, glass);
        inv.setItem(32, glass);
        inv.setItem(33, glass);
        inv.setItem(34, glass);
        inv.setItem(35, glass);
        //⛀⛁⛃⛂
        
        ItemStack redstone = new ItemStack(Material.REDSTONE);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName("§cRedstone");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 5 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 80 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 320 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        redstoneMeta.setLore(lore);
        redstone.setItemMeta(redstoneMeta);
        inv.setItem(1, redstone);
        lore.clear();

        ItemStack redstoneRepeater = new ItemStack(Material.REPEATER);
        ItemMeta redstoneRepeaterMeta = redstoneRepeater.getItemMeta();
        redstoneRepeaterMeta.setDisplayName("§cRedstone Repeater");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 20 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f3x = 60 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f5x = 100 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        redstoneRepeaterMeta.setLore(lore);
        redstoneRepeater.setItemMeta(redstoneRepeaterMeta);
        inv.setItem(2, redstoneRepeater);
        lore.clear();
        
        
        player.openInventory(inv);
        return inv;
    }

    @EventHandler
    public void rightClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTitle().equalsIgnoreCase("§c§nAline's Redstone Market")) {
            event.setCancelled(true);
            if(event.getSlot() == 1) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REDSTONE), 64, 320, player, MarketMethods.MarketNPC.ALINE);
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REDSTONE), 1, 5, player, MarketMethods.MarketNPC.ALINE);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REDSTONE), 16, 80, player, MarketMethods.MarketNPC.ALINE);
                }
            }

            if(event.getSlot() == 2) {
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REPEATER), 1, 20, player, MarketMethods.MarketNPC.ALINE);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REPEATER), 3, 60, player, MarketMethods.MarketNPC.ALINE);
                }
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REPEATER), 5, 100, player, MarketMethods.MarketNPC.ALINE);
                }
                player.closeInventory();
            }
        }
    }
}
