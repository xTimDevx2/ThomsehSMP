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

public class BlockMarket implements Listener {

    MarketMethods marketMethods = new MarketMethods();

    public static Inventory openMarketRedstone(Player player) {
        Inventory inv = Bukkit.createInventory(player,  54, "§2§nNoah's Block Market");
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack glasspain = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

        inv.setItem(0, glasspain);
        inv.setItem(9, glasspain);
        inv.setItem(18, glasspain);
        inv.setItem(27, glasspain);
        inv.setItem(36, glasspain);
        inv.setItem(45, glasspain);
        inv.setItem(46, glasspain);
        inv.setItem(47, glasspain);
        inv.setItem(48, glasspain);
        inv.setItem(49, glasspain);
        inv.setItem(50, glasspain);
        inv.setItem(51, glasspain);
        inv.setItem(52, glasspain);
        inv.setItem(53, glasspain);
        //⛀⛁⛃⛂

        ItemStack stone = new ItemStack(Material.STONE);
        ItemMeta stoneMeta = stone.getItemMeta();
        stoneMeta.setDisplayName("§2Stone");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 2 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 32 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 100 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        stoneMeta.setLore(lore);
        stone.setItemMeta(stoneMeta);
        inv.setItem(1, stone);
        lore.clear();


        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE);
        ItemMeta cobblestoneMeta = cobblestone.getItemMeta();
        cobblestoneMeta.setDisplayName("§2Cobblestone");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 1 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 16 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 60 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        cobblestoneMeta.setLore(lore);
        cobblestone.setItemMeta(cobblestoneMeta);
        inv.setItem(2, cobblestone);
        lore.clear();

        ItemStack dirt = new ItemStack(Material.DIRT);
        ItemMeta dirtMeta = dirt.getItemMeta();
        dirtMeta.setDisplayName("§2Dirt");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 1 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 16 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 50 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        dirtMeta.setLore(lore);
        dirt.setItemMeta(dirtMeta);
        inv.setItem(3, dirt);
        lore.clear();
        
        ItemStack grass = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta grassMeta = grass.getItemMeta();
        grassMeta.setDisplayName("§2Grass Blocks");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 4 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 64 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 200 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        grassMeta.setLore(lore);
        grass.setItemMeta(grassMeta);
        inv.setItem(4, grass);
        lore.clear();

        ItemStack gravel = new ItemStack(Material.GRAVEL);
        ItemMeta gravelMeta = gravel.getItemMeta();
        gravelMeta.setDisplayName("§2Gravel");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 3 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 48 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 150 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        gravelMeta.setLore(lore);
        gravel.setItemMeta(gravelMeta);
        inv.setItem(5, gravel);
        lore.clear();

        ItemStack sand = new ItemStack(Material.SAND);
        ItemMeta sandMeta = sand.getItemMeta();
        sandMeta.setDisplayName("§2Sand");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 1 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 16 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 60 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        sandMeta.setLore(lore);
        sand.setItemMeta(sandMeta);
        inv.setItem(6, sand);
        lore.clear();

        ItemStack redsand = new ItemStack(Material.RED_SAND);
        ItemMeta redsandMeta = redsand.getItemMeta();
        redsandMeta.setDisplayName("§2Red Sand");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 3 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 48 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 180 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        redsandMeta.setLore(lore);
        redsand.setItemMeta(redsandMeta);
        inv.setItem(7, redsand);
        lore.clear();

        ItemStack clayblock = new ItemStack(Material.CLAY);
        ItemMeta clayblockMeta = clayblock.getItemMeta();
        clayblockMeta.setDisplayName("§2Clay Block");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 12 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 192 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 600 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        clayblockMeta.setLore(lore);
        clayblock.setItemMeta(clayblockMeta);
        inv.setItem(8, clayblock);
        lore.clear();

        ItemStack ice = new ItemStack(Material.ICE);
        ItemMeta iceMeta = ice.getItemMeta();
        iceMeta.setDisplayName("§2Ice Block");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 6 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 96 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 300 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        iceMeta.setLore(lore);
        ice.setItemMeta(iceMeta);
        inv.setItem(10, ice);
        lore.clear();

