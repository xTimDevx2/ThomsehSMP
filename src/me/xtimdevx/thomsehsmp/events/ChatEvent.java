package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        OfflinePlayer oplayer = event.getPlayer();
        Player player = event.getPlayer();

        event.setCancelled(true);

        if(player.isOp()) {
            Bukkit.broadcastMessage(MessageUtils.returnPrefix(oplayer) + player.getName() + " §8> §f" + event.getMessage().replace("&", "§"));

        }else {
            Bukkit.broadcastMessage(MessageUtils.returnPrefix(oplayer) + player.getName() + " §8> §f" + event.getMessage());

        }
    }
}
