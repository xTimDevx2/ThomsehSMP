package me.xtimdevx.thomsehsmp.features;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.managers.EconomyManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MoneyCheques implements Listener {

    private final EconomyManager economyManager = new EconomyManager();

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();
        User user = User.get(player);

        ItemStack cheque = player.getItemInHand();

        if(cheque.getType() == Material.PAPER) {
            if(cheque.getItemMeta().getDisplayName().contains("Cheque §8< §7§o(Right click)")) {
                String itemName = cheque.getItemMeta().getDisplayName().replace(" ⛀ Cheque §8< §7§o(Right click)", "").replace("§8> §3§l", "");

                int amount = Integer.parseInt(itemName);

                if(cheque.getAmount() == 0) {
                    cheque.setType(Material.AIR);
                }else {
                    cheque.setAmount(cheque.getAmount() - 1);
                }
                economyManager.addBalance(player, amount);

                    player.sendMessage("§8> §fYou added §3" + amount + "§f⛀ to your account balance with a cheque.");

                return;
            }
        }
    }
}