        ItemStack packedice = new ItemStack(Material.PACKED_ICE);
        ItemMeta packediceMeta = packedice.getItemMeta();
        packediceMeta.setDisplayName("§2Packed Ice");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 30 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 480 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1800 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        packediceMeta.setLore(lore);
        packedice.setItemMeta(packediceMeta);
        inv.setItem(11, packedice);
        lore.clear();

        ItemStack blueice = new ItemStack(Material.BLUE_ICE);
        ItemMeta blueiceMeta = blueice.getItemMeta();
        blueiceMeta.setDisplayName("§2Blue Ice");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 30 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 480 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1800 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        blueiceMeta.setLore(lore);
        blueice.setItemMeta(blueiceMeta);
        inv.setItem(12, blueice);
        lore.clear();

        ItemStack obsidian = new ItemStack(Material.OBSIDIAN);
        ItemMeta obsidianMeta = obsidian.getItemMeta();
        obsidianMeta.setDisplayName("§2Obsidian");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 25 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 400 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 1500 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        obsidianMeta.setLore(lore);
        obsidian.setItemMeta(obsidianMeta);
        inv.setItem(13, obsidian);
        lore.clear();

        ItemStack oaklog = new ItemStack(Material.OAK_LOG);
        ItemMeta oaklogMeta = oaklog.getItemMeta();
        oaklogMeta.setDisplayName("§2Oak Log");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        oaklogMeta.setLore(lore);
        oaklog.setItemMeta(oaklogMeta);
        inv.setItem(14, oaklog);
        lore.clear();

        ItemStack birchlog = new ItemStack(Material.BIRCH_LOG);
        ItemMeta birchlogMeta = birchlog.getItemMeta();
        birchlogMeta.setDisplayName("§2Birch Log");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        birchlogMeta.setLore(lore);
        birchlog.setItemMeta(birchlogMeta);
        inv.setItem(15, birchlog);
        lore.clear();

        ItemStack sprucelog = new ItemStack(Material.SPRUCE_LOG);
        ItemMeta sprucelogMeta = sprucelog.getItemMeta();
        sprucelogMeta.setDisplayName("§2Spruce Log");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        sprucelogMeta.setLore(lore);
        sprucelog.setItemMeta(sprucelogMeta);
        inv.setItem(16, sprucelog);
        lore.clear();
        
        ItemStack junglelog = new ItemStack(Material.JUNGLE_LOG);
        ItemMeta junglelogMeta = junglelog.getItemMeta();
        junglelogMeta.setDisplayName("§2Jungle Log");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        junglelogMeta.setLore(lore);
        junglelog.setItemMeta(junglelogMeta);
        inv.setItem(17, junglelog);
        lore.clear();

        ItemStack accacialog = new ItemStack(Material.ACACIA_LOG);
        ItemMeta accacialogMeta = accacialog.getItemMeta();
        accacialogMeta.setDisplayName("§2Acacia Log");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        accacialogMeta.setLore(lore);
        accacialog.setItemMeta(accacialogMeta);
        inv.setItem(19, accacialog);
        lore.clear();

        ItemStack darkoaklog = new ItemStack(Material.DARK_OAK_LOG);
        ItemMeta darkoaklogMeta = darkoaklog.getItemMeta();
        darkoaklogMeta.setDisplayName("§2Dark Oak Log");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        darkoaklogMeta.setLore(lore);
        darkoaklog.setItemMeta(darkoaklogMeta);
        inv.setItem(20, darkoaklog);
        lore.clear();

        ItemStack mangrovelog = new ItemStack(Material.MANGROVE_LOG);
        ItemMeta mangrovelogMeta = mangrovelog.getItemMeta();
        mangrovelogMeta.setDisplayName("§2Mangrove Log");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        mangrovelogMeta.setLore(lore);
        mangrovelog.setItemMeta(mangrovelogMeta);
        inv.setItem(21, mangrovelog);
        lore.clear();

        ItemStack granite = new ItemStack(Material.GRANITE);
        ItemMeta graniteMeta = granite.getItemMeta();
        graniteMeta.setDisplayName("§2Granite");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 2 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 32 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 120 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        graniteMeta.setLore(lore);
        granite.setItemMeta(graniteMeta);
        inv.setItem(22, granite);
        lore.clear();

