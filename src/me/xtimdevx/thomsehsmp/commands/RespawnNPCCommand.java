package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.npc.*;
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
            player.sendMessage("§cQuest NPC's: Aaron, Baldemar");
            player.sendMessage("§cMarket NPC's: Redstone, Resource, Fish, Block");

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
        if(args[0].equalsIgnoreCase("Redstone")) {
            Location npcSpawn = new Location(Bukkit.getWorld("world"), -4.5, 84, -55.5);
            npcSpawn.setYaw(-90);
            npcSpawn.setPitch(0);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "MarketRedstone");

            npc.setName("§cRedstone §f§lMarket §cAline");

            SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
            System.out.println(skinTrait.getSkinName());
            skinTrait.setSkinPersistent("redstonemarketsmpthomseh", "sfyQcGkECt3CdkVa2SoqhGqS5Rmj9tOmI3FxU/fQGuQh+FF+pY9kg0jm7Fr9XUMv+O3NhUi4sZJ7sgHJU+xhYNSl/INGr/kYTEIUQC5yUUnwj/wYkqUbJ4WEGpiPozdgTl3ZG6NZkEBIOSo6r1Pwoqz91UcP2040ewi4tLyGdOSIhefkXuhSPPEexBWeYR8B7XTQGyaXmkmaVqhd8kd8moDOae3/XJefBUDjT5Ygomt7zEQ0MVmQCiJqJXN/OavReXVhWsigBVpAoUuw7L+XXYGanRrsS827KWoLSxlLPDE5bLpv9oM9cD+SADdxIw/q6lXqzVnSpI8Oy4XAZKQJmWqlw0ZASygnQPLrM6ysTYVCiB8TWyu83UEcUoojtkPX7b/RAts4pu8/OhkVv/rBotX9KMfX/5kmvJ7N7sHbOekv5q87/wTste1GNPrtipm/TbNEjgxh9OYB6xpaGcciHWOgiXC281QyZBNBurGxSIp56IkNGK46/ixorjhacXcL2buNm6WKfhHPgfGL8xMJrFRR5undQydySO3gY/qxfQF18dBiunpE8UU+IQ5qbiJV+2l7t30tgESqNFKh4+mGnrXzIDYO4jyPOwp6oaUhCWCZ18z6oMab+oWN3OTapYuRg2kwapulYtwWWPTX6+a8hLBPeCwfU9tJpuDx2agzzR0=", "ewogICJ0aW1lc3RhbXAiIDogMTY1Njk2NjUzMzc2OSwKICAicHJvZmlsZUlkIiA6ICJmMTdkOWJlZmM2NGM0YzA4ODVhYWU3NWQ0YjhiNWE0NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJTaXJCYW5kZXJzbmF0Y2giLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGFjZGUxNGFiZGZiODNmMTI1YjYxMzc0MzVlYTg2NDg4ODM0MWM3MDhkY2EwNTdjZmQwYWExM2RhODBiMDZlMiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9");

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.REDSTONE, 1));

            npc.addTrait(RedstoneMarketTrait.class);

            npc.spawn(npcSpawn);


            player.sendMessage("§3§lSMP §8> §fSpawned Market NPC §3Aline§f.");
        }

        if(args[0].equalsIgnoreCase("Resource")) {
            Location npcSpawn = new Location(Bukkit.getWorld("world"), -7.5, 84, -58.5);
            npcSpawn.setYaw(180);
            npcSpawn.setPitch(0);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "MarketResource");

            npc.setName("§bResource §f§lMarket §bTheodorus");

            SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
            System.out.println(skinTrait.getSkinName());
            skinTrait.setSkinPersistent("resourcemarketsmpthomseh", "ZUYhH4gjexMZk/bp6mr8s5BByvYhcGX4GIGHskcwH1wzDzoSjVTRt3A7c2daezXZ0fJFq0Rp8B4XdQawdM8O/GsrklGO4CIm+yMWxnYmpszTZLYdBAEsQZSUjikrpz5l50KqSYhx4Gt4RWNV7U+livd027TdRz/nY0o/KAF7eNf1X1dbIMG6hswU2k6zJB1QyIs8RzU0lCiyRbGy1fGNRKXewN6bTO6uxL5aXmmAmwNHwG+hluQ9xGmPXHDXaJYcU+D3KtPBvjzlXIynRMmY55EqI5La40qmr1HpJThY+BDk25cgQAQgrCOyuyZHXsY7ONzZWwrJ33PVMbVKX5DwwRblMVxYh+J7VyacqBwJb55vAOtqkMRd9/gVdFam5ScNAsVLMK8hEFAisnsRAZ9q5iL1CCtGHH3NpNH+iHl7j4EFpjO5l9bZQKBWALGOdJbtmx7Z/QxW1LfzRIRZMWtzfP3XA/6L0pX7UOwkgt/MuueFLZNseA2MzyV8bSayqBdOpTnyLISigbjI3sxHHGaFlZLBbhkRmTpGfZ5ff7MIuZkeTQ6yW6crxNQ72/VqnXneBCC6u0+wdPLyLx+xWnMRW8S5YlyF+DxfKTNmgMSEy7XBq7GOYXEzbcFOqKFg5C0mdGKtYCeYc9lc+1VX1UtwUB5GUSnBa9UFqj6R24yRk+M=", "ewogICJ0aW1lc3RhbXAiIDogMTY1NzIyMTE2NDczNiwKICAicHJvZmlsZUlkIiA6ICI4YWFlYTdlYjViOWM0ZWEwODUxNWU3MDhhZGIxODBkNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJNYVBhODA3MTEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQzNjE2Mzg1Y2I3OWMzZDllOGMzZGNiN2RhYjg5YThlZDRhOTE1MWRlNGZkMTczYzAzNTFlODc3MDJiZGZjZiIKICAgIH0KICB9Cn0=");

            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.IRON_INGOT, 1));

            npc.addTrait(ResourceMarketTrait.class);

            npc.spawn(npcSpawn);


            player.sendMessage("§3§lSMP §8> §fSpawned Market NPC §3Theodorus§f.");
        }

        if(args[0].equalsIgnoreCase("Fish")) {
            Location npcSpawn = new Location(Bukkit.getWorld("world"), -7.5, 84, -52.5);
            npcSpawn.setYaw(0);
            npcSpawn.setPitch(0);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "MarketFish");

            npc.setName("§9Fish §f§lMarket §9Mia");

            SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
            System.out.println(skinTrait.getSkinName());
            skinTrait.setSkinPersistent("fishmarketsmpthomseh", "dOBOlOUH5iKJ9GjH6rISncVdpasWlYAeU/hY0n+w0iy2xgwa7LCA+iu57c+Ib/Lo8d1z1Ld1ruOpATnJtNbDxfmwiVOXfotp61ONNESXzmIC2Cf7jJCNF49e8PzpS0cPQ7E3AaOhTmnAIImeapb4j9fgmjn056T3iCv8RwC1WPZPp27TrPos+L7UemGsJx65MkYU3thOiG+sXH4j5DVJTPgFwTyOJOjGYXoTMVQlPTcESbexnaI1qb+xtYG4v8VUUQ9CUmPWq+AaAwGI27mnyzbyBmyw62lT+/RMLBo5+Hc43mccPzQGMSykCZNAni9A8Ag4ZSY+5RIoIP0d9A3L3tDA9rRc6mSwnJ408DG3PGALS70gGnByAyADvzJhhRqs7NibMqDnpjTpLs7yMrgeh4r8QncAoRx/vkUTckpudIvxfrFxkL4lAFJQfjypviNMidD4L+v70a1/eD1n0wG0mjFs2VWZSNoRoctLFzZX7QPJCe7B2eBAR18rEq+ADzHG4me1Xfiv+qY6xzCpIZl3rsuxEzdOoD4QynJSvjUuGm2FJMhJhdlrSZAn20tiHGvqdrAb+7GWbEre9BWKG6avOHidoRk7N/0e4y8paba9k0totSWwMmtliZ1kXg6NeK8QV3pRAAbFuXVmsL+QRAd52H5e8GKFN3SZ52+UkdEOU5k=", "ewogICJ0aW1lc3RhbXAiIDogMTY0OTA3OTg1ODg3NiwKICAicHJvZmlsZUlkIiA6ICJmNWQwYjFhZTQxNmU0YTE5ODEyMTRmZGQzMWU3MzA1YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYXRjaFRoZVdhdmUxMCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kZGY1NDU0ZDBiODFhMjk3Njg3NDllMmVlZTExNDUyNjA4ZTE5MDY4MzlkY2FkZTljZDdhYTk2ODE1OTU0NTFlIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=");

            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.FISHING_ROD, 1));

            npc.addTrait(FishMarketTrait.class);

            npc.spawn(npcSpawn);


            player.sendMessage("§3§lSMP §8> §fSpawned Market NPC §3Mia§f.");
        }

        if(args[0].equalsIgnoreCase("Block")) {
            Location npcSpawn = new Location(Bukkit.getWorld("world"), -10.5, 84, -55.5);
            npcSpawn.setYaw(90);
            npcSpawn.setPitch(0);
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "MarketBlock");

            npc.setName("§2Block §f§lMarket §2Noah");

            SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
            System.out.println(skinTrait.getSkinName());
            skinTrait.setSkinPersistent("blockmarketsmpthomseh", "gHn/TH3+HAxOF65P0F/Y0lD6w7UhFQj8p0IQdfFOgsUBpWBbtKu9KIdGQv1Fep8I+goyOsMVVPAhQ/1GDlNf4WN7iyCCvORe5f5PbJgWBM+R82qGX9YwARVwLpLwu/XpTzwbtuisL19M/qdULm3qB+1zrSHv42D07fpG+a7rhXhqDAnvKoXUKt8S3OyUccu4drfIh9R1ctor7kF0+KrZ6j4hGikhuHUVj0Xm5SLgJv9ILCfgN8cMjBvjIR3wbIpluvcvZ5xHJ5GbgrGpCKPrBdR5scvknX8iv+T2HEl58LMQfmy340z9CGCJnJio1wM8dFbq8Fp/1trMqAY2UjEemkGryBF4zdcXcGHyGY2lkm4TFTf6QoI7F0+b6HAh6KQe7VmzV96Mp1vJ2FytJq9v477D+zblsjdun75c18RR2O/obdSNhBwTinwaWGKUFruhQQrDS28jzgRGRQrjs9EQV74YD8eorTEFoWdnf//edglVi6KnQoRsxa+prlbUR5ASfiWSRjhAUyYmSUYTFjbKs74pjMYPTkmPEXnozUC41q2y40ECXWnV1ycOj+S5/FhImNLfJf5cdROM9NIRPfqyFIFZOejGuElAHmXAyFTBi0/Pnfj+30d83mCaei0+Q+y4XRxGgV05Ep0kAiaf/tv5LIBCoDGls1IpCMm4UUAtcHc=", "ewogICJ0aW1lc3RhbXAiIDogMTYxMjY5NDgyODQ3MiwKICAicHJvZmlsZUlkIiA6ICI2MTZiODhkNDMwNzM0ZTM3OWM3NDc1ODdlZTJkNzlmZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJfX25vdGFodW1hbl9fIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q4NWVjNWQ1OWEwNDhkMzNjYzVlNTUwNWNlZTJhZWMyMWIzNTRlMTc4ZDdkZjcwNzE3ZThiMGNlMmMyMDkyMWUiCiAgICB9CiAgfQp9");

            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.COBBLESTONE, 1));
            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, new ItemStack(Material.SPRUCE_LOG, 1));

            npc.addTrait(BlockMarketTrait.class);

            npc.spawn(npcSpawn);


            player.sendMessage("§3§lSMP §8> §fSpawned Market NPC §3Noah§f.");
        }
        return true;
    }
}
