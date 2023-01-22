package me.xtimdevx.thomsehsmp.managers;
import me.xtimdevx.thomsehsmp.Settings;
import me.xtimdevx.thomsehsmp.utils.FileUtils;
import me.xtimdevx.thomsehsmp.utils.LocationUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Set;

public class WorldManager implements Listener {

    private Settings settings = Settings.getInstance();
    private static WorldManager instance = new WorldManager();

    public static WorldManager getInstance() {
        return instance;
    }

    public void loadWorlds() {
        try {
            Set<String> worlds = settings.getWorlds().getConfigurationSection("worlds").getKeys(false);

            for (String world : worlds) {
                String name = world;

                try {
                    loadWorld(name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
        }
    }

    public void createWorld(String name, int radius, long seed, World.Environment environment, WorldType type) {
        WorldCreator creator = new WorldCreator(name);
        creator.generateStructures(true);
        creator.environment(environment);
        creator.type(type);
        creator.seed(seed);

        World world = creator.createWorld();
        world.setDifficulty(Difficulty.HARD);
        world.setSpawnLocation(0, 0, 0);
        int y = LocationUtils.highestTeleportableYAtLocation(world.getSpawnLocation()) + 2;
        world.setSpawnLocation(0, y, 0);

        WorldBorder border = world.getWorldBorder();

        border.setSize(radius * 2);
        border.setCenter(0.0, 0.0);
        border.setWarningDistance(0);
        border.setWarningTime(60);
        border.setDamageAmount(0.1);
        border.setDamageBuffer(0);

        world.save();

        settings.getWorlds().set("worlds." + world.getName() + ".name", name);
        settings.getWorlds().set("worlds." + world.getName() + ".radius", radius);
        settings.getWorlds().set("worlds." + world.getName() + ".seed", seed);
        settings.getWorlds().set("worlds." + world.getName() + ".environment", environment.name());
        settings.getWorlds().set("worlds." + world.getName() + ".worldtype", type.name());
        settings.saveWorlds();
    }

    public boolean deleteWorld(World world) {
        for (Player player : world.getPlayers()) {
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }

        Bukkit.getServer().unloadWorld(world, false);

        if (FileUtils.deleteWorld(world.getWorldFolder())) {
            settings.getWorlds().set("worlds." + world.getName(), null);
            settings.saveWorlds();
            return true;
        }
        return false;
    }

    public void loadWorld(String name) throws Exception {
        Set<String> worlds = settings.getWorlds().getConfigurationSection("worlds").getKeys(false);

        if (!worlds.contains(name)) {
            throw new Exception("This world doesn't exist.");
        }

        WorldCreator creator = new WorldCreator(name);
        creator.generateStructures(true);

        long seed = settings.getWorlds().getLong("worlds." + name + ".seed", 2347862349786234l);
        World.Environment environment = World.Environment.valueOf(settings.getWorlds().getString("worlds." + name + ".environment", World.Environment.NORMAL.name()));
        WorldType worldtype = WorldType.valueOf(settings.getWorlds().getString("worlds." + name + ".worldtype", WorldType.NORMAL.name()));

        creator.environment(environment);
        creator.type(worldtype);
        creator.seed(seed);

        World world = creator.createWorld();
        world.setDifficulty(Difficulty.HARD);
        world.save();
    }

    public void unloadWorld(World world) {
        Bukkit.getServer().unloadWorld(world, false);
    }

}
