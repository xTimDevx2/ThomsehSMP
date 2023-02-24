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

public class ResourceMarket implements Listener {

    MarketMethods marketMethods = new MarketMethods();

    public static Inventory openMarketRedstone(Player player) {
        Inventory inv = Bukkit.createInventory(player,  36, "§b§nTheodorus's Resource Market");
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack glass = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);

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

        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        ItemMeta ironMeta = iron.getItemMeta();
        ironMeta.setDisplayName("§bIron Ingot");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 15 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 240 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 800 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        ironMeta.setLore(lore);
        iron.setItemMeta(ironMeta);
        inv.setItem(1, iron);
        lore.clear();


        ItemStack coal = new ItemStack(Material.COAL);
        ItemMeta coalMeta = coal.getItemMeta();
        coalMeta.setDisplayName("§bCoal");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        coalMeta.setLore(lore);
        coal.setItemMeta(coalMeta);
        inv.setItem(2, coal);
        lore.clear();

        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        ItemMeta goldMeta = gold.getItemMeta();
        goldMeta.setDisplayName("§bGold Ingot");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 25 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 40 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1500 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        goldMeta.setLore(lore);
        gold.setItemMeta(goldMeta);
        inv.setItem(3, gold);
        lore.clear();


        ItemStack lapis = new ItemStack(Material.LAPIS_LAZULI);
        ItemMeta lapisMeta = lapis.getItemMeta();
        lapisMeta.setDisplayName("§bLapis Lazuli");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        lapisMeta.setLore(lore);
        lapis.setItemMeta(lapisMeta);
        inv.setItem(4, lapis);
        lore.clear();

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = diamond.getItemMeta();
        diamondMeta.setDisplayName("§bDiamond");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 160 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 2560 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 10000 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        diamondMeta.setLore(lore);
        diamond.setItemMeta(diamondMeta);
        inv.setItem(5, diamond);
        lore.clear();

        ItemStack emerald = new ItemStack(Material.EMERALD);
        ItemMeta emeraldMeta = emerald.getItemMeta();
        emeraldMeta.setDisplayName("§bEmerald");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 100 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 1600 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 6000 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        emeraldMeta.setLore(lore);
        emerald.setItemMeta(emeraldMeta);
        inv.setItem(6, emerald);
        lore.clear();

        ItemStack quartz = new ItemStack(Material.QUARTZ);
        ItemMeta quartzMeta = quartz.getItemMeta();
        quartzMeta.setDisplayName("§bNether Quartz");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 10 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 160 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 500 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        quartzMeta.setLore(lore);
        quartz.setItemMeta(quartzMeta);
        inv.setItem(7, quartz);
        lore.clear();

        ItemStack glowstone = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta glowstoneMeta = glowstone.getItemMeta();
        glowstoneMeta.setDisplayName("§bGlowstone Dust");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 15 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 240 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 800 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        glowstoneMeta.setLore(lore);
        glowstone.setItemMeta(glowstoneMeta);
        inv.setItem(8, glowstone);
        lore.clear();

        ItemStack clay = new ItemStack(Material.CLAY_BALL);
        ItemMeta clayMeta = clay.getItemMeta();
        clayMeta.setDisplayName("§bClay Ball");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 3 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 48 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 150 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        clayMeta.setLore(lore);
        clay.setItemMeta(clayMeta);
        inv.setItem(10, clay);
        lore.clear();

        ItemStack flint = new ItemStack(Material.FLINT);
        ItemMeta flintMeta = flint.getItemMeta();
        flintMeta.setDisplayName("§bFlint");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 4 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 64 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 200 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        flintMeta.setLore(lore);
        flint.setItemMeta(flintMeta);
        inv.setItem(11, flint);
        lore.clear();

        ItemStack gunpowdr = new ItemStack(Material.GUNPOWDER);
        ItemMeta gunpowdrMeta = gunpowdr.getItemMeta();
        gunpowdrMeta.setDisplayName("§bGunpowder");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 10 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 160 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 500 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        gunpowdrMeta.setLore(lore);
        gunpowdr.setItemMeta(gunpowdrMeta);
        inv.setItem(12, gunpowdr);
        lore.clear();

        ItemStack leather = new ItemStack(Material.LEATHER);
        ItemMeta leatherMeta = leather.getItemMeta();
        leatherMeta.setDisplayName("§bLeather");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 20 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 380 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 900 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        leatherMeta.setLore(lore);
        leather.setItemMeta(leatherMeta);
        inv.setItem(13, leather);
        lore.clear();

        ItemStack feather = new ItemStack(Material.FEATHER);
        ItemMeta featherMeta = feather.getItemMeta();
        featherMeta.setDisplayName("§bFeather");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 4 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 64 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 200 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        featherMeta.setLore(lore);
        feather.setItemMeta(featherMeta);
        inv.setItem(14, feather);
        lore.clear();

        ItemStack bone = new ItemStack(Material.BONE);
        ItemMeta boneMeta = bone.getItemMeta();
        boneMeta.setDisplayName("§bBone");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 6 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 96 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 300 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        boneMeta.setLore(lore);
        bone.setItemMeta(boneMeta);
        inv.setItem(15, bone);
        lore.clear();