        ItemStack diorite = new ItemStack(Material.DIORITE);
        ItemMeta dioriteMeta = diorite.getItemMeta();
        dioriteMeta.setDisplayName("§2Diorite");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 2 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 32 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 120 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        dioriteMeta.setLore(lore);
        diorite.setItemMeta(dioriteMeta);
        inv.setItem(23, diorite);
        lore.clear();

        ItemStack andesite = new ItemStack(Material.ANDESITE);
        ItemMeta andesiteMeta = andesite.getItemMeta();
        andesiteMeta.setDisplayName("§2Andesite");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 2 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 32 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 120 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        andesiteMeta.setLore(lore);
        andesite.setItemMeta(andesiteMeta);
        inv.setItem(24, andesite);
        lore.clear();

        ItemStack deepslate = new ItemStack(Material.DEEPSLATE);
        ItemMeta deepslateMeta = deepslate.getItemMeta();
        deepslateMeta.setDisplayName("§2Deepslate");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 5 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 80 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 550 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        deepslateMeta.setLore(lore);
        deepslate.setItemMeta(deepslateMeta);
        inv.setItem(25, deepslate);
        lore.clear();

        ItemStack sandstone = new ItemStack(Material.SANDSTONE);
        ItemMeta sandstoneMeta = sandstone.getItemMeta();
        sandstoneMeta.setDisplayName("§2Sandstone");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 3 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 48 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 180 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        sandstoneMeta.setLore(lore);
        sandstone.setItemMeta(sandstoneMeta);
        inv.setItem(26, sandstone);
        lore.clear();

        ItemStack wool = new ItemStack(Material.WHITE_WOOL);
        ItemMeta woolMeta = wool.getItemMeta();
        woolMeta.setDisplayName("§2Wool");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 6 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 96 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 300 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        woolMeta.setLore(lore);
        wool.setItemMeta(woolMeta);
        inv.setItem(28, wool);
        lore.clear();

        ItemStack glassblock = new ItemStack(Material.GLASS);
        ItemMeta glassblockMeta = glassblock.getItemMeta();
        glassblockMeta.setDisplayName("§2Glass");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 480 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        glassblockMeta.setLore(lore);
        glassblock.setItemMeta(glassblockMeta);
        inv.setItem(29, glassblock);
        lore.clear();

        ItemStack bricks = new ItemStack(Material.BRICKS);
        ItemMeta bricksMeta = bricks.getItemMeta();
        bricksMeta.setDisplayName("§2Bricks");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 15 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 240 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 800 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        bricksMeta.setLore(lore);
        bricks.setItemMeta(bricksMeta);
        inv.setItem(30, bricks);
        lore.clear();

        ItemStack smoothstone = new ItemStack(Material.SMOOTH_STONE);
        ItemMeta smoothstoneMeta = smoothstone.getItemMeta();
        smoothstoneMeta.setDisplayName("§2Smooth Stone");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 2 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 32 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 120 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        smoothstoneMeta.setLore(lore);
        smoothstone.setItemMeta(smoothstoneMeta);
        inv.setItem(31, smoothstone);
        lore.clear();

        ItemStack stonebrick = new ItemStack(Material.STONE_BRICKS);
        ItemMeta stonebrickMeta = stonebrick.getItemMeta();
        stonebrickMeta.setDisplayName("§2Stone Bricks");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 2 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 32 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 120 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        stonebrickMeta.setLore(lore);
        stonebrick.setItemMeta(stonebrickMeta);
        inv.setItem(32, stonebrick);
        lore.clear();

        ItemStack netherbrick = new ItemStack(Material.NETHER_BRICKS);
        ItemMeta netherbrickMeta = netherbrick.getItemMeta();
        netherbrickMeta.setDisplayName("§2Nether Bricks");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 8 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 128 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 400 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        netherbrickMeta.setLore(lore);
        netherbrick.setItemMeta(netherbrickMeta);
        inv.setItem(33, netherbrick);
        lore.clear();

        ItemStack terracota = new ItemStack(Material.TERRACOTTA);
        ItemMeta terracotaMeta = terracota.getItemMeta();
        terracotaMeta.setDisplayName("§2Terracotta");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 15 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 240 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 800 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        terracotaMeta.setLore(lore);
        terracota.setItemMeta(terracotaMeta);
        inv.setItem(34, terracota);
        lore.clear();

