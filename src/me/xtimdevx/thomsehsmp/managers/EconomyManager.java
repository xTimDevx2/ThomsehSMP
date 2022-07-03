package me.xtimdevx.thomsehsmp.managers;

import me.xtimdevx.thomsehsmp.User;
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
    }

    public void removeBalance(Player player, int amount) {
        User user = User.get(player);

        user.getFile().set("balance", user.getFile().getInt("balance") - amount);
        user.saveFile();
    }

    public void setBalance(Player player, int amount) {
        User user = User.get(player);

        user.getFile().set("balance", amount);
        user.saveFile();
    }
}
