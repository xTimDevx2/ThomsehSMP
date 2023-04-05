package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.PermsUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SocialspyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);

        if(!player.hasPermission("smp.command.socialspy")) {
            player.sendMessage(MessageUtils.NOPERM);
            return true;
        }

        if(user.getFile().get("socialspy") == null) {
            user.getFile().set("socialspy", true);
        }


        if(user.getFile().getBoolean("socialspy")) {
            user.getFile().set("socialspy", false);
            user.saveFile();

            player.sendMessage(MessageUtils.PREFIX + "You have turned off SocialSpy.");
        }else {
            user.getFile().set("socialspy", true);
            user.saveFile();

            player.sendMessage(MessageUtils.PREFIX + "You have turned on SocialSpy.");


        }
        return true;
    }
}
