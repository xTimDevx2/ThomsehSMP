package me.xtimdevx.thomsehsmp.features;

import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DonatorWand implements Listener, CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("amulet")) {
            ItemStack amulet = new ItemStack(Material.AMETHYST_SHARD);
            ItemMeta amuletMeta = amulet.getItemMeta();
            amuletMeta.setDisplayName(MessageUtils.format("§8§k:#D46CFE§lDreambender§8§k:"));
            amulet.setItemMeta(amuletMeta);
            Player player = (Player) sender;
            if(!player.hasPermission("smp.feature.amulet")) {
                player.sendMessage(MessageUtils.PREFIX + "Alleen §6§lGold §fkan deze feature gebruiken.");
                player.sendMessage("§8> §3§nstore.thomseh.live §fof §3§o/buy");
                return true;
            }

            if(player.getInventory().contains(amulet)) {
                player.sendMessage("§cError: Je hebt al een amulet in je inventory.");
                return true;
            }

            if(!player.getInventory().getItem(8).equals(Material.AIR)) {
                player.sendMessage("§cError: Je laatste slot van je hotbar moet vrij zijn om je amulet te ontvangen.");
                return true;
            }

            player.getInventory().setItem(8, amulet);
        }
        return true;
    }

    @EventHandler
    public void rightClickEvent(InventoryClickEvent event) {

    }
}
