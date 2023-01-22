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
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
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
                player.sendMessage("§8§m----------------------------------------------------");            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lHelp §lMenu");
                MessageUtils.sendCenteredMessage(player, "§7§o(Klik op de topic waar je meer over wilt weten.)");
                player.sendMessage(" ");

                ComponentBuilder builder1 = new ComponentBuilder("");
                builder1.append(MessageUtils.returnCenteredMessage("§8> §3§oTeleports §8<"));
                builder1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help teleports"));
                builder1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om de teleport help te lezen.")}));
                player.spigot().sendMessage(builder1.create());

                ComponentBuilder builder2 = new ComponentBuilder("");
                builder2.append(MessageUtils.returnCenteredMessage("§8> §3§oClaimen §8<"));
                builder2.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help claimen"));
                builder2.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om de claim help te lezen.")}));
                player.spigot().sendMessage(builder2.create());

                ComponentBuilder builder3 = new ComponentBuilder("");
                builder3.append(MessageUtils.returnCenteredMessage("§8> §3§oQuests §8<"));
                builder3.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help quests"));
                builder3.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om de quest help te lezen.")}));
                player.spigot().sendMessage(builder3.create());

                ComponentBuilder builder4 = new ComponentBuilder("");
                builder4.append(MessageUtils.returnCenteredMessage("§8> §3§oDonates §8<"));
                builder4.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help donates"));
                builder4.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om de donate help te lezen.")}));
                player.spigot().sendMessage(builder4.create());

                ComponentBuilder builder5 = new ComponentBuilder("");
                builder5.append(MessageUtils.returnCenteredMessage("§8> §3§oEconomy §8<"));
                builder5.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help economy"));
                builder5.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("§cKlik Hier om de economy help te lezen.")}));
                player.spigot().sendMessage(builder5.create());
                player.sendMessage("§8§m----------------------------------------------------");            }
            return true;
        }


        if(args[0].equalsIgnoreCase("teleports")) {
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lTeleport Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fThere are a lot of teleport commands, here are a few");
                player.sendMessage(" ");
                player.sendMessage("§8> §f/spawn §8- §fTeleport to spawn.");
                player.sendMessage("§8> §f/back §8- §fGo back to the previous location.");
                player.sendMessage("§8> §f/crates §8- §fTeleport to the crates.");
                player.sendMessage("§8> §f/Home §8- §fTeleport to the home you set with /sethome.");
                player.sendMessage("§8> §f/Randomtp §8- §fTeleport to a random location in the map. §7§o(Cooldown: 2 Minutes)");
                player.sendMessage("§8> §f/tpa §8- §fTeleport to another player.");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Every teleport command has a 5 second.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lTeleport Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fEr zijn vele teleport commands, dit is een kleine samenvatting.");
                player.sendMessage(" ");
                player.sendMessage("§8> §f/spawn §8- §fTeleport naar spawn.");
                player.sendMessage("§8> §f/back §8- §fGa terug naar je vorige locatie.");
                player.sendMessage("§8> §f/crates §8- §fTeleport naar de crates.");
                player.sendMessage("§8> §f/Home §8- §fTeleport naar een home die jij gezet hebt met /sethome.");
                player.sendMessage("§8> §f/Randomtp §8- §fTeleport naar een willekeurige locatie in de map. §7§o(Cooldown: 2 Minuten)");
                player.sendMessage("§8> §f/tpa §8- §fTeleport naar een andere speler.");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Elke teleport command heeft een 5 seconde cooldown voor je teleporteert.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("claimen")) {
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§e§lClaim Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fWe the §e§oGrief Prevention §fplugin to claim land.");
                player.sendMessage("§8> §fYou can use the §e§oGolden Shovel §fto claim.");
                player.sendMessage("§8> §fYou can check claims by using a §e§oStick§f.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fVideo tutorial: §e§nhttp://bit.ly/mcgpuser");
                player.sendMessage(" ");
                player.sendMessage("§8> §fMaking a new claim is very easy.");
                player.sendMessage("§8> §fYou have to set 2 claim corners with your claim tool,");
                player.sendMessage("§8> §fyou right mouse click it on the ground to create corners.");
                player.sendMessage("§8> §fThis will automaticly create a claim for you.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fWant to give someone permissions on your plot?");
                player.sendMessage("§8> §fJust use /trust <player>");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Need help? Use /helpop and someone will come and help.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§e§lClaim Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fClaimen doen wij via de §e§oGrief Prevention §fplugin.");
                player.sendMessage("§8> §fJe kan claimen met een §e§oGolden Shovel§f.");
                player.sendMessage("§8> §fClaims checken doe je met een §e§oStick§f.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fVideo tutorial: §e§nhttp://bit.ly/mcgpuser");
                player.sendMessage(" ");
                player.sendMessage("§8> §fEen claim maken is heel simpel je moet gewoon");
                player.sendMessage("§8> §f2 claim hoeken zetten met je golden shovel,");
                player.sendMessage("§8> §fdit doe je door er rechtermuisknop mee te doen op de grond.");
                player.sendMessage("§8> §fDit maakt dan automatisch een volle claim voor je.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fHeb je iemand die je permission wilt geven om op je claim te bouwen?");
                player.sendMessage("§8> §fGebruik dan /trust <speler>");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Als je hulp nodig hebt gebruik dan /helpop en iemand komt je zo snel mogelijk helpen.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("quests")) {
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lQuests Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fYou currently have 2 types of quests,");
                player.sendMessage("§8> §fyou have normal quests and questlines.");
                player.sendMessage("§8> §fNormal quests are the bottom row in §3§o/quest§f.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fQuests are still in §lBETA §fwe will add more later.");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Need help? Use /helpop and someone will come and help.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lQuests Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fJe hebt 2 verschillende quests,");
                player.sendMessage("§8> §feen normale quests en questlines.");
                player.sendMessage("§8> §fNormale quests zijn de onderste rij in §3§o/quest§f.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fQuests zijn nog in §lBETA §fWe voegen er later nog meer toe.");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Als je hulp nodig hebt gebruik dan /helpop en iemand komt je zo snel mogelijk helpen.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("donates")) {
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lDonates Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fYou can donate to the server by using §3§o/buy");
                player.sendMessage("§8> §for on our website §3§ostore.thomseh.live");
                player.sendMessage(" ");
                player.sendMessage("§8> §fAll donations go back into the server.");
                player.sendMessage("§8> §fWe greatly appreciate all donations!");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Need help? Use /helpop and someone will come and help.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lDonates Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fJe kan doneren via §3§o/buy");
                player.sendMessage("§8> §fof op onze website §3§ostore.thomseh.live");
                player.sendMessage(" ");
                player.sendMessage("§8> §fAlle donaties gaan terug in de server.");
                player.sendMessage("§8> §fWe appreciëren alle donaties enorm!");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Als je hulp nodig hebt gebruik dan /helpop en iemand komt je zo snel mogelijk helpen.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            return true;
        }

        if(args[0].equalsIgnoreCase("economy")) {
            if (user.getLanguage().equalsIgnoreCase("ENGLISH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lEconomy Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fOur server uses it's own economy system.");
                player.sendMessage("§8> §fUse §3§o/balance §fto look at how much thomcoin you have.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fYou can use this to buy items in spawn.");
                player.sendMessage("§8> §fYou get money by doing quests or out of crates.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fThe economy system will be expanded on later!");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Need help? Use /helpop and someone will come and help.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }
            if (user.getLanguage().equalsIgnoreCase("DUTCH")) {
                player.sendMessage("§8§m----------------------------------------------------");
                MessageUtils.sendCenteredMessage(player, "§3§lEconomy Help");
                player.sendMessage(" ");
                player.sendMessage("§8> §fDe server heeft zijn eigen economy systeem.");
                player.sendMessage("§8> §fGebruik §3§o/balance §fom te kijken hoeveel Thomcoin u heeft.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fJe kan met je geld items kopen in de shop op spawn.");
                player.sendMessage("§8> §fJe komt aan geld door quests te doen, en uit crates.");
                player.sendMessage(" ");
                player.sendMessage("§8> §fDe economy system moet later nog enorm uitgebreid worden!");
                player.sendMessage(" ");
                player.sendMessage("§7§o(Als je hulp nodig hebt gebruik dan /helpop en iemand komt je zo snel mogelijk helpen.)");
                player.sendMessage("§8§m----------------------------------------------------");
            }

            return true;
        }
        return true;
    }
}
