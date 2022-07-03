package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.npc.AaronTrait;
import me.xtimdevx.thomsehsmp.npc.BaldemarTrait;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RespawnNPCCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length == 0) {
            player.sendMessage("§cUsage: /respawnnpc <npc>");
            player.sendMessage("§cNPC's: Aaron, Baldemar");

            return true;
        }
        if(args[0].equalsIgnoreCase("aaron")) {
            Location aaronSpawn = new Location(Bukkit.getWorld("world"), -65, 85, -105);
            aaronSpawn.setYaw(60);
            aaronSpawn.setPitch(0);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Aaron");

            npc.setName("§3Aaron §fthe lumberjack");

            SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
            System.out.println(skinTrait.getSkinName());
            skinTrait.setSkinPersistent("lumberjacksmpthomseh", "WjxnUctx69vNsdRQ46vuUCm0MhWCcrIcC1nuKAztlmPVYi8cpoXxfIHF/0vmrj/dWr8PW8EEypaQKfXHpWRfd1EQysVM16SoT4PmZ2e6zQzHZHTVEeee1CvneEYgeTEOyGqly2Ytj2IslurxPaaUCDEeQcL4XK9dyuBk9Gc6P1bwkL1s2c39w8WKohCq8/8v5Ymec3epoaHYnJm1cyD1+mHbUN6qfYqsW43pu4SA9ae0kmt0F9zg4Lj+uIxMpogqmESRZPTRFZUrvLcrKp+diHDG5PvpfgUEPS0G5su0hfWaJQ8vJ0EXZMm9DiEnrrOSYC2lipd6EM7H8uy9WZ/xfq4ypAbRoml2uYdCWRn3a4hK92OEzJ2ESyIwEOPibqlSykdvmDY37sRc3kZ36UrXn190vwCsAA9Be+3MNKZfIlcyKWM1GZCZ6u032UhyunLyeEK5ltO9K1wvLQ1rhDLRry1218o2O2KUW94/k+VWXi9d+misRRkUsb7YGI6BMlfMTQv7qifANddXF8PfpsYn/2C3hg66HGMBYqv4Y1A5+dI3SMU1wJfZBGLOqe0P2Op6UIzdEv+SlFkKYr39zlwJoLslyBV3rD9AqsiwYak/cLDEldxDJvYvK74Mum6laLrpdjXaTis3LbY8s60VpzdxGMTqh6etafxl5H60LRk+NHA=", "ewogICJ0aW1lc3RhbXAiIDogMTY1NjcxNDQ4ODc1NywKICAicHJvZmlsZUlkIiA6ICI1MTY4ZjZlMjIyM2E0Y2FjYjdiN2QyZjYyZWMxZGFhOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJkZWZfbm90X2FzaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MDBiMTBmMGU5ZDJiMTdiN2MxOWE4ZDk4YWExN2Q4MWQ1MmM4OWYyMDU3YTRmZjM4YmEwMzI5NDczODA2YzEyIgogICAgfQogIH0KfQ==");

            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.IRON_AXE, 1));


            npc.addTrait(AaronTrait.class);

            npc.spawn(aaronSpawn);


            player.sendMessage("§3§lSMP §8> §fSpawned quest NPC §3Aaron§f.");
        }
        if(args[0].equalsIgnoreCase("baldemar")) {
            Location npcSpawn = new Location(Bukkit.getWorld("world"), -65, 85, -118);
            npcSpawn.setYaw(60);
            npcSpawn.setPitch(-10);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Baldemar");

            npc.setName("§3Baldemar §fde smid");

            SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
            System.out.println(skinTrait.getSkinName());
            skinTrait.setSkinPersistent("baldemarsmidsmpthomseh", "Tb6hJhtkcvaDrFr4mW4Moam3/SzL9MiCa8jxIgbK75jRiQZU14xz6REpYXgS4TseUWbIhdwaoJ6EbKZ/Wnw1EPfrKKL24dwKn9ctYUW9qRE/NrhY/kHauX7VI3G2/kAAOHcVKuViltnlUXDb+F8VbKN7Csi7ZkM2OJNQR8gmkdMJ0J0kfHA+juO2QeVxfUT/u52Y1vnXGrHmKJsKMOkYPbC+W/ve6S8ant2v+BhhYmX6K2Vy0PwgvMkm0yBHbPLlui3Y5uaCNYGZttzIhy4Xf8wewZzPQKA6XyNyvs8N4+OfgZTrgtRszPnHxjTmwb+olDVtiCwe0XSfLe7J55dHiKInXADtr0RT+SIIk9r760j8G6GXc3Rzaaxqnk7bn9XWLyqDJ2eVctgUb6LeP1w3oLBuduOmrIDkW4K4MB35wTEFpxwAgOdXllLRDEI4pEzA7+43GqY62F9NYeLQ9sDq+w/gXZfmh/dMZao47O5EQK60NYp4x7Ve5JQe89UnOl9I63m0mrClXBwgQgLU/bC/+nykQGgQGof1O6sQuXymUL3G2zgSLay8QcHUv8lSN9zwP/dgIR9paED2qcQnrMeC6iyjCCmKDNxZ6IG2MTU8KXikZuJS14kG+ZUfzDYQZPhuOWGi1QrVutsqRDGtl2H9MpiYeLM3cdUegKumpMBmDTA=", "ewogICJ0aW1lc3RhbXAiIDogMTU4ODQ0MjIyODQyMywKICAicHJvZmlsZUlkIiA6ICJiMGQ0YjI4YmMxZDc0ODg5YWYwZTg2NjFjZWU5NmFhYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaW5lU2tpbl9vcmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDI3YTA0NWU2Mzk0NzZkOTI2ZmM4MGEwMDViYTYwZTA1NDhmNGI5M2IxMzE4OTNlNDMxNTIxYTgxNjliOTZjNyIKICAgIH0KICB9Cn0=");

            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.FLINT_AND_STEEL, 1));
            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, new ItemStack(Material.IRON_INGOT, 1));


            npc.addTrait(BaldemarTrait.class);

            npc.spawn(npcSpawn);


            player.sendMessage("§3§lSMP §8> §fSpawned quest NPC §3Baldemar§f.");
        }
        return true;
    }
}
