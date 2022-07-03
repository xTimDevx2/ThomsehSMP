package me.xtimdevx.thomsehsmp.crates;

import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.NameUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class CratesManager {

    private final Utils utils = new Utils();

    public enum CrateType {
        QUEST, LEVEL, DONATOR
    }

    public String lastreward = "none";

    public ItemStack randomReward(CrateType type) {
        if(type == CrateType.QUEST) {
            Random random = new Random();
            int rand = random.nextInt(100);
            if(rand > 0 && rand <= 5) {
                //500$
                ItemStack money500 = new ItemStack(Material.PAPER);
                ItemMeta money500meta = money500.getItemMeta();
                money500meta.setDisplayName("§8> §3§l500$ Cheque §8< §7§o(Right click)");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§8§m-------------------------");
                lore.add("§3§lMoney Cheque");
                lore.add("§f500$");
                lore.add("§7§oRight click to claim.");
                lore.add("§8§m-------------------------");

                money500meta.setLore(lore);

                money500.setItemMeta(money500meta);

                lore.clear();

                lastreward = "500$ Money Cheque";


                return money500;
            }

            if(rand > 5 && rand <= 10) {
                //750$
                ItemStack money750 = new ItemStack(Material.PAPER);
                ItemMeta money750meta = money750.getItemMeta();
                money750meta.setDisplayName("§8> §3§l750$ Cheque §8< §7§o(Right click)");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§8§m-------------------------");
                lore.add("§3§lMoney Cheque");
                lore.add("§f750$");
                lore.add("§7§oRight click to claim.");
                lore.add("§8§m-------------------------");

                money750meta.setLore(lore);

                money750.setItemMeta(money750meta);

                lore.clear();

                lastreward = "750$ Money Cheque";


                return money750;
            }

            if(rand > 10 && rand <= 15) {
                //Redstone package
                ItemStack reward = new ItemStack(Material.REDSTONE);
                ItemMeta rewardmeta = reward.getItemMeta();
                rewardmeta.setDisplayName("§8> §3§lRedstone Package §8< §7§o(Right click)");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§8§m-------------------------");
                lore.add("§3§lRedstone Package");
                lore.add("§fContains:");
                lore.add("§f- 16x Redstone Dust");
                lore.add("§f- 3x Redstone repeater");
                lore.add("§f- 1x Redstone comparator");
                lore.add("§f- 5x Redstone Torch");
                lore.add("§7§oRight click to claim.");
                lore.add("§8§m-------------------------");

                rewardmeta.setLore(lore);

                reward.setItemMeta(rewardmeta);

                lore.clear();

                lastreward = "Redstone package";


                return reward;
            }

            if(rand > 15 && rand <= 20) {
                //16 oak
                ItemStack reward = new ItemStack(Material.OAK_LOG, 16);

                lastreward = "Oak Log";

                return reward;
            }

            if(rand > 20 && rand <= 25) {
                //8 xp bottle
                ItemStack reward = new ItemStack(Material.EXPERIENCE_BOTTLE, 8);

                lastreward = "8 Bottles o' experience";

                return reward;
            }
            if(rand > 25 && rand <= 30) {
                //16 bone
                ItemStack reward = new ItemStack(Material.BONE, 16);

                lastreward = "16 Bones";

                return reward;
            }

            if(rand > 30 && rand <= 35) {
                //12 cooked pork
                ItemStack reward = new ItemStack(Material.COOKED_PORKCHOP, 12);

                lastreward = "12 Cooked Pork";

                return reward;
            }

            if(rand > 35 && rand <= 40) {
                //5 emerald
                ItemStack reward = new ItemStack(Material.EMERALD, 5);

                lastreward = "5 Emeralds";

                return reward;
            }

            if(rand > 40 && rand <= 45) {
                //3 Enderpearl
                ItemStack reward = new ItemStack(Material.ENDER_PEARL, 3);

                lastreward = "3 Enderpearls";

                return reward;
            }
            if(rand > 45 && rand <= 50) {
                //64 Torch
                ItemStack reward = new ItemStack(Material.TORCH, 64);

                lastreward = "64 Torches";

                return reward;
            }
            if(rand > 50 && rand <= 55) {
                //Horse Armor
                ItemStack reward = new ItemStack(Material.IRON_HORSE_ARMOR, 1);

                lastreward = "Horse Armor (Iron)";

                return reward;
            }
            if(rand > 55 && rand <= 60) {
                //Dragon's Breath
                ItemStack reward = new ItemStack(Material.DRAGON_BREATH, 1);

                lastreward = "Dragon Breath";

                return reward;
            }
            if(rand > 60 && rand <= 65) {
                //Saddle
                ItemStack reward = new ItemStack(Material.SADDLE, 1);

                lastreward = "Saddle";

                return reward;
            }
            if(rand > 65 && rand <= 70) {
                //5 Bamboo
                ItemStack reward = new ItemStack(Material.BAMBOO, 5);

                lastreward = "5 Bamboo";

                return reward;
            }
            if(rand > 70 && rand <= 75) {
                //2 Blaze rods
                ItemStack reward = new ItemStack(Material.BLAZE_ROD, 2);

                lastreward = "2 Blaze Rods";

                return reward;
            }
            if(rand > 75 && rand <= 80) {
                //Sponmge 4
                ItemStack reward = new ItemStack(Material.SPONGE, 4);

                lastreward = "4 Sponge";

                return reward;
            }
            if(rand > 80 && rand <= 85) {
                //gold carrot 8
                ItemStack reward = new ItemStack(Material.GOLDEN_CARROT, 8);

                lastreward = "8 Golden Carrots";

                return reward;
            }
            if(rand > 85 && rand <= 90) {
                //turtegg 2
                ItemStack reward = new ItemStack(Material.TURTLE_EGG, 2);

                lastreward = "2 Turtle Eggs";

                return reward;
            }
            if(rand > 90 && rand <= 93) {
                //Bow Kit
                ItemStack reward = new ItemStack(Material.BOW);
                ItemMeta rewardmeta = reward.getItemMeta();
                rewardmeta.setDisplayName("§8> §3§lBow Package §8< §7§o(Right click)");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§8§m-------------------------");
                lore.add("§3§lBow Package");
                lore.add("§fContains:");
                lore.add("§f- 1x Bow (Unbreaking 1)");
                lore.add("§f- 16x Arrow");
                lore.add("§7§oRight click to claim.");
                lore.add("§8§m-------------------------");

                rewardmeta.setLore(lore);

                reward.setItemMeta(rewardmeta);

                lore.clear();

                lastreward = "Bow Package";

                return reward;
            }
            if(rand == 94) {
                //DiaPick
                ItemStack diaPick = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                ItemMeta diaMeta = diaPick.getItemMeta();
                diaMeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                diaPick.setItemMeta(diaMeta);

                lastreward = "Diamond Pickaxe (Efficiency 3)";

                return diaPick;
            }
            if(rand > 94 && rand <= 96) {
                //Procbook
                ItemStack reward = new ItemStack(Material.ENCHANTED_BOOK, 1);
                ItemMeta rewardmeta = reward.getItemMeta();
                rewardmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                reward.setItemMeta(rewardmeta);

                lastreward = "Protection 2 Book";

                return reward;
            }
            if(rand == 97) {
                //Elytra
                ItemStack reward = new ItemStack(Material.ELYTRA, 1);

                lastreward = "Elytra (Legendary Reward)";

                return reward;
            }
            if(rand == 98) {
                //Trident
                ItemStack reward = new ItemStack(Material.TRIDENT, 1);

                lastreward = "Trident (Legendary Reward)";

                return reward;
            }
        }
        lastreward = "1 Dirt (SECRET SSSSHHH)";

        ItemStack dirt = new ItemStack(Material.DIRT);
        ItemMeta dirtmeta = dirt.getItemMeta();
        dirtmeta.setDisplayName("§dThe §lHOLY §ddirt");
        dirt.setItemMeta(dirtmeta);
        return dirt;
    }

    public void openCrate(CrateType type, Player player) {
        if(type == CrateType.QUEST) {
            utils.giveItem(player, randomReward(CrateType.QUEST));
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lDaily Crate Reward!");
            MessageUtils.sendCenteredMessage(player, "§fYou recieved:");
            MessageUtils.sendCenteredMessage(player, "§3" + lastreward);
            player.sendMessage("§8§m----------------------------------------------------");
            if(lastreward.equalsIgnoreCase("Elytra (Legendary Reward)")) {
                Bukkit.broadcastMessage("§3§lCrates §8> §3" + player.getName() + " §fjust got a §6§lLEGENDARY §freward from his daily crate. §7§o(Elytra)");
                Bukkit.broadcastMessage("§8> §fUse §3/reward §fto claim your free daily key.");
            }
            if(lastreward.equalsIgnoreCase("Trident (Legendary Reward)")) {
                Bukkit.broadcastMessage("§3§lCrates §8> §3" + player.getName() + " §fjust got a §6§lLEGENDARY §freward from his daily crate. §7§o(Trident)");
                Bukkit.broadcastMessage("§8> §fUse §3/reward §fto claim your free daily key.");
            }
        }
        if(type == CrateType.LEVEL) {

        }

        if(type == CrateType.DONATOR) {

        }
    }


    public static void spawnRedstone(Location location) {

        location.setX(location.getBlockX() + 0.5);
        location.setZ(location.getBlockZ() + 0.5);

        int red = 199;
        int green = 21;
        int blue = 255;
        location.getWorld().spawnParticle(Particle.REDSTONE, location, 1, new Particle.DustOptions(Color.fromBGR(red, green, blue), 1));
    }

    public Inventory openCratemenu(Player player) {
        Inventory inv = Bukkit.createInventory(player, 27, "§3§nCrate Rewards:");
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack fivehg = new ItemStack(Material.PAPER);
        ItemMeta fivehgMeta = fivehg.getItemMeta();
        fivehgMeta.setDisplayName("§7§l500$ §7Money Cheque");
        lore.add(" ");
        lore.add("§8> §7§oRight click to use.");
        lore.add(" ");
        fivehgMeta.setLore(lore);
        fivehg.setItemMeta(fivehgMeta);
        inv.setItem(0, fivehg);
        lore.clear();

        ItemStack zevenhe = new ItemStack(Material.PAPER);
        ItemMeta zevenheMeta = zevenhe.getItemMeta();
        zevenheMeta.setDisplayName("§7§l750$ §7Money Cheque");
        lore.add(" ");
        lore.add("§8> §7§oRight click to use.");
        lore.add(" ");
        zevenheMeta.setLore(lore);
        zevenhe.setItemMeta(zevenheMeta);
        inv.setItem(1, zevenhe);
        lore.clear();

        ItemStack redstonepak = new ItemStack(Material.REDSTONE);
        ItemMeta redstonepakMeta = redstonepak.getItemMeta();
        redstonepakMeta.setDisplayName("§7§lRedstone §7Pakket");
        lore.add(" ");
        lore.add("§8> §7§oRight click to use.");
        lore.add(" ");
        redstonepakMeta.setLore(lore);
        redstonepak.setItemMeta(redstonepakMeta);
        inv.setItem(2, redstonepak);
        lore.clear();

        ItemStack oaklog = new ItemStack(Material.OAK_LOG, 16);
        ItemMeta oaklogMeta = oaklog.getItemMeta();
        oaklogMeta.setDisplayName("§7§l16x §7Oak Logs");
        oaklog.setItemMeta(oaklogMeta);
        inv.setItem(3, oaklog);

        ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE, 8);
        ItemMeta xpMeta = xp.getItemMeta();
        xpMeta.setDisplayName("§7§l8x §7Bottle o' enchanting");
        xp.setItemMeta(xpMeta);
        inv.setItem(4, xp);

        ItemStack bone = new ItemStack(Material.BONE, 16);
        ItemMeta boneMeta = bone.getItemMeta();
        boneMeta.setDisplayName("§7§l16x §7Bones");
        bone.setItemMeta(boneMeta);
        inv.setItem(5, bone);

        ItemStack pork = new ItemStack(Material.COOKED_BEEF, 12);
        ItemMeta porkMeta = pork.getItemMeta();
        porkMeta.setDisplayName("§7§l12x §7Cooked beef");
        pork.setItemMeta(porkMeta);
        inv.setItem(6, pork);

        ItemStack emerald = new ItemStack(Material.EMERALD, 5);
        ItemMeta emeraldMeta = emerald.getItemMeta();
        emeraldMeta.setDisplayName("§7§l5x §7Emerald");
        emerald.setItemMeta(emeraldMeta);
        inv.setItem(7, emerald);

        ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 3);
        ItemMeta pearlMeta = pearl.getItemMeta();
        pearlMeta.setDisplayName("§7§l3x §7Ender Pearl");
        pearl.setItemMeta(pearlMeta);
        inv.setItem(8, pearl);

        ItemStack torch = new ItemStack(Material.TORCH, 64);
        ItemMeta torchMeta = torch.getItemMeta();
        torchMeta.setDisplayName("§7§l64x §7Torches");
        torch.setItemMeta(torchMeta);
        inv.setItem(9, torch);

        ItemStack ironarmor = new ItemStack(Material.IRON_HORSE_ARMOR, 1);
        ItemMeta ironarmorMeta = ironarmor.getItemMeta();
        ironarmorMeta.setDisplayName("§7§l1x §7Iron Horse Armor");
        ironarmor.setItemMeta(ironarmorMeta);
        inv.setItem(10, ironarmor);

        ItemStack reward = new ItemStack(Material.BOW);
        ItemMeta rewardmeta = reward.getItemMeta();
        rewardmeta.setDisplayName("§7§lBow §7Package");
        lore.add(" ");
        lore.add("§8> §7§oRight click to use.");
        lore.add(" ");
        rewardmeta.setLore(lore);
        reward.setItemMeta(rewardmeta);
        lore.clear();
        inv.setItem(11, reward);

        ItemStack adem = new ItemStack(Material.DRAGON_BREATH, 1);
        ItemMeta ademMeta = adem.getItemMeta();
        ademMeta.setDisplayName("§7§l1x §7Dragon Breath");
        adem.setItemMeta(ademMeta);
        inv.setItem(12, adem);

        ItemStack saddle = new ItemStack(Material.SADDLE, 1);
        ItemMeta saddleMeta = saddle.getItemMeta();
        saddleMeta.setDisplayName("§7§l1x §7Saddle");
        saddle.setItemMeta(saddleMeta);
        inv.setItem(13, saddle);

        ItemStack bamboo = new ItemStack(Material.BAMBOO, 5);
        ItemMeta bambooMeta = bamboo.getItemMeta();
        bambooMeta.setDisplayName("§7§l5x §7Bamboo");
        bamboo.setItemMeta(bambooMeta);
        inv.setItem(14, bamboo);

        ItemStack diaPick = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta diaMeta = diaPick.getItemMeta();
        diaMeta.setDisplayName("§7§l1x §7Diamond Pickaxe ");
        lore.add(" ");
        lore.add("§8> §7§oEfficiency 3.");
        lore.add(" ");
        diaMeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        diaMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        diaMeta.setLore(lore);
        diaPick.setItemMeta(diaMeta);
        lore.clear();
        inv.setItem(15, diaPick);

        ItemStack blaze = new ItemStack(Material.BLAZE_ROD, 2);
        ItemMeta blazeMeta = blaze.getItemMeta();
        blazeMeta.setDisplayName("§7§l2x §7Blaze Rods");
        blaze.setItemMeta(blazeMeta);
        inv.setItem(16, blaze);

        ItemStack sponge = new ItemStack(Material.SPONGE, 4);
        ItemMeta spongeMeta = sponge.getItemMeta();
        spongeMeta.setDisplayName("§7§l4x §7Sponge");
        sponge.setItemMeta(spongeMeta);
        inv.setItem(17, sponge);

        ItemStack book = new ItemStack(Material.BOOK, 1);
        ItemMeta bookMeta = book.getItemMeta();
        bookMeta.setDisplayName("§7§l1x §7Enchanted Book");
        lore.add(" ");
        lore.add("§8> §7§oProtection 2");
        lore.add(" ");
        bookMeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        bookMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bookMeta.setLore(lore);
        book.setItemMeta(bookMeta);
        lore.clear();
        inv.setItem(18, book);

        ItemStack goldcarrot = new ItemStack(Material.GOLDEN_CARROT, 8);
        ItemMeta goldcarrotMeta = goldcarrot.getItemMeta();
        goldcarrotMeta.setDisplayName("§7§l8x §7Golden Carrot");
        goldcarrot.setItemMeta(goldcarrotMeta);
        inv.setItem(19, goldcarrot);

        ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
        ItemMeta elytraMeta = elytra.getItemMeta();
        elytraMeta.setDisplayName("§7§l1x §7Elytra §6§lLEGENDARY");
        elytra.setItemMeta(elytraMeta);
        inv.setItem(20, elytra);

        ItemStack trident = new ItemStack(Material.TRIDENT, 1);
        ItemMeta tridentMeta = trident.getItemMeta();
        tridentMeta.setDisplayName("§7§l1x §7Trident §6§lLEGENDARY");
        trident.setItemMeta(tridentMeta);
        inv.setItem(21, trident);

        ItemStack egg = new ItemStack(Material.TURTLE_EGG, 2);
        ItemMeta eggMeta = egg.getItemMeta();
        eggMeta.setDisplayName("§7§l2x §7Turtle Egg");
        egg.setItemMeta(eggMeta);
        inv.setItem(22, egg);
        player.openInventory(inv);
        return inv;
    }
}
