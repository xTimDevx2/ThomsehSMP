package me.xtimdevx.thomsehsmp.markets;

import me.xtimdevx.thomsehsmp.managers.EconomyManager;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MarketMethods {

    EconomyManager economyManager = new EconomyManager();

    public enum MarketNPC{
        ALINE, THEODORUS, MIA, NOAH;
    }

    public String refactorName(MarketNPC npc) {
        switch (npc) {
            case ALINE:
                return "Aline";
            case THEODORUS:
                return "Theodorus";
            case MIA:
                return "Mia";
            case NOAH:
                return "Noah";
            default:
                return "?";
        }
    }

    public String refactorMarketItem(Material itemStack) {
        switch (itemStack) {
            case REDSTONE:
                return "Redstone";
            case REPEATER:
                return "Redstone Repeater";
            case IRON_INGOT:
                return "Iron Ingot";
            case COAL:
                return "Coal";
            case GOLD_INGOT:
                return "Gold Ingot";
            case LAPIS_LAZULI:
                return "Lapis Lazuli";
            case DIAMOND:
                return "Diamond";
            case EMERALD:
                return "Emerald";
            case QUARTZ:
                return "Nether Quartz";
            case GLOWSTONE_DUST:
                return "Glowstone Dust";
            case CLAY_BALL:
                return "Clay Ball";
            case FLINT:
                return "Flint";
            case GUNPOWDER:
                return "Gunpowder";
            case LEATHER:
                return "Leather";
            case FEATHER:
                return "Feather";
            case BONE:
                return "Bone";
            case STRING:
                return "String";
            case ENDER_PEARL:
                return "Ender Pearl";
            case NETHER_WART:
                return "Nether Wart";
            case ARROW:
                return "Arrow";
            case COMPARATOR:
                return "Redstone Comperator";
            case PISTON:
                return "Piston";
            case REDSTONE_LAMP:
                return "Redstone Lamp";
            case FISHING_ROD:
                return "Fishing Rod";
            case SALMON:
                return "Raw Salmon";
            case COD:
                return "Raw Cod";
            case PUFFERFISH:
                return "Pufferfish";
            case TROPICAL_FISH:
                return "Tropical Fish";
            case STONE:
                return "Stone";
            case COBBLESTONE:
                return "Cobblestone";
            case DIRT:
                return "Dirt";
            case GRASS_BLOCK:
                return "Grass Block";
            case GRAVEL:
                return "Gravel";
            case SAND:
                return "Sand";
            case RED_SAND:
                return "Red Sand";
            case CLAY:
                return "Clay Block";
            case ICE:
                return "Ice";
            case PACKED_ICE:
                return "Packed Ice";
            case BLUE_ICE:
                return "Blue Ice";
            case OBSIDIAN:
                return "Obsidian";
            case OAK_LOG:
                return "Oak Log";
            case BIRCH_LOG:
                return "Birch Log";
            case SPRUCE_LOG:
                return "Spruce Log";
            case JUNGLE_LOG:
                return "Jungle Log";
            case ACACIA_LOG:
                return "Acacia Log";
            case DARK_OAK_LOG:
                return "Dark Oak Log";
            case MANGROVE_LOG:
                return "Mangrove Log";
            case GRANITE:
                return "Granite";
            case DIORITE:
                return "Diorite";
            case ANDESITE:
                return "Andesite";
            case DEEPSLATE:
                return "Deepslate";
            case SANDSTONE:
                return "Sandstone";
            case WHITE_WOOL:
                return "Wool (White)";
            case GLASS:
                return "Glass";
            case BRICKS:
                return "Bricks";
            case SMOOTH_STONE:
                return "Smooth Stone";
            case STONE_BRICKS:
                return "Stone Bricks";
            case NETHER_BRICKS:
                return "Nether Bricks";
            case TERRACOTTA:
                return "Terracotta";
            case QUARTZ_BLOCK:
                return "Quartz Block";
            case PRISMARINE:
                return "Prismarine";
            case DARK_PRISMARINE:
                return "Dark Prismarine";
            case BLACKSTONE:
                return "Blackstone";
            case RED_MUSHROOM_BLOCK:
                return "Red Mushroom Block";
            case BROWN_MUSHROOM_BLOCK:
                return "Brown Mushroom Block";
            default:
                return "?";
        }
    }

    public void marketNPCMessage(MarketNPC npc, Player player, String message) {
        if(npc == MarketNPC.ALINE) {
            player.sendMessage("§8(§c§lMarket Guild§8) §fAline §8> §f" + message);
        }
        if(npc == MarketNPC.THEODORUS) {
            player.sendMessage("§8(§b§lMarket Guild§8) §fTheodorus §8> §f" + message);
        }
        if(npc == MarketNPC.MIA) {
            player.sendMessage("§8(§9§lMarket Guild§8) §fMia §8> §f" + message);
        }
        if(npc == MarketNPC.NOAH) {
            player.sendMessage("§8(§2§lMarket Guild§8) §fNoah §8> §f" + message);
        }
    }

    public void buyItem(ItemStack item, int amount, int money, Player player, MarketNPC npc) {
        if(!economyManager.hasEnoughBalance(player, money)) {
            if(npc == MarketNPC.ALINE) {
                marketNPCMessage(npc, player, "Sorry maar je hebt §c"+ money +" ⛀ §fnodig om dit te kopen.");
            }
            if(npc == MarketNPC.THEODORUS) {
                marketNPCMessage(npc, player, "Sorry maar je hebt §b"+ money +" ⛀ §fnodig om dit te kopen.");
            }
            if(npc == MarketNPC.MIA) {
                marketNPCMessage(npc, player, "Sorry maar je hebt §9"+ money +" ⛀ §fnodig om dit te kopen.");
            }
            if(npc == MarketNPC.NOAH) {
                marketNPCMessage(npc, player, "Sorry maar je hebt §2"+ money +" ⛀ §fnodig om dit te kopen.");
            }
            return;
        }
        Utils.giveItem(player, item, amount);


        player.sendMessage("§8§m----------------------------------------------------");
        final String color = npc == MarketNPC.ALINE ? "§c" : npc == MarketNPC.THEODORUS ? "§b" : npc == MarketNPC.MIA ? "§9" : npc == MarketNPC.NOAH ? "§2" : "§f";
        player.sendMessage(" "  + color + "§lItem Gekocht");
        player.sendMessage(" ");
        player.sendMessage("§fJe kocht een item van " + color + refactorName(npc));
        player.sendMessage(" ");
        player.sendMessage("  §fItem §8> §f§o" + refactorMarketItem(item.getType()));
        player.sendMessage("  §fHoeveelheid §8> §f§o" + amount + (amount == 1 ? " Stuk": " Stuks"));
        player.sendMessage("  §fPrijs §8> §f§o" + money + " ⛀");
        player.sendMessage("§8§m----------------------------------------------------");


        economyManager.removeBalance(player, money);
    }
}