        ItemStack string = new ItemStack(Material.STRING);
        ItemMeta stringMeta = string.getItemMeta();
        stringMeta.setDisplayName("§bString");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 4 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 64 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 200 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        stringMeta.setLore(lore);
        string.setItemMeta(stringMeta);
        inv.setItem(16, string);
        lore.clear();

        ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL);
        ItemMeta enderpearlMeta = enderpearl.getItemMeta();
        enderpearlMeta.setDisplayName("§bEnder Pearl");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 35 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 560 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 2100 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        enderpearlMeta.setLore(lore);
        enderpearl.setItemMeta(enderpearlMeta);
        inv.setItem(17, enderpearl);
        lore.clear();

        ItemStack blazerod = new ItemStack(Material.BLAZE_ROD);
        ItemMeta blazerodMeta = blazerod.getItemMeta();
        blazerodMeta.setDisplayName("§bBlaze Rod");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 50 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 800 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 3000 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        blazerodMeta.setLore(lore);
        blazerod.setItemMeta(blazerodMeta);
        inv.setItem(19, blazerod);
        lore.clear();

        ItemStack netherwart = new ItemStack(Material.NETHER_WART);
        ItemMeta netherwartMeta = netherwart.getItemMeta();
        netherwartMeta.setDisplayName("§bNether Wart Seeds");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 100 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 1600 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 6000 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        netherwartMeta.setLore(lore);
        netherwart.setItemMeta(netherwartMeta);
        inv.setItem(20, netherwart);
        lore.clear();

        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowMeta = arrow.getItemMeta();
        arrowMeta.setDisplayName("§bArrow");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 5 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 80 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 300 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        arrowMeta.setLore(lore);
        arrow.setItemMeta(arrowMeta);
        inv.setItem(21, arrow);
        lore.clear();

        player.openInventory(inv);
        return inv;
    }

    @EventHandler
    public void rightClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTitle().equalsIgnoreCase("§b§nTheodorus's Resource Market")) {
            event.setCancelled(true);
            if(event.getSlot() == 1) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.IRON_INGOT), 64, 800, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.IRON_INGOT), 1, 15, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.IRON_INGOT), 16, 240, player, MarketMethods.MarketNPC.THEODORUS);
                }
            }

            if(event.getSlot() == 2) {
                if(event.isShiftClick()) {
                        marketMethods.buyItem(new ItemStack(Material.COAL), 64, 400, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COAL), 1, 8, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COAL), 16, 124, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }

            if(event.getSlot() == 3) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GOLD_INGOT), 64, 2000, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GOLD_INGOT), 1, 40, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GOLD_INGOT), 16, 640, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }

            if(event.getSlot() == 4) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.LAPIS_LAZULI), 64, 400, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.LAPIS_LAZULI), 1, 8, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.LAPIS_LAZULI), 16, 128, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 5) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIAMOND), 64, 10000, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIAMOND), 1, 160, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIAMOND), 16, 2560, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 6) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.EMERALD), 64, 6000, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.EMERALD), 1, 100, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.EMERALD), 16, 1600, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 7) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.QUARTZ), 64, 500, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.QUARTZ), 1, 10, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.QUARTZ), 16, 160, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 8) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GLOWSTONE_DUST), 64, 800, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GLOWSTONE_DUST), 1, 15, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GLOWSTONE_DUST), 16, 240, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 10) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.CLAY_BALL), 64, 150, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.CLAY_BALL), 1, 3, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.CLAY_BALL), 16, 48, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 11) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FLINT), 64, 200, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FLINT), 1, 4, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FLINT), 16, 64, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 12) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GUNPOWDER), 64, 500, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GUNPOWDER), 1, 10, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GUNPOWDER), 16, 160, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 13) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.LEATHER), 64, 900, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.LEATHER), 1, 20, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.LEATHER), 16, 380, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 14) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FEATHER), 64, 200, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FEATHER), 1, 4, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.FEATHER), 16, 64, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 15) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BONE), 64, 300, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BONE), 1, 6, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BONE), 16, 96, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 16) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STRING), 64, 200, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STRING), 1, 4, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STRING), 16, 64, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 17) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ENDER_PEARL), 64, 2100, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ENDER_PEARL), 1, 35, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ENDER_PEARL), 16, 560, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 19) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLAZE_ROD), 64, 3000, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLAZE_ROD), 1, 50, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLAZE_ROD), 16, 800, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 20) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.NETHER_WART), 64, 6000, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.NETHER_WART), 1, 100, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.NETHER_WART), 16, 1600, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
            if(event.getSlot() == 21) {
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ARROW), 64, 300, player, MarketMethods.MarketNPC.THEODORUS);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ARROW), 1, 5, player, MarketMethods.MarketNPC.THEODORUS);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ARROW), 16, 80, player, MarketMethods.MarketNPC.THEODORUS);
                }
                player.closeInventory();
            }
        }
    }
}
