package me.xtimdevx.thomsehsmp.minigames.kotl;


import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.minigames.pushbattle.PushbattleEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class KotlMain {

    public static ArrayList<UUID> inKotl = new ArrayList<>();

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


}
