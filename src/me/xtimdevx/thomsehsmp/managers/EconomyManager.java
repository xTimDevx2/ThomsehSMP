package me.xtimdevx.thomsehsmp.managers;

import me.xtimdevx.thomsehsmp.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class EconomyManager {

    public int getBalance(Player player) {
        User user = User.get(player);

        return user.getFile().getInt("balance");
    }

    public void addBalance(Player player, int amount) {
        User user = User.get(player);

        user.getFile().set("balance", user.getFile().getInt("balance") + amount);
        user.saveFile();

        ScoreboardManager.updateScoreBoard(player);
    }

    public void removeBalance(Player player, int amount) {
        User user = User.get(player);

        user.getFile().set("balance", user.getFile().getInt("balance") - amount);
        user.saveFile();

        ScoreboardManager.updateScoreBoard(player);
    }

    public void setBalance(Player player, int amount) {
        User user = User.get(player);

        user.getFile().set("balance", amount);
        user.saveFile();

        ScoreboardManager.updateScoreBoard(player);
    }

    public boolean hasEnoughBalance(Player player, int amount) {
        User user = User.get(player);

        if(user.getFile().getInt("balance") >= amount) {
            return true;
        }
        return false;
    }

    public int getBalance(OfflinePlayer player) {
        User user = User.get(player);

        return user.getFile().getInt("balance");
    }

    public void addBalance(OfflinePlayer player, int amount) {
        User user = User.get(player);

        user.getFile().set("balance", user.getFile().getInt("balance") + amount);
        user.saveFile();

    }

    public void removeBalance(OfflinePlayer player, int amount) {
        User user = User.get(player);

        user.getFile().set("balance", user.getFile().getInt("balance") - amount);
        user.saveFile();
    }

    public void setBalance(OfflinePlayer player, int amount) {
        User user = User.get(player);

        user.getFile().set("balance", amount);
        user.saveFile();
    }

    public boolean hasEnoughBalance(OfflinePlayer player, int amount) {
        User user = User.get(player);

        if(user.getFile().getInt("balance") >= amount) {
            return true;
        }
        return false;
    }
}
