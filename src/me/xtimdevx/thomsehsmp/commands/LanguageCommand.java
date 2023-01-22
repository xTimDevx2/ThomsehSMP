package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LanguageCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        User user = User.get(player);

        if (user.getFile().get("language") == null) {
            user.getFile().set("language", "ENGLISH");
            user.saveFile();
        }

        if (args.length == 0) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, user.getLanguage().equalsIgnoreCase("ENGLISH") ? "§3§lLanguage" : "§3§lTaal");
            player.sendMessage(" ");
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                MessageUtils.sendCenteredMessage(player, "Click on the language that you want to change to!");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                MessageUtils.sendCenteredMessage(player, "Klik op de taal waar je naar wilt veranderen!");
            }

            ComponentBuilder builder1 = new ComponentBuilder("");
            builder1.append(MessageUtils.returnCenteredMessage("§8> " + (user.getLanguage().equalsIgnoreCase("DUTCH") ? "§a" : "§c") + "§oNederlands §8<"));
            builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/language nederlands"));
            builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om je taal naar nederlands te veranderen.")}));
            player.spigot().sendMessage(builder1.create());

            ComponentBuilder builder2 = new ComponentBuilder("");
            builder2.append(MessageUtils.returnCenteredMessage("§8> " + (user.getLanguage().equalsIgnoreCase("ENGLISH") ? "§a" : "§c") + "§oEnglish §8<"));
            builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/language english"));
            builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick here to change your language to english.")}));
            player.spigot().sendMessage(builder2.create());
            player.sendMessage("§8§m----------------------------------------------------");

        }

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("nederlands")) {
                user.setLanguage(User.Languages.DUTCH);
                player.sendMessage("§8> §fJe hebt je taal naar §3§oNederlands §fgezet!");
            }
            if(args[0].equalsIgnoreCase("english")) {
                user.setLanguage(User.Languages.ENGLISH);
                player.sendMessage("§8> §fYou changed your language to §3§oEnglish§f!");
            }
        }
        return true;
    }
}
