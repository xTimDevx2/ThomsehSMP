package me.xtimdevx.thomsehsmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static String NOPERM = "§cYou don't have permissions to use this.";
    public static String PREFIX = "§3§lSMP §8> §f";
    public static String GARY = "§c§lGary §8> §f";

    public static String returnPrefix(OfflinePlayer player){
        PermsUtils permsUtils = PermsUtils.getInstance();
        if(permsUtils.isInGroup(player, "owner")) {
            return "§8(§4§lOwner§8) §f";
        }
        if(permsUtils.isInGroup(player, "developer")) {
            return "§8(§c§lDeveloper§8) §f";
        }
        if(permsUtils.isInGroup(player, "mod")) {
            return "§8(§2§lMod§8) §f";
        }
        if(permsUtils.isInGroup(player, "twitch")) {
            return "§8(§5§lTwitch§8) §f";
        }
        if(permsUtils.isInGroup(player, "gold")) {
            return "§8(§6§lGold§8) §f";
        }
        if(permsUtils.isInGroup(player, "diamond")) {
            return "§8(§b§lDiamond§8) §f";
        }
        if(permsUtils.isInGroup(player, "builder")) {
            return "§8(§3§lBuilder§8) §f";
        }
        if(permsUtils.isInGroup(player, "emerald")) {
            return "§8(§a§lEmerald§8) §f";
        }
        return "§8(§7§lPlayer§8) §f";
     }

    private final static int CENTER_PX = 154;

    public static void sendCenteredMessage(Player player, String message){
        if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    public static void broadcastCenteredMessage(String message){
        if(message == null || message.equals("")) Bukkit.broadcastMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode == true){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        Bukkit.broadcastMessage(sb.toString() + message);
    }
}
