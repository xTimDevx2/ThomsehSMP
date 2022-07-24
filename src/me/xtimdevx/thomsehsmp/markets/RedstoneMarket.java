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
        Inventory inv = Bukkit.createInventory(player,  18, "§c§nAline's Redstone Market");
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack glass = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        
        inv.setItem(0, glass);
        inv.setItem(9, glass);
        inv.setItem(10, glass);
        inv.setItem(11, glass);
        inv.setItem(12, glass);
        inv.setItem(13, glass);
        inv.setItem(14, glass);
        inv.setItem(15, glass);
        inv.setItem(16, glass);
        inv.setItem(17, glass);
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
        lore.add("§8>    §f1x = 25 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 400 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1200 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        redstoneRepeaterMeta.setLore(lore);
        redstoneRepeater.setItemMeta(redstoneRepeaterMeta);
        inv.setItem(2, redstoneRepeater);
        lore.clear();

        ItemStack comperator = new ItemStack(Material.COMPARATOR);
        ItemMeta comperatorMeta = comperator.getItemMeta();
        comperatorMeta.setDisplayName("§cRedstone Comparator");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 30 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 480 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1700 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        comperatorMeta.setLore(lore);
        comperator.setItemMeta(comperatorMeta);
        inv.setItem(3, comperator);
        lore.clear();

        ItemStack piston = new ItemStack(Material.PISTON);
        ItemMeta pistonMeta = piston.getItemMeta();
        pistonMeta.setDisplayName("§cPiston");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 30 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 480 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1600 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        pistonMeta.setLore(lore);
        piston.setItemMeta(pistonMeta);
        inv.setItem(4, piston);
        lore.clear();

        ItemStack lamp = new ItemStack(Material.REDSTONE_LAMP);
        ItemMeta lampMeta = lamp.getItemMeta();
        lampMeta.setDisplayName("§cRedstone Lamp");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 35 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 560 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1800 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        lampMeta.setLore(lore);
        lamp.setItemMeta(lampMeta);
        inv.setItem(5, lamp);
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
                    player.closeInventory();
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
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REPEATER), 64, 1200, player, MarketMethods.MarketNPC.ALINE);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REPEATER), 1, 25, player, MarketMethods.MarketNPC.ALINE);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REPEATER), 16, 400, player, MarketMethods.MarketNPC.ALINE);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 3) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COMPARATOR), 64, 1700, player, MarketMethods.MarketNPC.ALINE);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COMPARATOR), 1, 30, player, MarketMethods.MarketNPC.ALINE);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COMPARATOR), 16, 480, player, MarketMethods.MarketNPC.ALINE);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 4) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PISTON), 64, 1600, player, MarketMethods.MarketNPC.ALINE);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PISTON), 1, 30, player, MarketMethods.MarketNPC.ALINE);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PISTON), 16, 480, player, MarketMethods.MarketNPC.ALINE);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 5) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REDSTONE_LAMP), 64, 1800, player, MarketMethods.MarketNPC.ALINE);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REDSTONE_LAMP), 1, 35, player, MarketMethods.MarketNPC.ALINE);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.REDSTONE_LAMP), 16, 560, player, MarketMethods.MarketNPC.ALINE);
                }
                player.closeInventory();
            }
        }
    }
}
