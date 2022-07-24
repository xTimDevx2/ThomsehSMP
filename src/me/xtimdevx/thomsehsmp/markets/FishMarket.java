package me.xtimdevx.thomsehsmp.markets;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class FishMarket implements Listener {

    MarketMethods marketMethods = new MarketMethods();

    public static Inventory openMarketRedstone(Player player) {
        Inventory inv = Bukkit.createInventory(player,  18, "§9§nMia's Fish Market");
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack glass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

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

        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemMeta rodMeta = rod.getItemMeta();
        rodMeta.setDisplayName("§9Fishing Rod");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 100 ⛀");
        lore.add(" ");
        rodMeta.setLore(lore);
        rod.setItemMeta(rodMeta);
        inv.setItem(1, rod);
        lore.clear();

        ItemStack salmonRepeater = new ItemStack(Material.SALMON);
        ItemMeta salmonRepeaterMeta = salmonRepeater.getItemMeta();
        salmonRepeaterMeta.setDisplayName("§9Salmon");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 20 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 320 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 900 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        salmonRepeaterMeta.setLore(lore);
        salmonRepeater.setItemMeta(salmonRepeaterMeta);
        inv.setItem(2, salmonRepeater);
        lore.clear();

        ItemStack tropicalRepeater = new ItemStack(Material.TROPICAL_FISH);
        ItemMeta tropicalRepeaterMeta = tropicalRepeater.getItemMeta();
        tropicalRepeaterMeta.setDisplayName("§9Tropical Fish");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 20 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 320 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 900 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        tropicalRepeaterMeta.setLore(lore);
        tropicalRepeater.setItemMeta(tropicalRepeaterMeta);
        inv.setItem(3, tropicalRepeater);
        lore.clear();

        ItemStack codRepeater = new ItemStack(Material.COD);
        ItemMeta codRepeaterMeta = codRepeater.getItemMeta();
        codRepeaterMeta.setDisplayName("§9Cod");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 20 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 320 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 900 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        codRepeaterMeta.setLore(lore);
        codRepeater.setItemMeta(codRepeaterMeta);
        inv.setItem(4, codRepeater);
        lore.clear();

        ItemStack pufferRepeater = new ItemStack(Material.PUFFERFISH);
        ItemMeta pufferRepeaterMeta = pufferRepeater.getItemMeta();
        pufferRepeaterMeta.setDisplayName("§9Pufferfish");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 40 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 640 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1800 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        pufferRepeaterMeta.setLore(lore);
        pufferRepeater.setItemMeta(pufferRepeaterMeta);
        inv.setItem(5, pufferRepeater);
        lore.clear();
        
        player.openInventory(inv);
        return inv;
    }

    @EventHandler
    public void rightClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTitle().equalsIgnoreCase("§9§nMia's Fish Market")) {
            event.setCancelled(true);
            if(event.getSlot() == 1) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FISHING_ROD), 1, 100, player, MarketMethods.MarketNPC.MIA);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FISHING_ROD), 1, 100, player, MarketMethods.MarketNPC.MIA);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FISHING_ROD), 1, 100, player, MarketMethods.MarketNPC.MIA);
                }
            }

            if(event.getSlot() == 2) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SALMON), 64, 900, player, MarketMethods.MarketNPC.MIA);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SALMON), 1, 20, player, MarketMethods.MarketNPC.MIA);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SALMON), 16, 320, player, MarketMethods.MarketNPC.MIA);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 3) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.TROPICAL_FISH), 64, 900, player, MarketMethods.MarketNPC.MIA);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.TROPICAL_FISH), 1, 20, player, MarketMethods.MarketNPC.MIA);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.TROPICAL_FISH), 16, 320, player, MarketMethods.MarketNPC.MIA);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 4) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COD), 64, 900, player, MarketMethods.MarketNPC.MIA);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COD), 1, 20, player, MarketMethods.MarketNPC.MIA);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COD), 16, 320, player, MarketMethods.MarketNPC.MIA);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 5) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PUFFERFISH), 64, 1800, player, MarketMethods.MarketNPC.MIA);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PUFFERFISH), 1, 40, player, MarketMethods.MarketNPC.MIA);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PUFFERFISH), 16, 640, player, MarketMethods.MarketNPC.MIA);
                }
                player.closeInventory();
            }
        }
    }
}
