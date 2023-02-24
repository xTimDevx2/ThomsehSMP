package me.xtimdevx.thomsehsmp.minigames.pushbattle;

import me.xtimdevx.thomsehsmp.utils.Utils;
import net.citizensnpcs.npc.ai.speech.Chat;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class PushbattleMain {

    public static boolean gameRunning;
    public static ArrayList inGame = new ArrayList();
    public static ArrayList teamRed = new ArrayList();
    public static ArrayList teamBlue = new ArrayList();
    public static ArrayList inLobby = new ArrayList();


    public static Location blueSpawn = new Location(Bukkit.getWorld("SMP"), 108.5, 63, -216.5);
    public static Location redSpawn = new Location(Bukkit.getWorld("SMP"), 108.5, 63, -221.5);

    /*
    Pushbattle:
        Spelers kunnen de game joinen via de command /pb join of de sign bij de arena.
        Er zijn 2 teams in de game. Rood en blauw
        Er zijn minstens 2 spelers nodig om de game te starten (maximum 8)
        De arena is voorzien om later te kunnen spelen met 4 teams.
        De spelers starten in de lobby tribune. Na 30 seconden met de minimum aantal spelers start de game.

          Player loadout:
           - Stick
           - Chestplate in kleur team
           - Candle in kleur team
           - nametag in kleur team.



           important:
           - Disable commands while ingame
           - Make sure inventories work.
           - Geen item drops
           - Geen echte deaths

        Game start.
     */


    public static void joinLobby(Player player) {
        if(conditionsMet(player)) {
            inLobby.add(player);
            decideTeam(player);


            String team;

            if(teamBlue.contains(player)) {
                blueSpawn.setPitch(0);
                blueSpawn.setYaw(110);

                player.teleport(blueSpawn);

                team = "blue";
            }else {
                redSpawn.setPitch(0);
                redSpawn.setYaw(104);

                player.teleport(redSpawn);

                team = "red";
            }


            Utils.saveInventory(player);

            player.getInventory().clear();


            ScoreboardManager scoreboard = Bukkit.getScoreboardManager();
            Scoreboard board = scoreboard.getNewScoreboard();
            Team teamRed = board.registerNewTeam("red");
            Team teamBlue = board.registerNewTeam("blue");

            teamRed.setPrefix(ChatColor.RED + "");
            teamBlue.setPrefix(ChatColor.BLUE + "");


            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
            if(team.equals("red")) {
                chestplateMeta.setColor(Color.RED);
                chestplate.setItemMeta(chestplateMeta);

                player.getInventory().setHelmet(new ItemStack(Material.RED_CANDLE));
                player.getInventory().setChestplate(new ItemStack(chestplate));

                teamRed.addEntry(player.getName());
                player.setPlayerListName(player.getPlayerListName().replace(player.getName(), ChatColor.RED + player.getName()));
            }else {
                chestplateMeta.setColor(Color.BLUE);
                chestplate.setItemMeta(chestplateMeta);

                player.getInventory().setHelmet(new ItemStack(Material.BLUE_CANDLE));
                player.getInventory().setChestplate(chestplate);

                teamBlue.addEntry(player.getName());
                player.setPlayerListName(player.getPlayerListName().replace(player.getName(), ChatColor.BLUE + player.getName()));
            }

        }
    }

    public static void decideTeam(Player player) {

        String team = sortTeams();

        if(team.equalsIgnoreCase("red")) {
            teamRed.add(player);
        }else {
            teamBlue.add(player);
        }
    }

    public static boolean conditionsMet(Player player) {
        if(gameRunning) {
            player.sendMessage("§cEr is al een pushbattle game bezig. Wacht tot de game afgelopen is.");
            return false;
        }

        if(player.getGameMode() != GameMode.SURVIVAL) {
            player.sendMessage("§cJe moet gamemode survival hebben om te kunnen joinen");
            return false;
        }
        if(inLobby.contains(player)) {
            player.sendMessage("§cJe zit al in de pushbattle lobby.");
            return false;
        }
        if(inGame.contains(player)) {
            player.sendMessage("§cJe zit al in de game.");
            return false;
        }

        return true;
    }


    public static String sortTeams() {
        if(teamRed.size() == 0) {
            return "red";
        }
        if(teamRed.size() == 1 && teamBlue.size() < 1) {
            return "blue";
        }
        if(teamBlue.size() == 1 && teamRed.size() < 2) {
            return "red";
        }
        if(teamRed.size() == 2 && teamBlue.size() < 2) {
            return "blue";
        }
        if(teamBlue.size() == 2 && teamRed.size() < 3) {
            return "red";
        }
        if(teamRed.size() == 3 && teamBlue.size() < 3) {
            return "blue";
        }
        if(teamBlue.size() == 3 && teamRed.size() < 4) {
            return "red";
        }
        if(teamRed.size() == 4 && teamBlue.size() < 4) {
            return "blue";
        }
        return null;
    }

}

