package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.managers.CooldownManager;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RewardCommand implements CommandExecutor {

    public static final int DAILYCOOLDOWN = 86400;
    public static Map<String, Long> cooldownsdaily = new HashMap<>();

    Utils utils = new Utils();


    public String convert(long secs) {
        long h = secs / 3600, i = secs - h * 3600, m = i / 60, s = i - m * 60;
        String timeF = "";

        if (h < 10) {
            timeF = timeF + "0";
        }
        timeF = timeF + h + ":";
        if (m < 10) {
            timeF = timeF + "0";
        }
        timeF = timeF + m + ":";
        if (s < 10) {
            timeF = timeF + "0";
        }
        timeF = timeF + s;

        return timeF;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);

        if (cmd.getName().equalsIgnoreCase("reward")) {

            if (cooldownsdaily.containsKey(player.getUniqueId().toString())) {

                long secondsleft = ((cooldownsdaily.get(player.getUniqueId().toString()) / 1000) + DAILYCOOLDOWN) - (System.currentTimeMillis() / 1000);

                if (secondsleft > 0) {
                    player.sendMessage("§8> §fYou have to wait " + convert(secondsleft) + " before claiming a new key.");

                } else {
                    cooldownsdaily.put(player.getUniqueId().toString(), System.currentTimeMillis());
                    player.sendMessage("§8> §fYou recieved your daily free key!");
                    ItemStack questkey = new ItemStack(Material.GOLDEN_CARROT);
                    ItemMeta questkeyMeta = questkey.getItemMeta();
                    questkeyMeta.setDisplayName("§8> §6§lDaily Crate Key §8<");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add("§8§m-------------------------");
                    lore.add("§fRight click §3Daily Crate");
                    lore.add("§fto recieve your rewards.");
                    lore.add("§3§o/warp crates");
                    lore.add("§8§m-------------------------");

                    questkeyMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);

                    questkeyMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    questkeyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    questkeyMeta.setLore(lore);
                    questkey.setItemMeta(questkeyMeta);
                    if (utils.invFull(player)) {
                        Bukkit.getWorld(player.getLocation().getWorld().getName()).dropItem(player.getLocation(), questkey);
                    }else {
                        player.getInventory().addItem(questkey);
                    }
                    lore.clear();
                }

            } else {
                cooldownsdaily.put(player.getUniqueId().toString(), System.currentTimeMillis());
                player.sendMessage("§8> §fYou recieved your daily free key!");
                ItemStack questkey = new ItemStack(Material.GOLDEN_CARROT);
                ItemMeta questkeyMeta = questkey.getItemMeta();
                questkeyMeta.setDisplayName("§8> §6§lDaily Crate Key §8<");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§8§m-------------------------");
                lore.add("§fRight click §3Daily Crate");
                lore.add("§fto recieve your rewards.");
                lore.add("§3§o/warp crates");
                lore.add("§8§m-------------------------");

                questkeyMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);

                questkeyMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                questkeyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                questkeyMeta.setLore(lore);
                questkey.setItemMeta(questkeyMeta);
                if (utils.invFull(player)) {
                    Bukkit.getWorld(player.getLocation().getWorld().getName()).dropItem(player.getLocation(), questkey);
                }else {
                    player.getInventory().addItem(questkey);
                }
                lore.clear();
            }
        }
        return true;
    }
}
