package me.psikuvit.pharoahfactions.data.factions;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.factions.utils.FactionRanks;
import me.psikuvit.pharoahfactions.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FactionsDataFiles implements FactionsDataInterface {

    private final Pharaoh_Factions plugin;
    public FactionsDataFiles(Pharaoh_Factions plugin) {
        this.plugin = plugin;
        loadFactionData();
    }

    @Override
    public void createFactionData(Faction faction) {
        File dataFolder = new File(plugin.getDataFolder(), "Factions/");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        File file = new File(dataFolder, faction.getUuid() + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveFactionData(Faction faction) {
        createFactionData(faction);
        File file = new File(plugin.getDataFolder(), "Factions/" + faction.getUuid() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        String owner = faction.getOwner().getName();
        String uuid = String.valueOf(faction.getUuid());
        List<String> members = faction.getMembers().stream().map(Player::getName).collect(Collectors.toList());
        String name = faction.getName();
        List<String> description = faction.getDescription();

        yaml.set("Faction-Name", name);
        yaml.set("Faction-Owner", owner);
        yaml.set("Faction-UUID", uuid);
        yaml.set("Faction-Description", description);
        yaml.set("Faction-Members", members);
        yaml.set("Faction-Ranks", faction.getMembersRank());
        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void loadFactionData() {
        String folderPath = plugin.getDataFolder().getPath() + "/Factions";

        // get a stream of all files in the folder
        try (Stream<Path> fileStream = Utils.getFilesInFolder(folderPath)) {
            // iterate through the stream and print the file names
            fileStream.forEach(file -> {
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file.toFile());

                String name = yaml.getString("Faction-Name");
                Player owner = Bukkit.getPlayer(yaml.getString("Faction-Owner"));
                UUID uuid = UUID.fromString(yaml.getString("Faction-UUID"));
                List<String> description = yaml.getStringList("Faction-Description");
                List<String> members = yaml.getStringList("Faction-Members");

                Faction faction = new Faction(name, members.stream().map(Bukkit::getPlayer).collect(Collectors.toList()), owner, uuid, description);
                FactionMethods.addFaction(faction);
            });

        } catch (Exception e) {
            // handle any exceptions that occur
            e.printStackTrace();
        }

    }
}
