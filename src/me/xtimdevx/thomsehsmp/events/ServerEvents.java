package me.xtimdevx.thomsehsmp.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerEvents implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        event.setMotd("§3§lOrigami SMP §8- §fCurrently in development! §oReleasing soon \n" +
                "§8> §7§oTwitch.tv/thomseh");
    }
}
