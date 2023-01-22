package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.PortalType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ServerEvents implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        if(Settings.getInstance().getData().getBoolean("maintenance")) {
            event.setMotd(MessageUtils.format("                   #01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP §7§oSeason 2\n" +
                    "    §fServer maintenance is enabled right now."));
            return;
        }
        event.setMotd(MessageUtils.format("                   #01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP §7§oSeason 2\n" +
                " §fSurvival - Custom - Crates - Thomseh - [§l1.19.2§f]"));
    }

    @EventHandler
    public void manipulate(PlayerArmorStandManipulateEvent e)
    {
        if(!e.getRightClicked().isVisible())
        {
            e.setCancelled(true);
        }
    }
}
