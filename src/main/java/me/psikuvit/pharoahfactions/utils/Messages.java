package me.psikuvit.pharoahfactions.utils;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Messages {


    public static String INCORRECT_COMMAND = color("§cUnknown command");
    public static String MUST_BE_PLAYER = color("§cYou need to be a player");

    public static void sendMessage(Player receiver, String msg) {
        receiver.sendMessage(color(msg));
    }

    public static List<String> color(List<String> msg) {
        return msg.stream().map(Messages::color).collect(Collectors.toList());
    }

    public static String color(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String convertedCode = hexCode.replaceAll("#", "x").replaceAll("(?<=x)(.)", "&$1");
            message = message.replace(hexCode, convertedCode);
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
