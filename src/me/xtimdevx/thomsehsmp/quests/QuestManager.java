package me.xtimdevx.thomsehsmp.quests;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestManager {


    public enum QuestLines {
        TIMBER, MINER;
    }

    public enum Quests {
        //TIMBER
        TIMBER_16LOGS, TIMBER_DIAMONDAXE, TIMBER_3SAPLING;

    }
    
    public enum NPCS {
        AARON, BALDEMAR;
    }

    int taskID = -1;

    public static List<String> NPCTalking = new ArrayList<>();

    public static List<UUID> TimberRequest16LOG = new ArrayList<>();
    public static List<UUID> TimberRequestDiaAXE = new ArrayList<>();
    public static List<UUID> TimberRequestSapling = new ArrayList<>();


    public void startQuestline(QuestLines questLines, Player player) {
        if(NPCTalking.contains(player.getName())) {
            return;
        }
        User user = User.get(player);

        if(questLines.equals(QuestLines.TIMBER)) {
            NPCTalking.add(player.getName());
            sendNPCMessage(NPCS.AARON, player, "Hey! I'm lumberjack Aaron.");

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    sendNPCMessage(NPCS.AARON, player, "Trees have been growing like crazy lately.");
                }
            }, 50L);

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    sendNPCMessage(NPCS.AARON, player, "I could really use some help to get them all down!");
                }
            }, 100L);

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    sendNPCMessage(NPCS.AARON, player, "I will reward you handsomely.");
                }
            }, 150L);
            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    sendNPCMessage(NPCS.AARON, player, "Do you accept my offer and will you help me?");

                    TimberRequest16LOG.add(player.getUniqueId());

                    player.sendMessage("§8> §3§lQuest offer! §fAaron the lumberjack requests your help.");
                    player.sendMessage("§8> §fDo you accept?");
                    ComponentBuilder builder2 = new ComponentBuilder("");
                    builder2.append("§8> §aAccept");
                    builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest accept"));
                    builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aClick here to accept this quest.")}));
                    player.spigot().sendMessage(builder2.create());

                    ComponentBuilder builder1 = new ComponentBuilder("");
                    builder1.append("§8> §cDeny");
                    builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest deny"));
                    builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick here to deny this quest.")}));
                    player.spigot().sendMessage(builder1.create());
                }
            }, 190L);
        }
    }
    
    public void sendNPCMessage(NPCS npc, Player player, String message) {
        if(npc.equals(NPCS.AARON)) {
            player.sendMessage("§8(§2§lLumberjack§8) §fAaron §8> §f" + message);
        }
        if(npc.equals(NPCS.BALDEMAR)) {
            player.sendMessage("§8(§4§lSmid§8) §fBaldemar §8> §f" + message);
        }
    }

    public boolean questlineStarted(QuestLines questline, Player player) {
        User user = User.get(player);

        String questlineName = questline.name();

        if(user.getFile().getBoolean("quest." + questlineName + ".started")) {
            return true;
        }
        return false;
    }



    public void startQuest(QuestLines questLines, Quests quests, Player player) {
        if(questLines == QuestLines.TIMBER) {
            if(quests == Quests.TIMBER_DIAMONDAXE) {
                if(NPCTalking.contains(player.getName())) {
                    return;
                }
                if(isQuestCompleted(QuestLines.TIMBER, Quests.TIMBER_DIAMONDAXE, player)) {
                    return;
                }
                int taskID = -1;
                NPCTalking.add(player.getName());
                sendNPCMessage(NPCS.AARON, player, "Hey wat goed om je weer te zien!");

                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        sendNPCMessage(NPCS.AARON, player, "Om onze volgende missie in succesvolle banen te lijden heb ik je hulp nodig reiziger.");
                    }
                },50L);

                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        sendNPCMessage(NPCS.AARON, player, "Er is een man in dorpje wat verder in het westen die kan een bijl maken waarmee je alle bomen kan omhakken.");
                    }
                },100L);
                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        sendNPCMessage(NPCS.AARON, player, "Ik heb jou hulp nodig om te materialen voor die bijl te verzamelen en ze bij hem binnen te brengen. Zou je dat voor mij kunnen doen reiziger?");
                    }
                },170L);
                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        TimberRequestDiaAXE.add(player.getUniqueId());

                        player.sendMessage("§8> §3§lQuest offer! §fAaron the lumberjack vraagt om jou hulp.");
                        player.sendMessage("§8> §fAccepteer je dit?");
                        ComponentBuilder builder2 = new ComponentBuilder("");
                        builder2.append("§8> §aAccepteren");
                        builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest accept"));
                        builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aClick hier om deze quest te accepteren.")}));
                        player.spigot().sendMessage(builder2.create());

                        ComponentBuilder builder1 = new ComponentBuilder("");
                        builder1.append("§8> §cWeigeren");
                        builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest deny"));
                        builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick hier om deze quest te weigeren.")}));
                        player.spigot().sendMessage(builder1.create());
                    }
                }, 240L);
            }
            if(quests == Quests.TIMBER_3SAPLING) {
                if(NPCTalking.contains(player.getName())) {
                    return;
                }
                if(isQuestCompleted(QuestLines.TIMBER, Quests.TIMBER_3SAPLING, player)) {
                    return;
                }
                int taskID = -1;
                NPCTalking.add(player.getName());
                sendNPCMessage(NPCS.AARON, player, "Goed je weer te zien reiziger! We gaan op zoek naar 3 van de moeilijkst verkrijgbare bomen in deze landen.");

                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        sendNPCMessage(NPCS.AARON, player, "Dit zijn de Jungle sappling, Accacia sappling en Spruce sappling.");
                    }
                },50L);

                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        sendNPCMessage(NPCS.AARON, player, "Je kan heatwaker gebruiken om deze bomen om te hakken je krijgt dan ook een verhoogde kans op sappling drops met deze bijl.");
                    }
                },100L);
                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        sendNPCMessage(NPCS.AARON, player, "Als je de sapplings naar mij terug brengt dan heb ik veel goede rewards voor je, ook mag je dan heatwaker houden.");
                    }
                },170L);
                taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        TimberRequestSapling.add(player.getUniqueId());

                        player.sendMessage("§8> §3§lQuest offer! §fAaron the lumberjack vraagt om jou hulp.");
                        player.sendMessage("§8> §fAccepteer je dit?");
                        ComponentBuilder builder2 = new ComponentBuilder("");
                        builder2.append("§8> §aAccepteren");
                        builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest accept"));
                        builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aClick hier om deze quest te accepteren.")}));
                        player.spigot().sendMessage(builder2.create());

                        ComponentBuilder builder1 = new ComponentBuilder("");
                        builder1.append("§8> §cWeigeren");
                        builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest deny"));
                        builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick hier om deze quest te weigeren.")}));
                        player.spigot().sendMessage(builder1.create());
                    }
                }, 240L);
            }
        }
    }

    public boolean isQuestActive(QuestLines questLines, Quests quest, Player player) {
        User user = User.get(player);
        if (user.getFile().getBoolean("quest." + questLines + "." + quest + ".active")) {
            return true;
        }
        return false;
    }

    public boolean isQuestCompleted(QuestLines questLines, Quests quest, Player player) {
        User user = User.get(player);
        if (user.getFile().getBoolean("quest." + questLines + "." + quest + ".completed")) {
            return true;
        }
        return false;
    }

    public void npcReply(QuestLines questLines, Quests quest, Player player) {
        User user = User.get(player);
        if(questLines == QuestLines.TIMBER) {
            if(quest == Quests.TIMBER_16LOGS) {
                Inventory inventory = player.getInventory();
                if(!user.getFile().getBoolean("quest.TIMBER.TIMBER_16LOGS.active")) {
                    return;
                }

                if(player.getItemInHand().getType() == Material.OAK_LOG || player.getItemInHand().getType() == Material.ACACIA_LOG|| player.getItemInHand().getType() == Material.BIRCH_LOG || player.getItemInHand().getType() == Material.DARK_OAK_LOG || player.getItemInHand().getType() == Material.JUNGLE_LOG|| player.getItemInHand().getType() == Material.MANGROVE_LOG || player.getItemInHand().getType() == Material.SPRUCE_LOG) {
                    if(player.getItemInHand().getAmount() < 16) {
                        return;
                    }
                    if(player.getItemInHand().getAmount() != 15) {
                        player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 16);
                    }else {
                        player.getItemInHand().setType(Material.AIR);
                    }

                    user.getFile().set("quest.TIMBER.TIMBER_16LOGS.active", false);
                    user.getFile().set("quest.TIMBER.TIMBER_16LOGS.completed", true);
                    user.saveFile();

                    player.sendMessage("§8§m----------------------------------------------------");
                    MessageUtils.sendCenteredMessage(player, "§3§lQuest Completed!");
                    MessageUtils.sendCenteredMessage(player, "§fAaron the lumberjack ");
                    player.sendMessage(" ");
                    MessageUtils.sendCenteredMessage(player, "§fChapter §31§8/§33");
                    MessageUtils.sendCenteredMessage(player, "§fGather 16 logs and bring them to Aaron.");
                    player.sendMessage(" ");
                    MessageUtils.sendCenteredMessage(player, "§fTalk to Aaron again to recieve your next .");
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    player.sendTitle("§3§lQuest Completed", "§fChapter 1!");
                    player.sendMessage("§8§m----------------------------------------------------");

                    sendNPCMessage(NPCS.AARON, player, "Hey! Thanks for helping me out, come talk to me again soon!");
                }else {
                    sendNPCMessage(NPCS.AARON, player, "You need to return 16 logs of whatever kind to me to complete this quest.");
                }
            }
        }
    }



    public Inventory openQuestmenu(Player player) {
        User user = User.get(player);
        Inventory inv = Bukkit.createInventory(player, 54, "§3§nQuest Menu:");
        ArrayList<String> lore = new ArrayList<String>();

        ItemStack glass = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);

        inv.setItem(0, glass);
        inv.setItem(9, glass);
        inv.setItem(18, glass);
        inv.setItem(27, glass);
        inv.setItem(36, glass);
        inv.setItem(45, glass);
        inv.setItem(46, glass);
        inv.setItem(47, glass);
        inv.setItem(51, glass);
        inv.setItem(52, glass);
        inv.setItem(53, glass);
        ItemStack QuestTimber = new ItemStack(Material.BOOK);
        ItemMeta QuestTimberMeta = QuestTimber.getItemMeta();
        QuestTimberMeta.setDisplayName("§8> §3§lTimber §f§oQuest §8<");
        lore.add(" ");
        lore.add("§8§m-------------------------");
        lore.add("         §3§lTimber");
        lore.add("§fChapters: §33");
        lore.add(" ");
        lore.add("§3§oInstructions: ");
        lore.add("§fTo start of this quest you");
        lore.add("§fhave to talk to talk to");
        lore.add("§flumberjack §3Aaron at:");
        lore.add("   §f§ox:-33, y:83, z:-133");
        lore.add(" ");
        if(isQuestCompleted(QuestLines.TIMBER, Quests.TIMBER_3SAPLING, player)) {
            lore.add("§fStatus: §a§lCOMPLETED");
            QuestTimberMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);

        }else {
            lore.add("§fStatus: " + (user.getFile().getBoolean("quest.TIMBER.started") ? "§a§lONGOING" : "§C§LNOT STARTED")+"");
        }
        lore.add("§8§m-------------------------");
        lore.add(" ");
        QuestTimberMeta.setLore(lore);
        QuestTimberMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        QuestTimberMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        QuestTimber.setItemMeta(QuestTimberMeta);
        inv.setItem(1, QuestTimber);
        lore.clear();

        ItemStack QuestTimber16LOG = new ItemStack(Material.PAPER);
        ItemMeta QuestTimber16LOGMeta = QuestTimber16LOG.getItemMeta();
        QuestTimber16LOGMeta.setDisplayName("§8> §3§lTimber §f§oQuest §8<");
        lore.add(" ");
        lore.add("§8§m-------------------------");
        lore.add("         §3§lTimber");
        lore.add("§fChapter: §3§l1§f/§3§l3");
        lore.add(" ");
        lore.add("§3§oInstructies: ");
        lore.add("§fVerzamel 16 wooden logs");
        lore.add("§fen breng deze terug naar Aaron");
        lore.add(" ");
        if(isQuestCompleted(QuestLines.TIMBER, Quests.TIMBER_16LOGS, player)) {
            lore.add("§fStatus: §a§lCOMPLETED");
            QuestTimber16LOGMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1,true);
        }else {
            lore.add("§fStatus: " + (user.getFile().getBoolean("quest.TIMBER.TIMBER_16LOGS.active") ? "§a§lONGOING" : "§C§LNOT STARTED")+"");
        }
        lore.add("§8§m-------------------------");
        lore.add(" ");
        QuestTimber16LOGMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        QuestTimber16LOGMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        QuestTimber16LOGMeta.setLore(lore);
        QuestTimber16LOG.setItemMeta(QuestTimber16LOGMeta);
        inv.setItem(2, QuestTimber16LOG);
        lore.clear();

        ItemStack QuestTimberDIAAXE = new ItemStack(Material.PAPER);
        ItemMeta QuestTimberDIAAXEMeta = QuestTimberDIAAXE.getItemMeta();
        QuestTimberDIAAXEMeta.setDisplayName("§8> §3§lTimber §f§oQuest §8<");
        lore.add(" ");
        lore.add("§8§m-------------------------");
        lore.add("         §3§lTimber");
        lore.add("§fChapter: §3§l2§f/§3§l3");
        lore.add(" ");
        lore.add("§3§oInstructies: ");
        lore.add("§fBreng 4 diamonds, 2 sticks");
        lore.add("§fen 1 bookshelf naar Baldemar de smid");
        lore.add(" ");
        if(isQuestCompleted(QuestLines.TIMBER, Quests.TIMBER_DIAMONDAXE, player)) {
            lore.add("§fStatus: §a§lCOMPLETED");
            QuestTimberDIAAXEMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        }else {
            lore.add("§fStatus: " + (user.getFile().getBoolean("quest.TIMBER.TIMBER_DIAMONDAXE.active") ? "§a§lONGOING" : "§C§LNOT STARTED")+"");
        }
        lore.add("§8§m-------------------------");
        lore.add(" ");
        QuestTimberDIAAXEMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        QuestTimberDIAAXEMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        QuestTimberDIAAXEMeta.setLore(lore);
        QuestTimberDIAAXE.setItemMeta(QuestTimberDIAAXEMeta);
        inv.setItem(3, QuestTimberDIAAXE);
        lore.clear();

        ItemStack QuestSapplings = new ItemStack(Material.PAPER);
        ItemMeta QuestSapplingsMeta = QuestSapplings.getItemMeta();
        QuestSapplingsMeta.setDisplayName("§8> §3§lTimber §f§oQuest §8<");
        lore.add(" ");
        lore.add("§8§m-------------------------");
        lore.add("         §3§lTimber");
        lore.add("§fChapter: §3§l3§f/§3§l3");
        lore.add(" ");
        lore.add("§3§oInstructies: ");
        lore.add("§fVerzamel een accacia, jungle");
        lore.add("§fen spruce sappling en breng");
        lore.add("§fdeze naar Aaron.");
        lore.add(" ");
        if(isQuestCompleted(QuestLines.TIMBER, Quests.TIMBER_3SAPLING, player)) {
            lore.add("§fStatus: §a§lCOMPLETED");
            QuestSapplings.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        }else {
            lore.add("§fStatus: " + (user.getFile().getBoolean("quest.TIMBER.TIMBER_3SAPLING.active") ? "§a§lONGOING" : "§C§LNOT STARTED")+"");
        }
        lore.add("§8§m-------------------------");
        lore.add(" ");
        QuestSapplingsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        QuestSapplingsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        QuestSapplingsMeta.setLore(lore);
        QuestSapplings.setItemMeta(QuestSapplingsMeta);
        inv.setItem(4, QuestSapplings);
        lore.clear();


        player.openInventory(inv);
        return inv;

    }

    public static void spawnAnvilParticle() {

        Location loc1 = new Location(Bukkit.getWorld("world"), -66, 86.2, -118);
        Location loc2 = new Location(Bukkit.getWorld("world"), -66.5, 86.2, -118.4);
        Location loc3 = new Location(Bukkit.getWorld("world"), -66.5, 86.2, -118.1);
        Location loc4 = new Location(Bukkit.getWorld("world"), -66.2, 86.2, -118.3);
        Location loc5 = new Location(Bukkit.getWorld("world"), -66.8, 86.2, -118.6);
        Location loc6 = new Location(Bukkit.getWorld("world"), -66.1, 86.2, -118.9);

        int red = 249;
        int green = 1;
        int blue = 1;
        loc1.getWorld().spawnParticle(Particle.REDSTONE, loc1, 1, new Particle.DustOptions(Color.fromBGR(red, green, blue), 1));
        loc1.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, new Particle.DustOptions(Color.fromBGR(red, green, blue), 1));
        loc1.getWorld().spawnParticle(Particle.REDSTONE, loc3, 1, new Particle.DustOptions(Color.fromBGR(red, green, blue), 1));
        loc1.getWorld().spawnParticle(Particle.REDSTONE, loc4, 1, new Particle.DustOptions(Color.fromBGR(red, green, blue), 1));
        loc1.getWorld().spawnParticle(Particle.REDSTONE, loc5, 1, new Particle.DustOptions(Color.fromBGR(red, green, blue), 1));
        loc1.getWorld().spawnParticle(Particle.REDSTONE, loc6, 1, new Particle.DustOptions(Color.fromBGR(red, green, blue), 1));

    }
}
