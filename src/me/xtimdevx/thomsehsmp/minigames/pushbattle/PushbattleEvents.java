package me.xtimdevx.thomsehsmp.minigames.pushbattle;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PushbattleEvents implements Listener {

    public ArrayList inlobby = PushbattleMain.inLobby;

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
    public void tpEvent(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        User user = User.get(player);

        if(event.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
            if(PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = (Player) event.getPlayer();

        if(PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
            if(PushbattleMain.tutorial) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inventoryInteractEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(MessageUtils.format("§8> " + PushbattleMain.color +"Klik op mij om uit de lobby te gaan §8<"))) {
                PushbattleMain.leaveLobby(player);
            }
        }
    }

    @EventHandler
    public void inventoryManipulateEvent(InventoryInteractEvent event) {
        Player player = (Player) event.getInventory().getViewers();
        if(PushbattleMain.inLobby.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = (Player) event.getPlayer();
        if(PushbattleMain.inLobby.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();
        Player player = (Player) event.getPlayer();
        User user = User.get(player);

        String command = message.split(" ")[0].substring(1);

        if (PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
            if (!command.equalsIgnoreCase("pushbattle") && !command.equalsIgnoreCase("pb")) {
                player.sendMessage("§cJe kan enkel pushbattle commands gebruiken wanneer je in het spel zit.");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(PushbattleMain.inLobby.contains(player)) {
            PushbattleMain.leaveLobby(player);
        }
    }
    @EventHandler
    public void onSaturation(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();

        if(PushbattleMain.inLobby.contains(player) || PushbattleMain.inGame.contains(player)) {
            event.setCancelled(true);
        }
    }
}
