package me.xtimdevx.thomsehsmp.features;

import me.xtimdevx.thomsehsmp.Main;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bossbar {

    private int taskID = -1;
    private final Main plugin;
    private org.bukkit.boss.BossBar bar;

    public Bossbar(Main plugin) {
        this.plugin = plugin;
    }

    public void addPlayer(Player player) {
        bar.addPlayer(player);
    }

    public BossBar getBar() {
        return bar;
    }

    public void createBar() {
        bar = Bukkit.createBossBar(MessageUtils.format("§8> §fWelcome to #01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP§f! §8<"), BarColor.BLUE, BarStyle.SOLID);        bar.setVisible(true);
        cast();
    }

    public void cast() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            int count = -1;
            double progress = 1.0;
            double time = 1.0 / (20);

            @Override
            public void run() {
                bar.setProgress(progress);
                switch (count) {
                    case -1:
                        break;
                    case 0:
                        bar.setColor(BarColor.PURPLE);
                        bar.setTitle("§8> §fFollow §5Twitch.tv/thomseh §ffor server updates §8<");
                        break;
                    case 1:
                        bar.setColor(BarColor.RED);
                        bar.setTitle("§8> §fThere are currently §c§o" + Bukkit.getOnlinePlayers().size() + " §fplayers online. §8<");
                        break;
                    case 2:
                        bar.setColor(BarColor.WHITE);
                        bar.setTitle("§8> §fServer produced by §b§oScyle Productions§f!§8 <");
                        break;
                    case 3:
                    default:
                        bar.setColor(BarColor.BLUE);
                        bar.setTitle(MessageUtils.format("§8> §fWelcome to #01C5CB§lO#00B0B6§lr#009EA3§li#008A8E§lg#00767A§la#006A6D§lm#006366§li §f§lSMP§f! §8<"));
                        count = -1;
                        break;
                }

                progress = progress - time;
                if(progress <= 0) {
                    count++;
                    progress = 1.0;
                }

            }
        }, 0, 20);
    }
}
