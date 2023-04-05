package me.xtimdevx.thomsehsmp.commands;

import me.xtimdevx.thomsehsmp.User;
import me.xtimdevx.thomsehsmp.utils.MessageUtils;
import me.xtimdevx.thomsehsmp.utils.Utils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        User user = User.get(player);
        if (args.length == 0) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lHelp §lMenu");
                MessageUtils.sendCenteredMessage(player, "§7§o(Click on the topic you want to know more about.)");
                player.sendMessage(" ");

                ComponentBuilder builder1 = new ComponentBuilder("");
                builder1.append(MessageUtils.returnCenteredMessage("§8> §3§oTeleports §8<"));
                builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help teleports"));
                builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick here to read about teleporting.")}));
                player.spigot().sendMessage(builder1.create());

                ComponentBuilder builder2 = new ComponentBuilder("");
                builder2.append(MessageUtils.returnCenteredMessage("§8> §3§oClaimen §8<"));
                builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help claimen"));
                builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick here to read about claiming.")}));
                player.spigot().sendMessage(builder2.create());

                ComponentBuilder builder3 = new ComponentBuilder("");
                builder3.append(MessageUtils.returnCenteredMessage("§8> §3§oQuests §8<"));
                builder3.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help quests"));
                builder3.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick here to read about quests.")}));
                player.spigot().sendMessage(builder3.create());

                ComponentBuilder builder4 = new ComponentBuilder("");
                builder4.append(MessageUtils.returnCenteredMessage("§8> §3§oDonates §8<"));
                builder4.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help donates"));
                builder4.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§c§cClick here to read about donating.")}));
                player.spigot().sendMessage(builder4.create());

                ComponentBuilder builder5 = new ComponentBuilder("");
                builder5.append(MessageUtils.returnCenteredMessage("§8> §3§oEconomy §8<"));
                builder5.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help economy"));
                builder5.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cClick here to read about economy.")}));
                player.spigot().sendMessage(builder5.create());
                player.sendMessage("§8§m----------------------------------------------------");
            return true;
        }


        if(args[0].equalsIgnoreCase("teleports")) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lTeleport Help");
            player.sendMessage(" ");
            player.sendMessage("§8> §fThere are many teleport commands, this is a brief summary.");
            player.sendMessage(" ");
            player.sendMessage("§8> §f/spawn §8- §fTeleport to spawn.");
            player.sendMessage("§8> §f/back §8- §fGo back to your previous location.");
            player.sendMessage("§8> §f/crates §8- §fTeleport to the crates.");
            player.sendMessage("§8> §f/Home §8- §fTeleport to a home you have set with /sethome.");
            player.sendMessage("§8> §f/Randomtp §8- §fTeleport to a random location in the map. §7§o(Cooldown: 2 Minutes)");
            player.sendMessage("§8> §f/tpa §8- §fTeleport to another player.");
            player.sendMessage(" ");
            player.sendMessage("§7§o(Each teleport command has a 5-second cooldown before you teleport.)");
            player.sendMessage("§8§m----------------------------------------------------");

            return true;
        }
        if(args[0].equalsIgnoreCase("claimen")) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§e§lClaim Help");
            player.sendMessage(" ");
            player.sendMessage("§8> §fWe use the §e§oGrief Prevention §fplugin for claiming.");
            player.sendMessage("§8> §fYou can claim with a §e§oGolden Shovel§f.");
            player.sendMessage("§8> §fTo check claims, use a §e§oStick§f.");
            player.sendMessage(" ");
            player.sendMessage("§8> §fVideo tutorial: §e§nhttp://bit.ly/mcgpuser");
            player.sendMessage(" ");
            player.sendMessage("§8> §fMaking a claim is very simple, you just need to");
            player.sendMessage("§8> §fset 2 claim corners with your golden shovel,");
            player.sendMessage("§8> §fdo this by right-clicking with it on the ground.");
            player.sendMessage("§8> §fThis will automatically create a full claim for you.");
            player.sendMessage(" ");
            player.sendMessage("§8> §fDo you want to give someone permission to build on your claim?");
            player.sendMessage("§8> §fUse /trust <player>");
            player.sendMessage(" ");
            player.sendMessage("§7§o(If you need help, use /helpop and someone will come to help you as soon as possible.)");
            player.sendMessage("§8§m----------------------------------------------------");

            return true;
        }
        if(args[0].equalsIgnoreCase("donates")) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lHelp met Donaties");
            player.sendMessage(" ");
            player.sendMessage("§8> §fYou can donate via §3§o/buy");
            player.sendMessage("§8> §fOr on our website §3§ostore.thomseh.live");
            player.sendMessage(" ");
            player.sendMessage("§8> §fAll donations go back into the server.");
            player.sendMessage("§8> §fWe greatly appreciate all donations!");
            player.sendMessage(" ");
            player.sendMessage("§7§o(If you need help, use /helpop and someone will come help you as soon as possible.)");
            player.sendMessage("§8§m----------------------------------------------------");
            return true;
        }

        if(args[0].equalsIgnoreCase("economy")) {
            player.sendMessage("§8§m----------------------------------------------------");
            MessageUtils.sendCenteredMessage(player, "§3§lEconomy Help");
            player.sendMessage(" ");
            player.sendMessage("§8> §fThe server has its own economy system.");
            player.sendMessage("§8> §fUse §3§o/balance §fto check how many Thomcoins you have.");
            player.sendMessage(" ");
            player.sendMessage("§8> §fYou can buy items in the shop at spawn with your money.");
            player.sendMessage("§8> §fYou can earn money by doing quests and from crates.");
            player.sendMessage(" ");
            player.sendMessage("§8> §fThe economy system will be expanded greatly later!");
            player.sendMessage(" ");
            player.sendMessage("§7§o(If you need help, use /helpop and someone will come to help you as soon as possible.)");
            player.sendMessage("§8§m----------------------------------------------------");


            return true;
        }
        return true;
    }
}
