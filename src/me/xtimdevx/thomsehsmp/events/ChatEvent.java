package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.DateUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;

public class ChatEvent implements Listener{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        OfflinePlayer oplayer = event.getPlayer();
        Player player = event.getPlayer();

        User userM = User.get(player);

        event.setCancelled(true);

        if (userM.isMuted()) {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            Date date = new Date();
            event.setCancelled(true);
            if (userM.getUnmuteTime() == -1 || userM.getUnmuteTime() > date.getTime()) {
                    player.sendMessage(MessageUtils.GARY + "You have been muted for§8: §c" + userM.getMutedReason());
                if (userM.getUnmuteTime() < 0) {
                        player.sendMessage("§8> §fYour mute is permanent.");
                } else {
                        player.sendMessage("§8> §fYour mute expires in: §c" + DateUtils.formatDateDiff(userM.getUnmuteTime()));
                }
                return;
            } else {
                userM.unmute();
            }
        }

        if(player.isOp()) {
            Utils.chat(MessageUtils.returnPrefix(oplayer) + player.getName() + " §8> §f" + event.getMessage().replace("&", "§"));
        }else {
            Utils.chat(MessageUtils.returnPrefix(oplayer) + player.getName() + " §8> §f" + event.getMessage());

        }
        Bukkit.getLogger().info("Chat > " + player.getName() + ": " + event.getMessage());

    }
}
