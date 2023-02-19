package me.xtimdevx.thomsehsmp.minigames.pushbattle;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PushbattleMain {

    public static boolean gameRunning;
    public static ArrayList inGame = new ArrayList();
    public static ArrayList teamRed = new ArrayList();
    public static ArrayList teamBlue = new ArrayList();
    public static ArrayList inLobby = new ArrayList();

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
           -

        Game start.
     */


    public static void joinLobby(Player player) {
        if(conditionsMet(player)) {

        }
    }

    public static void decideTeam(Player player) {

    }

    public static boolean conditionsMet(Player player) {
        if(gameRunning) {
            player.sendMessage("§cEr is al een pushbattle game bezig. Wacht tot de game afgelopen is.");
            return false;
        }

        if(player.getGameMode() != GameMode.SURVIVAL) {
            player.sendMessage("§cJe moet gamemode survival hebben om");
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

        inLobby.add(player);
        if(sortTeams().equalsIgnoreCase("red")) {
            teamRed.add(player);
        }else {
            teamBlue.add(player);
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

