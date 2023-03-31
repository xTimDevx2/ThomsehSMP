package me.xtimdevx.thomsehsmp.minigames.kotl;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.minigames.pushbattle.PushbattleMain;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class KotlEvents implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(KotlMain.isInside(event.getPlayer().getLocation(), new Location(Bukkit.getWorld("SMP"), 145.5, 90, -108.5), new Location(Bukkit.getWorld("SMP"), 165.5, 60, -87.5))) {

            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§8> §fHuidige Monarch van de ladder: §c§o" + (KotlMain.king == null ? "Niemand" : KotlMain.king) + "§f. §8<"));
            if(KotlMain.inKotl.contains(event.getPlayer().getUniqueId())) {
                if(event.getPlayer().getLocation().getY() != 75 && event.getPlayer().getName().equals(KotlMain.king)) {
                    KotlMain.king = null;

                }
                return;
            }
            KotlMain.inKotl.add(event.getPlayer().getUniqueId());
            KotlMain.enterKotl(event.getPlayer());
        }else {
            if(KotlMain.inKotl.contains(event.getPlayer().getUniqueId())) {
                KotlMain.inKotl.remove(event.getPlayer().getUniqueId());
                KotlMain.leaveKotl(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void pressureEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.PHYSICAL)) {
            if (event.getClickedBlock().getType() == Material.OAK_PRESSURE_PLATE) {
                if(KotlMain.inKotl.contains(player.getUniqueId())) {
                    KotlMain.broadcastKotl("§c§o" + player.getName() + " §fIs nu de §c§oMonarch Of The Ladder§f!", true);
                    KotlMain.king = player.getName();
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(KotlMain.inKotl.contains(event.getPlayer().getUniqueId())) {
            KotlMain.inKotl.remove(event.getPlayer().getUniqueId());

            player.getInventory().clear();
            Utils.restoreInventory(player);

            player.teleport(LocationUtils.spawn);
        }

    }

    @EventHandler
    public void pvpEvent(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof  Player && event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            if(KotlMain.inKotl.contains(player.getUniqueId()) && KotlMain.inKotl.contains(damager.getUniqueId())) {
                event.setCancelled(false);
                event.setDamage(0);
            }
        }
    }

    @EventHandler
    public void damageEvent(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (KotlMain.inKotl.contains(player.getUniqueId())) {
                player.setHealth(20);
                player.setFoodLevel(20);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();
        Player player = (Player) event.getPlayer();
        User user = User.get(player);

        String command = message.split(" ")[0].substring(1);

        if (KotlMain.inKotl.contains(player.getUniqueId())) {
            player.sendMessage("§cJe kan geen commandos gebruiken wanneer je in Kotl zit.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player) {
            if(KotlMain.inKotl.contains(event.getEntity().getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

}
