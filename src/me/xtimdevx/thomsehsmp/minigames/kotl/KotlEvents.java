package me.xtimdevx.thomsehsmp.minigames.kotl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class KotlEvents implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(KotlMain.isInside(event.getPlayer().getLocation(), new Location(Bukkit.getWorld("SMP"), 145.5, 90, -108.5), new Location(Bukkit.getWorld("SMP"), 165.5, 60, -87.5))) {
            if(KotlMain.inKotl.contains(event.getPlayer().getUniqueId())) {
                return;
            }
            KotlMain.inKotl.add(event.getPlayer().getUniqueId());
            Bukkit.broadcastMessage("SUccess!!!!!!!!!");
        }else {
            if(KotlMain.inKotl.contains(event.getPlayer().getUniqueId())) {
                KotlMain.inKotl.remove(event.getPlayer().getUniqueId());
                Bukkit.broadcastMessage("left krottle <3");
            }
        }
    }
}
