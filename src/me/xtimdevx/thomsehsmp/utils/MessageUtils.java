package me.xtimdevx.thomsehsmp.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {

    public static final Pattern HEX_PATTERN = Pattern.compile("(#[A-Fa-f0-9]{6})");

    public static String format(String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);
        while (matcher.find())
            string = string.replace(matcher.group(), "" + ChatColor.of(matcher.group()));
        return string;
    }

    public static String NOPERM = "§c§lERROR §cNo Perm.";
    public static String PREFIX = "§3§lSMP §8> §f";
    public static String GARY = "§c§lGary §8> §f";

    public static String returnPrefix(OfflinePlayer player){
        PermsUtils permsUtils = PermsUtils.getInstance();
        if(permsUtils.isInGroup(player, "owner")) {
            return "§8(" + format("#FF5F5F§lO#FE4848§lw#FF3030§ln#FF1D1D§le#FF0000§lr") + "§8) §f";
        }
        if(permsUtils.isInGroup(player, "developer")) {
            return "§8(" + format("#FF9E93§lD#FE9285§le#FF7E6F§lv#FF6451§le#FF5540§ll#FF3E27§lo#FF2B12§lp#EB1900§le#D01600§lr") + "§8) §f";
        }
        if(permsUtils.isInGroup(player, "mod")) {
            return "§8(§2§lMod§8) §f";
        }
        if(permsUtils.isInGroup(player, "twitch")) {
            return "§8(§5§lTwitch§8) §f";
        }
        if(permsUtils.isInGroup(player, "gold")) {
            return "§8(" + format("#FFD786§lG#FFCB62§lo#FCBB39§ll#FFAC06§ld") + "§8) §f";
        }
        if(permsUtils.isInGroup(player, "diamond")) {
            return "§8(§b§lDiamond§8) §f";
        }
        if(permsUtils.isInGroup(player, "builder")) {
            return "§8(" + format("#00DC99§lB#00D091§lu#00BF85§li#00AE79§ll#019B6C§ld#008A60§le#007F58§lr") + "§8) §f";
        }
        if(permsUtils.isInGroup(player, "emerald")) {
            return "§8(§a§lEmerald§8) §f";

        }
        return "§8(§7§lPlayer§8) §f";
     }


    public static String returnTabPrefix(OfflinePlayer player){
        PermsUtils permsUtils = PermsUtils.getInstance();
        if(permsUtils.isInGroup(player, "owner")) {
            return format("#FF5F5F§lO#FE4848§lw#FF3030§ln#FF1D1D§le#FF0000§lr") + "§f ";
        }
        if(permsUtils.isInGroup(player, "developer")) {
            return "" + format("#FF9E93§lD#FE9285§le#FF7E6F§lv") + "§f ";
        }
        if(permsUtils.isInGroup(player, "mod")) {
            return "§2§lMod§f ";
        }
        if(permsUtils.isInGroup(player, "twitch")) {
            return "§5§lTwitch§f ";
        }
        if(permsUtils.isInGroup(player, "gold")) {
            return "" + format("#FFD786§lG#FFCB62§lo#FCBB39§ll#FFAC06§ld") + "§f ";
        }
        if(permsUtils.isInGroup(player, "diamond")) {
            return "§b§lDiamond§f ";
        }
        if(permsUtils.isInGroup(player, "builder")) {
            return "" + format("#00DC99§lB#00D091§lu#00BF85§li#00AE79§ll#019B6C§ld#008A60§le#007F58§lr") + "§f ";
        }
        if(permsUtils.isInGroup(player, "emerald")) {
            return "§a§lEmerald§f ";
        }
        return "§7";
    }


    private final static int CENTER_PX = 154;

    public static void sendCenteredMessage(Player player, String message){
        if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('§', message);

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

    public static String returnCenteredMessage(String message){
        if(message == null || message.equals(""));
        message = ChatColor.translateAlternateColorCodes('§', message);

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
        return sb.toString() + message;
    }


    public static void broadcastCenteredMessage(String message){
        if(message == null || message.equals("")) Bukkit.broadcastMessage("");
        message = ChatColor.translateAlternateColorCodes('§', message);

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
