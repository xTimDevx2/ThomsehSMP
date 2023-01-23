package me.xtimdevx.thomsehsmp.features;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.commands.CratesCommand;
import me.xtimdevx.thomsehsmp.crates.CratesCommands;
import me.xtimdevx.thomsehsmp.crates.CratesEvents;
import me.xtimdevx.thomsehsmp.crates.CratesManager;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Tutorial implements Listener {

    public static int taskID = -1;

    public void startTutorial(Player player) {
        User user = User.get(player);


        if(!user.getFile().getBoolean("tutorial")) {
            user.getFile().set("tutorial", true);
            user.saveFile();

        }else {
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8> §fTutorial already started.");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8> §fTutorial al begonnen.");
            }
            return;
        }

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                //86.5, 72.5, -118.5

                Location tut = new Location(Bukkit.getWorld("SMP"), 86.5, 72.5, -118.5);
                tut.setYaw(0);
                tut.setPitch(31);
                player.teleport(tut);
                player.setGameMode(GameMode.SPECTATOR);

                Utils.inServerMessage.add(player.getName());

                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendTitle("§f§lWelcome on", MessageUtils.format("#01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP"));

                    player.sendMessage("§8> §fWelcome §3§o" + player.getName() + " §fon " + MessageUtils.format("#01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP") + "§f!");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendTitle("§f§lWelkom op", MessageUtils.format("#01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP"));

                    player.sendMessage("§8> §fWelkom §3§o" + player.getName() + " §fop " + MessageUtils.format("#01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP") + "§f!");
                }



            }
        }, 20L);

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {

                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");
                    player.sendMessage("§8> §fThis is a small tutorial about our server.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");
                    player.sendMessage("§8> §fDit is een kleine basis tutorial over onze server.");
                }

            }
        }, 80L);

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {

                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fYou can claim a free daily crate using §3§o/reward§f.");

                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fWij hebben een feature op onze server waarmee je met §3§o/reward §felke dag 1 crate key kunt claimen.");

                }

            }
        }, 140L);

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fYou are able to warp to crates using §3§o/crates§f.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fJe kan warpen naar de crates met §3§o/crates§f.");
                }

                Location tut = new Location(Bukkit.getWorld("SMP"), 35.5, 72.2, -112.5);
                tut.setYaw(180);
                tut.setPitch(41);
                player.teleport(tut);
            }
        }, 200L);
        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fYou always get a nice price! Hit the crate to look at the rewards.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fHier krijg je altijd leuke rewards uit! Sla de crate om de rewards te zien.");
                }
            }
        }, 260L);
        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {

                CratesManager cratesManager = new CratesManager();

                cratesManager.openCratemenu(player);
            }
        }, 310L);

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                player.sendMessage("   ");

                Location tut = new Location(Bukkit.getWorld("SMP"), 86.5, 74, -136.5);
                tut.setYaw(-180);
                tut.setPitch(30);
                player.teleport(tut);
                player.closeInventory();

                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("§8> §fDid you get money out of the crate? Come to our market area!");
                    player.sendMessage("§8> §fYou can buy items from our NPC'S!");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("§8> §fHeb je geld gekregen uit onze crate? Kom dan naar onze market area");
                    player.sendMessage("§8> §fwaar je items kan kopen van onze NPC's!");
                }

            }
        }, 400L);

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fThey sell all kinds of usefull stuff, for example");
                    player.sendMessage("§8> §f§cRedstone§f, §9Fish§f, §2Resources §fand §aBlocks§f. And upcoming even more!");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fZe verkopen allerhanden nuttige dingen, zoals");
                    player.sendMessage("§8> §f§cRedstone§f, §9Vis§f, §2Resources §fen §aBlokken§f. En later nog veel meer!");
                }
            }
        }, 460L);

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fAlso keep an eye out for our easter eggs,");
                    player.sendMessage("§8> §fhidden troughout the spawn.");
                    player.sendMessage("§8> §fRight click them all to recieve a reward.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fHou ook een oogje open voor onze Easter Eggs");
                    player.sendMessage("§8> §fverstopt in onze spawn.");
                    player.sendMessage("§8> §fClick op ze allemaal en krijg een leuke reward.");

                }

            }
        }, 520L);

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {

                Location tut = new Location(Bukkit.getWorld("SMP"), 59.5, 89, -38.5);
                tut.setYaw(-153);
                tut.setPitch(33);
                player.teleport(tut);


                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fThis was our basic tutorial on our server!");
                    player.sendMessage("§8> §fPvP is §cDisabled§f!");
                    player.sendMessage("§8> §fGriefing is §c§lNOT §fallowed.");
                    player.sendMessage("§8> §fAlso dont forget to look at §3§o/rules §fto see all rules.");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fDit was de basis rondleiding op onze server!");
                    player.sendMessage("§8> §fPvP staat §cUit§f!");
                    player.sendMessage("§8> §fEn griefen is §c§lNIET §ftoegestaan.");
                    player.sendMessage("§8> §fDoe ook even §3§o/rules §fvoor alle regels.");

                }
            }
        }, 580L);

        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fThank you for playing on Origami SMP");
                    player.sendMessage("§8> §fand have lots of fun!");
                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §fBedankt voor het spelen op Origami SMP");
                    player.sendMessage("§8> §fEn veel plezier!");
                }

            }
        }, 640L);
        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                Location tut = new Location(Bukkit.getWorld("SMP"), 93.5, 70, -115.5);
                tut.setYaw(-45);
                tut.setPitch(0);
                player.teleport(tut);

                if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §f§a§lYou completed the tutorial!");

                }
                if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                    player.sendMessage("   ");

                    player.sendMessage("§8> §f§a§lJe hebt de tutorial volledig afgekeken!");

                }
                user.getFile().set("tutorial", false);
                user.saveFile();
                player.setGameMode(GameMode.SURVIVAL);
                Utils.inServerMessage.remove(player.getName());
            }
        }, 700L);

    }

    @EventHandler
    public void tpevent(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        User user = User.get(player);

        if(event.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
            if(user.getFile().getBoolean("tutorial")) {
                event.setCancelled(true);
            }
        }
    }
}
