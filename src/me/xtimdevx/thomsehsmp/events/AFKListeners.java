package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.managers.AFKManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AFKListeners implements Listener {

    private final AFKManager afkManager;

    public AFKListeners(AFKManager afkManager) {
        this.afkManager = afkManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        afkManager.playerJoined(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        afkManager.playerLeft(e.getPlayer());
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent e){
        afkManager.playerMoved(e.getPlayer());
    }
}
