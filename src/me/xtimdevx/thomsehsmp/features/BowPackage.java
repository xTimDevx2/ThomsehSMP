package me.xtimdevx.thomsehsmp.features;

import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BowPackage implements Listener {

    Utils utils = new Utils();

    public void giveBowPackage(Player player) {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowmeta = bow.getItemMeta();
        bowmeta.addEnchant(Enchantment.DURABILITY, 1, true);
        bow.setItemMeta(bowmeta);

        utils.giveItem(player, bow);
        utils.giveItem(player, new ItemStack(Material.ARROW, 16));
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();

        ItemStack cheque = player.getItemInHand();

        if(cheque.getType() == Material.BOW) {
            if(cheque.getItemMeta().getDisplayName().contains("§8> §3§LBow Package §8<")) {


                if(cheque.getAmount() == 0) {
                    cheque.setType(Material.AIR);
                }else {
                    cheque.setAmount(cheque.getAmount() - 1);
                }
                giveBowPackage(player);
                player.sendMessage("§8> §fYou recieved the §2Bow §fpackage!");


                return;
            }
        }
    }
}
