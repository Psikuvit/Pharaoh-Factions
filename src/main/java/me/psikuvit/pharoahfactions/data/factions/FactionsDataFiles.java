package me.psikuvit.pharoahfactions.data.factions;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.factions.utils.FactionRanks;
import me.psikuvit.pharoahfactions.utils.Messages;
import me.psikuvit.pharoahfactions.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public void saveFactionData() {
        for (Faction faction : FactionMethods.getFactions()) {
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
            List<String> ranks = new ArrayList<>();
            for (Player player : faction.getMembersRank().keySet()) {
                String s = player.getName() + ":" + faction.getMembersRank().get(player);
                ranks.add(s);
            }
            yaml.set("Faction-Ranks", ranks);
            try {
                yaml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void loadFactionData() {
        String folderPath = plugin.getDataFolder().getPath() + "/Factions";
        Path folder = Paths.get(folderPath);
        if (!folder.toFile().exists()) {
            return;
        }

        // get a stream of all files in the folder
        try (Stream<Path> fileStream = Utils.getFilesInFolder(folderPath)) {
            // iterate through the stream and print the file names
            fileStream.forEach(file -> {
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file.toFile());
                if (file.toFile().length() == 0) {
                    Messages.log("File is empty");
                    return;
                }

                String name = yaml.getString("Faction-Name");
                Player owner = Bukkit.getPlayer(yaml.getString("Faction-Owner"));
                UUID uuid = UUID.fromString(yaml.getString("Faction-UUID"));
                List<String> description = yaml.getStringList("Faction-Description");
                List<Player> members = yaml.getStringList("Faction-Members").stream().map(Bukkit::getPlayer).collect(Collectors.toList());

                List<String> ranks = yaml.getStringList("Faction-Ranks");

                Faction faction = new Faction(name, members, owner, uuid, description);
                for (String rank : ranks) {
                    String[] split = rank.split(":");
                    faction.getMembersRank().put(Bukkit.getPlayer(split[0]), FactionRanks.valueOf(split[1]));
                }
                FactionMethods.addFaction(faction);
            });

        } catch (Exception e) {
            // handle any exceptions that occur
            e.printStackTrace();
        }

    }
}
