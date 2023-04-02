package me.xtimdevx.thomsehsmp.features.jobs;

import me.xtimdevx.thomsehsmp.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobsCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("jobs")) {
            Player player = (Player) sender;
            User user = User.get(player);

            if(user.getJob() == null) {
                user.setJob(User.Jobs.MINER);
                player.sendMessage("Job geselecteerd: Miner");
            }
        }
        return true;
    }
}
