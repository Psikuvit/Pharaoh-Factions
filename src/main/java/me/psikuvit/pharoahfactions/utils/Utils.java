package me.psikuvit.pharoahfactions.utils;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Utils {

    private static final Pharaoh_Factions plugin = Pharaoh_Factions.getPlugin(Pharaoh_Factions.class);

    public static ItemStack getPlayerSkull(Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        skull.setItemMeta(meta);
        return skull;
    }

    public static Stream<Path> getFilesInFolder(String folderPath) {
        // create a Path object for the folder
        Path folder = Paths.get(folderPath);
        try {
            return Files.list(folder);
        } catch (IOException e) {
            Messages.log("Couldn't find directory");
            throw new RuntimeException(e);
        }
    }
}
