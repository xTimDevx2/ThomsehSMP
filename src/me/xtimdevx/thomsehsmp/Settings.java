package me.xtimdevx.thomsehsmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Settings {

    private static Settings instance = new Settings();

    private FileConfiguration data;
    private File dfile;

    private FileConfiguration worlds;
    private File wfile;

    public static Settings getInstance() {
        return instance;
    }

    public void setup() {
        if (!Main.plugin.getDataFolder().exists()) {
            Main.plugin.getDataFolder().mkdir();
        }

        dfile = new File(Main.plugin.getDataFolder(), "data.yml");

        if (!dfile.exists()) {
            try {
                dfile.createNewFile();
            } catch (Exception e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create data.yml!");
            }
        }

        data = YamlConfiguration.loadConfiguration(dfile);

        wfile = new File(Main.plugin.getDataFolder(), "worlds.yml");

        if (!wfile.exists()) {
            try {
                wfile.createNewFile();
            } catch (Exception e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create worlds.yml!");
            }
        }

        worlds = YamlConfiguration.loadConfiguration(wfile);


        Main.plugin.getLogger().info("Configs has been setup.");
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() {
        try {
            data.save(dfile);
        } catch (Exception e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(dfile);

    }

    public FileConfiguration getWorlds() {
        return worlds;
    }

    public void saveWorlds() {
        try {
            worlds.save(wfile);
        } catch (Exception e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save worlds.yml!");
        }
    }

    public void reloadWorlds() {
        worlds = YamlConfiguration.loadConfiguration(wfile);
    }
}

