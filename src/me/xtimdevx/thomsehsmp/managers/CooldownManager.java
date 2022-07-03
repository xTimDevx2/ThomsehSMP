package me.xtimdevx.thomsehsmp.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Integer> cooldowns = new HashMap<>();

    public static final int RANDOMTP = 120;

    public void setCooldown(UUID player, int time) {
        if (time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }


    public int getCooldown(UUID player) {
        return cooldowns.getOrDefault(player, 0);
    }

}
