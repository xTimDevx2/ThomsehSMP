package me.xtimdevx.thomsehsmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SummonArmorstandCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(player.isOp()) {
            player.performCommand("/summon armor_stand ~ ~ ~ {Invisible:1b,Invulnerable:1b,NoBasePlate:1b,NoGravity:1b,Rotation:[45f],ArmorItems:[{id:\"minecraft:air\",Count:1b},{id:\"minecraft:air\",Count:1b},{id:\"minecraft:air\",Count:1b},{id:\"minecraft:player_head\",Count:1b,tag:{SkullOwner:{Id:[I;-189389222,-1480700786,-1293643635,2043883638],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RmYWMwMTRiMjJjNmY5Njg1YTM2NGYzMTY3M2I4Zjc3MmRiNDc1Mzk1NWFjM2Y1Nzc0MTg2MDJmZjNiZDgifX19\"}]}}}}]}");
            player.sendMessage("§8> §fArmor stand spawned.");
        }
        return true;
    }
}
