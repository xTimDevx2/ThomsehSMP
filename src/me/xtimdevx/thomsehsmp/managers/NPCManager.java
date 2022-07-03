package me.xtimdevx.thomsehsmp.managers;

import me.xtimdevx.thomsehsmp.Settings;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.*;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.UUID;

public class NPCManager implements Listener {

    @EventHandler
    public void enablecitizens(CitizensEnableEvent enableEvent) {
        //AARON

    }


    @EventHandler
    public void citizenClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        Entity npc = event.getRightClicked();

        if(npc.hasMetadata("NPC")) {
            if(npc.getName().contains("§3Aaron")) {
                player.sendMessage("§cDEBUG");
            }

        }

    }

}