        ItemStack quartzblock = new ItemStack(Material.QUARTZ_BLOCK);
        ItemMeta quartzblockMeta = quartzblock.getItemMeta();
        quartzblockMeta.setDisplayName("§2Quartz Block");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 40 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 640 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 2000 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        quartzblockMeta.setLore(lore);
        quartzblock.setItemMeta(quartzblockMeta);
        inv.setItem(35, quartzblock);
        lore.clear();
        
        ItemStack prismarine = new ItemStack(Material.PRISMARINE);
        ItemMeta prismarineMeta = prismarine.getItemMeta();
        prismarineMeta.setDisplayName("§2Prismarine");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 40 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 640 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 2000 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        prismarineMeta.setLore(lore);
        prismarine.setItemMeta(prismarineMeta);
        inv.setItem(37, prismarine);
        lore.clear();

        ItemStack darkprismarine = new ItemStack(Material.DARK_PRISMARINE);
        ItemMeta darkprismarineMeta = darkprismarine.getItemMeta();
        darkprismarineMeta.setDisplayName("§2Dark Prismarine");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 40 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 640 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 2000 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        darkprismarineMeta.setLore(lore);
        darkprismarine.setItemMeta(darkprismarineMeta);
        inv.setItem(38, darkprismarine);
        lore.clear();

        ItemStack blackstone = new ItemStack(Material.BLACKSTONE);
        ItemMeta blackstoneMeta = blackstone.getItemMeta();
        blackstoneMeta.setDisplayName("§2Blackstone");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 10 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 160 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 600 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        blackstoneMeta.setLore(lore);
        blackstone.setItemMeta(blackstoneMeta);
        inv.setItem(39, blackstone);
        lore.clear();

        ItemStack mushroomred = new ItemStack(Material.RED_MUSHROOM_BLOCK);
        ItemMeta mushroomredMeta = mushroomred.getItemMeta();
        mushroomredMeta.setDisplayName("§2Red Mushroom Block");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 10 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 160 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 600 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        mushroomredMeta.setLore(lore);
        mushroomred.setItemMeta(mushroomredMeta);
        inv.setItem(40, mushroomred);
        lore.clear();

        ItemStack mushroombrown = new ItemStack(Material.BROWN_MUSHROOM_BLOCK);
        ItemMeta mushroombrownMeta = mushroombrown.getItemMeta();
        mushroombrownMeta.setDisplayName("§2Brown Mushroom Block");
        lore.add(" ");
        lore.add("§8> §fPrices: ");
        lore.add("§8>    §f1x = 10 ⛀ §7§o(Rechtermuis Knop)");
        lore.add("§8>    §f16x = 160 ⛀ §7§o(Linkermuis Knop)");
        lore.add("§8>    §f64x = 600 ⛀ §7§o(Shift + Rechtermuis Knop)");
        lore.add(" ");
        mushroombrownMeta.setLore(lore);
        mushroombrown.setItemMeta(mushroombrownMeta);
        inv.setItem(41, mushroombrown);
        lore.clear();

