package me.xtimdevx.thomsehsmp.managers;

import jdk.jshell.execution.Util;
import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.commands.DuelCommand;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DuelsManager implements Listener {

    private static TriviaManager instance = new TriviaManager();

    public static TriviaManager getInstance() {
        return instance;
    }


    public static ArrayList<Player> duel = new ArrayList<>();
    public static ArrayList<Player> duelInvite = new ArrayList<>();

    public static int taskID = -1;
    public static int taskID2 = -1;
    public static int taskID3 = -1;


    public Inventory proposeKits(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, "§3§nKies de kit waar je mee wilt spelen:");
        ArrayList<String> lore = new ArrayList<String>();



        User user = User.get(player);

        ItemStack SG = new ItemStack(Material.FLINT_AND_STEEL);
        ItemMeta SGMeta = SG.getItemMeta();
        SGMeta.setDisplayName("§8> §2§lSG Kit §8<");
        lore.add(" ");
        lore.add("§8> §f§lItems");
        lore.add(" ");
        lore.add("§8> §fSword§8: §2§oIron Sword");
        lore.add(" ");
        lore.add("§8> §fHelmet§8: §2§oChainmail Helm");
        lore.add("§8> §fChestplate§8: §2§oIron Chestplate");
        lore.add("§8> §fLeggings§8: §2§oGold Leggings");
        lore.add("§8> §fBoots§8: §2§oChainmail Boots");
        lore.add(" ");
        lore.add("§8> §fExtra: §2§oBow, 10 Arrows en Flint and Steel");
        SGMeta.setLore(lore);
        SG.setItemMeta(SGMeta);
        inv.setItem(0, SG);
        lore.clear();

        ItemStack own = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta ownMeta = own.getItemMeta();
        ownMeta.setDisplayName("§8> §9§lEigen spullen §8<");
        lore.add(" ");
        lore.add("§8> §f§lSpeel het duel met je eigen stuff.");
        lore.add(" ");
        lore.add("§8> §fAls het duel klaar is krijg je all je spullen terug");
        lore.add("§8> §fin de §9originele §fstaat.");
        lore.add(" ");
        ownMeta.setLore(lore);
        own.setItemMeta(ownMeta);
        inv.setItem(8, own);
        lore.clear();

        player.openInventory(inv);
        return inv;
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        User user = User.get(player);

        if(player.getOpenInventory().getTitle().equalsIgnoreCase("§3§nKies de kit waar je mee wilt spelen:")) {
            event.setCancelled(true);

            if(event.getSlot() == 0) {
            }
            if(event.getSlot() == 8) {
                Player target = Bukkit.getPlayer(user.getFile().getString("duelrequest"));
                DuelsManager.duelInvite.add(target);

                User tuser = User.get(target);

                target.sendMessage("§8> §3§lDuel Aanvraag..");
                target.sendMessage("§8> §3" + player.getName() + " §fwil met je dueleren!");

                ComponentBuilder builder2 = new ComponentBuilder("");
                builder2.append("§8> §aAccepteren");
                builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept"));
                builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§aKlik Hier om het aanvraag te accepteren.")}));
                target.spigot().sendMessage(builder2.create());

                ComponentBuilder builder1 = new ComponentBuilder("");
                builder1.append("§8> §cWeigeren");
                builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel deny"));
                builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om het aanvraag te weigeren.")}));
                target.spigot().sendMessage(builder1.create());
                target.sendMessage("§8> §fMode: §3§oEigen Spullen");

                player.sendMessage("§8> §fDuel aanvraag verstuurd, ze hebben §330 §fseconden om te accepteren.");

                user.getFile().set("duelrequest", target.getName());
                user.saveFile();
                tuser.getFile().set("duelincoming", player.getName());
                tuser.getFile().set("duelmode", "own");
                tuser.saveFile();

                player.closeInventory();

                DuelsManager.taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        tuser.getFile().set("duelrequest", "none");
                        tuser.getFile().set("duelincoming", "none");
                        tuser.getFile().set("duelmode", null);
                        user.getFile().set("duelincoming", "none");
                        user.getFile().set("duelrequest", "none");
                        user.saveFile();
                        tuser.saveFile();
                        DuelsManager.duelInvite.remove(player);
                        if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                            player.sendMessage("§8> §fDuel request was not accepted.");
                        }
                        if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                            player.sendMessage("§8> §fDuel aanvraag was niet geaccepteerd.");
                        }
                    }
                }, 600);
            }
        }
    }


    public void startDuel(Player player, Player target, String mode) {
        User user = User.get(player);
        User tuser = User.get(target);
        if (mode.equalsIgnoreCase("sg")) {

        }
        if (mode.equalsIgnoreCase("own")) {

            tuser.getFile().set("duelrequest", "none");
            tuser.getFile().set("duelincoming", "none");
            tuser.getFile().set("duelmode", "own");
            user.getFile().set("duelincoming", "none");
            user.getFile().set("duelrequest", "none");
            user.getFile().set("duelmode", "own");
            Utils.saveInventory(player);
            Utils.saveInventory(target);
            user.saveFile();
            tuser.saveFile();

            player.sendMessage("§8> §fJe duel start in §35 §fseconden. Maak jezelf klaar!");

            target.sendMessage("§8> §fJe duel start in §35 §fseconden. Maak jezelf klaar!");

            taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lDuel Start in", "§4§l5");
                    player.sendTitle("§3§lDuel Start in", "§4§l5");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 0L);
            taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lDuel Start in", "§44");
                    player.sendTitle("§3§lDuel Start in", "§44");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 20L);
            taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lDuel Start in", "§c3");
                    player.sendTitle("§3§lDuel Start in", "§c3");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);


                }
            }, 40L);
            taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lDuel Start in", "§e2");
                    player.sendTitle("§3§lDuel Start in", "§e2");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }
            }, 60L);
            taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    target.sendTitle("§3§lDuel Start in", "§a1");
                    player.sendTitle("§3§lDuel Start in", "§a1");
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);


                }
            }, 80L);
            taskID2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {

                    Bukkit.getScheduler().cancelTask(ScoreboardManager.updateTaskID);
                    ScoreboardManager.createDuelBoard(player, target.getName(), "Eigen spullen");
                    ScoreboardManager.createDuelBoard(target, player.getName(), "Eigen spullen");

                    duel.add(player);
                    duel.add(target);

                    player.setHealth(20);
                    target.setHealth(20);
                    player.setFoodLevel(20);
                    target.setFoodLevel(20);

                    user.getFile().set("DuelTarget", target.getName());
                    tuser.getFile().set("DuelTarget", player.getName());
                    tuser.saveFile();
                    user.saveFile();


                    taskID3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            target.sendMessage(MessageUtils.PREFIX + "We beëindigen u duel!");
                            target.sendMessage("§8> §fU heeft te lang over u duel gedaan.");

                            player.sendMessage(MessageUtils.PREFIX + "We beëindigen u duel!");
                            player.sendMessage("§8> §fU heeft te lang over u duel gedaan.");


                            endDuel(player, target, "own", false, true);

                        }
                    }, 12000);

                }
            }, 100L);
        }

    }

    public void endDuel(Player player, Player target, String mode, boolean logout, boolean timeout) {
        User Duser = User.get(player);
        User Ruser = User.get(target);


        if(Duser.getFile().get("stats.duelwins") == null) {
            Duser.getFile().set("stats.duelwins", 1);
            Duser.saveFile();
        }else {
            Duser.getFile().set("stats.duelwins", Duser.getFile().getInt("stats.duelwins") + 1);
            Duser.saveFile();
        }

        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));

        target.getInventory().clear();
        target.getInventory().setHelmet(new ItemStack(Material.AIR));
        target.getInventory().setChestplate(new ItemStack(Material.AIR));
        target.getInventory().setLeggings(new ItemStack(Material.AIR));
        target.getInventory().setBoots(new ItemStack(Material.AIR));

        Utils.restoreInventory(player);
        Utils.restoreInventory(target);

        Bukkit.getScheduler().cancelTask(ScoreboardManager.dueltaskIDR);
        ScoreboardManager.createMainBoard(player);
        ScoreboardManager.createMainBoard(target);

        Bukkit.getScheduler().cancelTask(taskID3);

        duel.remove(player);
        duel.remove(target);
        duelInvite.remove(player);
        duelInvite.remove(target);

        Duser.getFile().set("DuelTarget", null);
        Ruser.getFile().set("DuelTarget", null);
        Ruser.getFile().set("duelmode", null);
        Duser.getFile().set("duelmode", null);
        Duser.saveFile();
        Ruser.saveFile();

        player.setFireTicks(0);
        target.setFireTicks(0);

        if(timeout) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lDuel beëindigd!");
            MessageUtils.sendCenteredMessage(player, "§c§lGelijkspel! §7§o(Tijd was op)");
            player.sendMessage(MessageUtils.format(target.getName() + "'s HP: §c§o" + Math.round(target.getHealth()) + "#810000 ❤"));
            player.sendMessage("§8§m----------------------------------------------------");

            target.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(target, "§3§lDuel beëindigd!");
            MessageUtils.sendCenteredMessage(target, "§c§lGelijkspel! §7§o(Tijd was op)");
            target.sendMessage(MessageUtils.format(player.getName() + "'s HP: §c§o" + Math.round(player.getHealth()) + "#810000 ❤"));
            target.sendMessage("§8§m----------------------------------------------------");

            if(mode.equalsIgnoreCase("own")) {
                Bukkit.broadcastMessage("§3§lDuels! §3§o" + player.getName() + " §fen §3§o" + target.getName() + " §fspeelde gelijkspel in een duel! §7§o(Eigen spullen)");
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.setHealth(20);
                    target.setHealth(20);
                    player.setFoodLevel(20);
                    target.setFoodLevel(20);
                }
            }, 20);
            return;
        }
        
        if(logout) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lDuel beëindigd!");
            MessageUtils.sendCenteredMessage(player, "§a§lU heeft gewonnen! §7§o(Speler logout)");
            player.sendMessage("§8§m----------------------------------------------------");


            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    player.setHealth(20);
                    target.setHealth(20);
                    player.setFoodLevel(20);
                    target.setFoodLevel(20);
                }
            }, 20);
            return;
        }

        player.sendMessage("§8§m----------------------------------------------------");
        MessageUtils.sendCenteredMessage(player, "§3§lDuel beëindigd!");
        MessageUtils.sendCenteredMessage(player, "§a§lU heeft gewonnen!");
        player.sendMessage("§8§m----------------------------------------------------");

        target.sendMessage("§8§m----------------------------------------------------");
        MessageUtils.sendCenteredMessage(target, "§3§lDuel beëindigd!");
        MessageUtils.sendCenteredMessage(target, "§c§lU heeft verloren.");
        target.sendMessage(MessageUtils.format(player.getName() + "'s HP: §c§o" + Math.round(player.getHealth()) + "#810000 ❤"));
        target.sendMessage("§8§m----------------------------------------------------");

        if(mode.equalsIgnoreCase("own")) {
            Bukkit.broadcastMessage("§3§lDuels! §3§o" + player.getName() + " §fwon van §3§o" + target.getName() + " §fin een duel! §7§o(Eigen spullen)");
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                player.setHealth(20);
                target.setHealth(20);
                player.setFoodLevel(20);
                target.setFoodLevel(20);
            }
        }, 20);

    }
}
