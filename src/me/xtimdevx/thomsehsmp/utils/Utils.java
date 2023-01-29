package me.xtimdevx.thomsehsmp.utils;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.commands.DonateAnnounceCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {


    public static ArrayList<String> inServerMessage = new ArrayList<>();

    public static void clearChat(Player player) {
        for(int i=0; i<50; i++)
        {
            player.sendMessage(" ");
        }
    }

    public static void createHologram(String text, Location location) {

        location.setY(location.getY() + 1);
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setCustomName(MessageUtils.format(text.replace("&", "§")));
        as.setCustomNameVisible(true);
        as.setVisible(false);
    }

    public static void saveInventory(Player player) {
        User user = User.get(player);

        user.getFile().set("inventory", null);
        user.saveFile();

        user.getFile().set("inventory.armor", player.getInventory().getArmorContents());
        user.getFile().set("inventory.content", player.getInventory().getContents());
        user.saveFile();
    }


    public static void restoreInventory(Player player) {
        User user = User.get(player);

        ItemStack[] content = ((List<ItemStack>) user.getFile().get("inventory.armor")).toArray(new ItemStack[0]);
        player.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) user.getFile().get("inventory.content")).toArray(new ItemStack[0]);
        player.getInventory().setContents(content);
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

    public static void winDuel(Player winner, Player loser) {

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

    public static void chat(String message) {
        for(Player online : Bukkit.getOnlinePlayers()) {
            if(!inServerMessage.contains(online.getName())) {
                online.sendMessage(MessageUtils.format(message));
            }
        }
    }

    public static void broadcastLanguage(String message, User.Languages language) {
        for(Player online : Bukkit.getOnlinePlayers()) {
            User user = User.get(online);
            if(language == User.Languages.DUTCH) {
                if(user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    online.sendMessage(message);
                }
            }
            if(language == User.Languages.ENGLISH) {
                if(user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    online.sendMessage(message);
                }
            }
        }
    }
}
