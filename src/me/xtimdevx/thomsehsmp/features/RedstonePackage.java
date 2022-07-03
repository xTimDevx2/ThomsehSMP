package me.xtimdevx.thomsehsmp.features;

import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RedstonePackage implements Listener {

    Utils utils = new Utils();

    public void giveRedstonePackage(Player player) {
        utils.giveItem(player, new ItemStack(Material.REDSTONE, 16));
        utils.giveItem(player, new ItemStack(Material.REPEATER, 3));
        utils.giveItem(player, new ItemStack(Material.COMPARATOR, 1));
        utils.giveItem(player, new ItemStack(Material.REDSTONE_TORCH, 5));
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();

        ItemStack cheque = player.getItemInHand();

        if(cheque.getType() == Material.REDSTONE) {
            if(cheque.getItemMeta().getDisplayName().contains("§8> §3§lRedstone Package §8<")) {

                if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    event.setCancelled(true);
                }

                if(cheque.getAmount() == 0) {
                    cheque.setType(Material.AIR);
                }else {
                    cheque.setAmount(cheque.getAmount() - 1);
                }
                giveRedstonePackage(player);
                player.sendMessage("§8> §fYou recieved the §cRedstone §fpackage!");


                return;
            }
        }
    }
}