        player.openInventory(inv);
        return inv;
    }

    @EventHandler
    public void rightClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTitle().equalsIgnoreCase("§2§nNoah's Block Market")) {
            event.setCancelled(true);
            if(event.getSlot() == 1) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STONE), 64, 100, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STONE), 1, 2, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STONE), 16, 32, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 2) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COBBLESTONE), 64, 60, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COBBLESTONE), 1, 1, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.COBBLESTONE), 16, 16, player, MarketMethods.MarketNPC.NOAH);
                }
            }
            if(event.getSlot() == 3) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIRT), 64, 50, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIRT), 1, 1, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIRT), 16, 16, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 4) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRASS_BLOCK), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRASS_BLOCK), 1, 4, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRASS_BLOCK), 16, 64, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 5) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRAVEL), 64, 150, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRAVEL), 1, 3, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRAVEL), 16, 48, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 6) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SAND), 64, 60, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SAND), 1, 1, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SAND), 16, 16, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 7) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.RED_SAND), 64, 180, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.RED_SAND), 1, 3, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.RED_SAND), 16, 48, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 8) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.CLAY), 64, 600, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.CLAY), 1, 12, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.CLAY), 16, 192, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 10) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ICE), 64, 300, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ICE), 1, 6, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ICE), 16, 96, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 11) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PACKED_ICE), 64, 1800, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PACKED_ICE), 1, 30, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PACKED_ICE), 16, 480, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 12) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLUE_ICE), 64, 1800, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLUE_ICE), 1, 30, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLUE_ICE), 16, 480, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 13) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.OBSIDIAN), 64, 1500, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.OBSIDIAN), 1, 25, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.OBSIDIAN), 16, 400, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 14) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.OAK_LOG), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.OAK_LOG), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.OAK_LOG), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 15) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BIRCH_LOG), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BIRCH_LOG), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BIRCH_LOG), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 16) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SPRUCE_LOG), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SPRUCE_LOG), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SPRUCE_LOG), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 17) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.JUNGLE_LOG), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.JUNGLE_LOG), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.JUNGLE_LOG), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 19) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ACACIA_LOG), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ACACIA_LOG), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ACACIA_LOG), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 20) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DARK_OAK_LOG), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DARK_OAK_LOG), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DARK_OAK_LOG), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 21) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.MANGROVE_LOG), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.MANGROVE_LOG), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.MANGROVE_LOG), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 22) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRANITE), 64, 120, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRANITE), 1, 2, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GRANITE), 16, 32, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 23) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIORITE), 64, 120, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIORITE), 1, 2, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DIORITE), 16, 32, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 24) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ANDESITE), 64, 120, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ANDESITE), 1, 2, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.ANDESITE), 16, 32, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 25) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DEEPSLATE), 64, 550, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DEEPSLATE), 1, 5, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DEEPSLATE), 16, 80, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 26) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SANDSTONE), 64, 180, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SANDSTONE), 1, 3, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SANDSTONE), 16, 48, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 28) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.WHITE_WOOL), 64, 300, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.WHITE_WOOL), 1, 6, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.WHITE_WOOL), 16, 96, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 29) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GLASS), 64, 480, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GLASS), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.GLASS), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 30) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BRICKS), 64, 800, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BRICKS), 1, 15, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BRICKS), 16, 240, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 31) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SMOOTH_STONE), 64, 120, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SMOOTH_STONE), 1, 2, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.SMOOTH_STONE), 16, 32, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 32) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STONE_BRICKS), 64, 120, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STONE_BRICKS), 1, 2, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.STONE_BRICKS), 16, 32, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 33) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.NETHER_BRICKS), 64, 400, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.NETHER_BRICKS), 1, 8, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.NETHER_BRICKS), 16, 128, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 34) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.TERRACOTTA), 64, 800, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.TERRACOTTA), 1, 15, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.TERRACOTTA), 16, 240, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 35) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.QUARTZ_BLOCK), 64, 2000, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.QUARTZ_BLOCK), 1, 40, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.QUARTZ_BLOCK), 16, 640, player, MarketMethods.MarketNPC.NOAH);
                }
            }
            if(event.getSlot() == 37) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PRISMARINE), 64, 2000, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PRISMARINE), 1, 40, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.PRISMARINE), 16, 640, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 38) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DARK_PRISMARINE), 64, 2000, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DARK_PRISMARINE), 1, 40, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.DARK_PRISMARINE), 16, 640, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 39) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLACKSTONE), 64, 600, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLACKSTONE), 1, 10, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BLACKSTONE), 16, 160, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 40) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.RED_MUSHROOM_BLOCK), 64, 600, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.RED_MUSHROOM_BLOCK), 1, 10, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.RED_MUSHROOM_BLOCK), 16, 160, player, MarketMethods.MarketNPC.NOAH);
                }
            }

            if(event.getSlot() == 41) {
                player.closeInventory();
                if(event.isShiftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BROWN_MUSHROOM_BLOCK), 64, 600, player, MarketMethods.MarketNPC.NOAH);
                    player.closeInventory();
                    return;
                }
                if(event.isRightClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BROWN_MUSHROOM_BLOCK), 1, 10, player, MarketMethods.MarketNPC.NOAH);
                }
                if(event.isLeftClick()) {
                    marketMethods.buyItem(new ItemStack(Material.BROWN_MUSHROOM_BLOCK), 16, 160, player, MarketMethods.MarketNPC.NOAH);
                }
            }

        }
    }
}
