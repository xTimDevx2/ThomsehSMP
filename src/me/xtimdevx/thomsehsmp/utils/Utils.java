package me.xtimdevx.thomsehsmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Utils {

    public static void clearChat(Player player) {
        for(int i=0; i<50; i++)
        {
            player.sendMessage(" ");
        }
    }

    public static boolean invFull(Player p) {

        return p.getInventory().firstEmpty() == -1;
    }

    public static void giveItem(Player player, ItemStack itemStack, int amount) {
        itemStack.setAmount(amount);
        if(!invFull(player)) {
            player.getInventory().addItem(itemStack);
        }else {
            Bukkit.getWorld(player.getLocation().getWorld().getName()).dropItem(player.getLocation(), itemStack);

        }
    }

    public static void giveItem(Player player, ItemStack itemStack) {
        if(!invFull(player)) {
            player.getInventory().addItem(itemStack);
        }else {
            Bukkit.getWorld(player.getLocation().getWorld().getName()).dropItem(player.getLocation(), itemStack);

        }
    }

    public static boolean consumeItem(Player player, int count, Material mat) {
        Map<Integer, ? extends ItemStack> ammo = player.getInventory().all(mat);

        int found = 0;
        for (ItemStack stack : ammo.values())
            found += stack.getAmount();
        if (count > found)
            return false;

        for (Integer index : ammo.keySet()) {
            ItemStack stack = ammo.get(index);

            int removed = Math.min(count, stack.getAmount());
            count -= removed;

            if (stack.getAmount() == removed)
                player.getInventory().setItem(index, null);
            else
                stack.setAmount(stack.getAmount() - removed);

            if (count <= 0)
                break;
        }

        player.updateInventory();
        return true;
    }
}
