package me.xtimdevx.thomsehsmp.events;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.PortalType;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ServerEvents implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        if(Settings.getInstance().getData().getBoolean("maintenance")) {
            event.setMotd(MessageUtils.format("#01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP §7§oSeason 3\n" +
                    "    §fServer maintenance is enabled right now."));
            return;
        }
        event.setMotd(MessageUtils.format("          #01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP §7§oSeason 3 §8[§3§o1.19.2§8]\n" +
                " §fSurvival §8- §fCustom §8- §fCrates §8- §fEvents §8- §fMinigames"));
    }

    @EventHandler
    public void manipulate(PlayerArmorStandManipulateEvent e)
    {
        if(!e.getRightClicked().isVisible())
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void loadWorldEvent(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        World world = player.getWorld();

        if(Settings.getInstance().getData().getBoolean("end")) {
            return;
        }
        if(world.getName().contains("_end")) {
            player.teleport(LocationUtils.spawn);
            player.sendMessage("§8> §fThe End is currently closed and will be opened during an upcoming event.");
        }

    }
}
