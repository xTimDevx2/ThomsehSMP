package me.xtimdevx.thomsehsmp.utils;

import me.xtimdevx.thomsehsmp.managers.AFKManager;
import org.bukkit.Bukkit;

public class MovementChecker implements Runnable{

    private final AFKManager afkManager;

    public MovementChecker(AFKManager afkManager) {
        this.afkManager = afkManager;
    }

    @Override
    public void run() {

        afkManager.checkAllPlayersAFKStatus();

    }
}
