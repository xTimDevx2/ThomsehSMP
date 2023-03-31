package me.xtimdevx.thomsehsmp.minigames.kotl;


import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.minigames.pushbattle.PushbattleEvents;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.UUID;

public class KotlMain {

    public static ArrayList<UUID> inKotl = new ArrayList<>();
    public static String king;

    public static boolean isInside(Location loc, Location l1, Location l2)
    {
        int x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        int z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        int x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        int z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());

        return loc.getX() >= x1 && loc.getX() <= x2 && loc.getZ() >= z1 && loc.getZ() <= z2;
    }

    public static void registerKotlEvents() {
        Bukkit.getPluginManager().registerEvents(new KotlEvents(), Main.plugin);
    }


    public static void enterKotl(Player player) {
        player.sendMessage(prefix + "Je bent de §c§oMonarch Of The Ladder §fbinnen gewandelt");

        Utils.saveInventory(player);
        player.getInventory().clear();


    }

    public static void leaveKotl(Player player) {
        player.sendMessage(prefix + "Je hebt de §c§oMonarch Of The Ladder §fverlaten");

        player.getInventory().clear();
        Utils.restoreInventory(player);
    }



    public static String prefix = "§c§lMotl §8> §f";
    public static void broadcastKotl(String message, boolean prefix) {
        for(Player inpb : Bukkit.getOnlinePlayers()) {
            if(inKotl.contains(inpb.getUniqueId())) {
                if(prefix) {
                    inpb.sendMessage(MessageUtils.format("§c§lMotl §8> §f" +  message));
                }else {
                    inpb.sendMessage(MessageUtils.format(message));

                }
            }
        }
    }


}
