package me.xtimdevx.thomsehsmp.quests;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class QuestEvents implements Listener {


    @EventHandler
    public void rightClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(player.getOpenInventory().getTitle().equalsIgnoreCase("§3§nQuest Menu:")) {
            event.setCancelled(true);
        }
    }
}
