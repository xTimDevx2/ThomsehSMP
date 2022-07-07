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
        ALINE;
    }

    public String refactorName(MarketNPC npc) {
        switch (npc) {
            case ALINE:
                return "Aline";
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
            default:
                return "?";
        }
    }

    public void marketNPCMessage(MarketNPC npc, Player player, String message) {
        if(npc == MarketNPC.ALINE) {
            player.sendMessage("§8(§c§lMarket Guild§8) §fAline §8> §f" + message);
        }
    }

    public void buyItem(ItemStack item, int amount, int money, Player player, MarketNPC npc) {
        if(!economyManager.hasEnoughBalance(player, money)) {
            marketNPCMessage(npc, player, "Sorry maar je hebt §c"+ money +" ⛀ §fnodig om dit te kopen.");
            return;
        }
        Utils.giveItem(player, item, amount);

        player.sendMessage("§8§m----------------------------------------------------");
        player.sendMessage("§c§lItem Gekocht");
        player.sendMessage(" ");
        player.sendMessage("§fJe kocht een item van §c" + refactorName(npc));
        player.sendMessage(" ");
        player.sendMessage("  §fItem §8> §f§o" + refactorMarketItem(item.getType()));
        player.sendMessage("  §fHoeveelheid §8> §f§o" + amount + (amount == 1 ? " Stuk": " Stuks"));
        player.sendMessage("  §fPrijs §8> §f§o" + money + " ⛀");
        player.sendMessage("§8§m----------------------------------------------------");


        economyManager.removeBalance(player, money);
    }
}
