package me.xtimdevx.thomsehsmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class User {

    private Player player;
    private String uuid;

    private FileConfiguration config;
    private File file;

    private int taskID = -1;


    private boolean creating = false;

    public static User get(Player player) {
        return new User(player, player.getUniqueId().toString());
    }

    public static User get(OfflinePlayer offline) {
        return new User(offline.getPlayer(), offline.getUniqueId().toString());
    }

    private User(Player player, String uuid) {
        if (!Main.plugin.getDataFolder().exists()) {
            Main.plugin.getDataFolder().mkdir();
        }

        File folder = new File(Main.plugin.getDataFolder() + File.separator + "users" + File.separator);

        if (!folder.exists()) {
            folder.mkdir();
        }

        file = new File(folder, uuid + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                creating = true;
            } catch (Exception e) {
                Main.plugin.getLogger().severe(ChatColor.RED + "Could not create " + uuid + ".yml!");
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

        this.player = player;
        this.uuid = uuid;

        if (creating) {
            if (player != null) {
                config.set("username", player.getName());
                config.set("uuid", player.getUniqueId().toString());
                config.set("ip", player.getAddress().getAddress().getHostAddress());
            }

            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

            config.set("firstjoined", new Date().getTime());
            config.set("lastlogin", new Date().getTime());
            config.set("lastlogoff", -1l);
            config.set("new", true);

            config.set("PPS", 0);

            config.set("muted.status", false);
            config.set("muted.reason", "NOT_MUTED");
            config.set("muted.time", -1);

            config.set("bans", 0);

            Main.home.clear();
            Main.home.add("Home");

            config.set("home.home.x", -62.5);
            config.set("home.home.y", 85);
            config.set("home.home.z", -108.5);
            config.set("home.home.world", "world");
            config.set("home.home.pitch", 0);
            config.set("home.home.yaw", 0);

            config.set("tpaincoming", "none");
            config.set("tparequest", "none");

            config.set("balance", 100);


            config.set("quest.TIMBER.started", false);

            taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    config.set("homelist", Main.home);
                    saveFile();
                }
            }, 20L);
            saveFile();
        }
    }

    public boolean isNew() {
        return creating;
    }

    public Player getPlayer() {
        return player;
    }

    public String getUUID() {
        return uuid;
    }

    public FileConfiguration getFile() {
        return config;
    }

    public void saveFile() {
        try {
            config.save(file);
        } catch (Exception e) {
            Main.plugin.getLogger().severe(ChatColor.RED + "Could not save " + file.getName() + "!");
        }
    }

    public void reloadFile() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void mute(String reason, Date unmute) {
        config.set("muted.status", true);
        config.set("muted.reason", reason);

        if (unmute == null) {
            config.set("muted.time", -1);
        } else {
            config.set("muted.time", unmute.getTime());
        }

        saveFile();
    }

    public void unmute() {
        config.set("muted.status", false);
        config.set("muted.reason", "NOT_MUTED");
        config.set("muted.time", -1);
        saveFile();
    }

    public boolean isMuted() {
        return config.getBoolean("muted.status", false);
    }

    public String getMutedReason() {
        if (!isMuted()) {
            return "NOT_MUTED";
        }

        return config.getString("muted.reason", "NOT_MUTED");
    }

    public long getUnmuteTime() {
        if (!isMuted()) {
            return -1;
        }

        return config.getLong("muted.time", -1);
    }

    public void Hmute(String reason, Date Hunmute) {
        config.set("Hmuted.status", true);
        config.set("Hmuted.reason", reason);

        if (Hunmute == null) {
            config.set("Hmuted.time", -1);
        } else {
            config.set("Hmuted.time", Hunmute.getTime());
        }

        saveFile();
    }

    public void Hunmute() {
        config.set("Hmuted.status", false);
        config.set("Hmuted.reason", "NOT_MUTED");
        config.set("Hmuted.time", -1);
        saveFile();
    }

    public boolean HisMuted() {
        return config.getBoolean("Hmuted.status", false);
    }

    public String getHMutedReason() {
        if (!HisMuted()) {
            return "NOT_MUTED";
        }

        return config.getString("Hmuted.reason", "NOT_MUTED");
    }

    public long getHUnmuteTime() {
        if (!HisMuted()) {
            return -1;
        }

        return config.getLong("Hmuted.time", -1);
    }

    public int getPPS() {
        return config.getInt("PPS");
    }

    public void addPPS(int score) {
        config.set("PPS", getPPS() + score);
        saveFile();
    }

    public void removePPS(int score) {
        config.set("PPS", getPPS() - score);
        saveFile();
    }

    public void setPPS(int score) {
        config.set("PPS", score);
        saveFile();
    }
}
