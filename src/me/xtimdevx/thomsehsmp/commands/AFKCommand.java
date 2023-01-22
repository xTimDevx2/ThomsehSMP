package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.managers.AFKManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AFKCommand implements CommandExecutor {

    private final AFKManager afkManager;

    public AFKCommand(AFKManager afkManager) {
        this.afkManager = afkManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            if(afkManager.toggleAFKStatus(p)){

                p.setPlayerListName(p.getPlayerListName() + " §7§o(afk)");

                afkManager.announceToOthers(p, true);

            }else{
                p.setPlayerListName(p.getPlayerListName().replace(" §7§o(afk)", ""));

                afkManager.announceToOthers(p, false);
            }

        }

        return true;
    }
}
