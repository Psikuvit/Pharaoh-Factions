package me.psikuvit.pharoahfactions.data.player;

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
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerDataFiles implements PlayerDataInterface {

    private final Pharaoh_Factions plugin;
    private final HashMap<Player, Faction> playerFaction = new HashMap<>();
    public PlayerDataFiles(final Pharaoh_Factions plugin) {
        this.plugin = plugin;
        loadFactionData();
    }

    @Override
    public Faction getPlayerFaction(Player player) {
        return playerFaction.get(player);
    }

    @Override
    public void setPlayerFaction(Player player, Faction faction) {
       playerFaction.put(player, faction);

    }
    @Override
    public void removePlayerFaction(Player player) {
        playerFaction.remove(player);

    }

    @Override
    public FactionRanks getPlayerRank(Player player) {
        Faction faction = getPlayerFaction(player);

        return faction.getMembersRank().get(player);

    }

    @Override
    public void setRankInFaction(Player player, FactionRanks rank) {
        Faction faction = getPlayerFaction(player);
        faction.getMembersRank().put(player, rank);
    }

    @Override
    public boolean isInFaction(Player player) {
        return getPlayerFaction(player) == null;
    }

    @Override
    public void savePlayerData() {
        File dataFolder = new File(plugin.getDataFolder(), "Players-Data/");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        for (Player player : playerFaction.keySet()) {
            File file = new File(dataFolder, player.getUniqueId() + ".yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

            yaml.set("Faction", null);
            yaml.set("Rank-In_Faction", null);

            try {
                yaml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void loadFactionData() {
        String folderPath = plugin.getDataFolder().getPath() + "/Players-Data";

        // get a stream of all files in the folder
        try (Stream<Path> fileStream = Utils.getFilesInFolder(folderPath)) {
            // iterate through the stream and print the file names
            for (Path file : fileStream.collect(Collectors.toList())) {
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file.toFile());
                String uuid = yaml.getString("Faction");
                if (uuid == null) {
                    Messages.log("Skipped player with uuid: " + file + ", Because he doesn't have a faction");
                    continue;
                }

                Faction faction = FactionMethods.getFactionByUUID(UUID.fromString(uuid));
                Player player = Bukkit.getPlayer(UUID.fromString(file.toString()));
                playerFaction.put(player, faction);
            };

        } catch (Exception e) {
            // handle any exceptions that occur
            e.printStackTrace();
        }
    }
}
