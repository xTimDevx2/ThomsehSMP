package me.xtimdevx.thomsehsmp.minigames.pushbattle;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.swing.*;

public class PushbattleEvents implements Listener {


    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if(PushbattleMain.inLobby.contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryInteractEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§8> §3Klik op mij om uit de lobby te gaan §8<")) {
                User user = User.get(player);
                player.teleport(user.getFile().getLocation("pblocation"));
                player.getInventory().clear();

                Utils.restoreInventory(player);
            }
        }
    }
}
