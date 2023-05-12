package me.psikuvit.pharoahfactions.data.player;

import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.utils.FactionRanks;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataFiles implements PlayerDataInterface {
    @Override
    public void createPlayer(Player player) {
        File dataFolder = new File(plugin.getDataFolder(), "Players-Data/");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
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

    @Override
    public Faction getPlayerFaction(Player player) {
        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        UUID uuid = UUID.fromString(yaml.getString("Faction"));

        return getFactionByUUID(uuid);
    }

    @Override
    public void setPlayerFaction(Player player, Faction faction) {
        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        yaml.set("Faction", String.valueOf(faction.getUuid()));
        yaml.set("Rank-In_Faction", faction.getMembersRank().get(player).toString());

        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void removePlayerFaction(Player player, Faction faction) {

        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        yaml.set("Faction", null);
        yaml.set("Rank-In_Faction", null);

        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public FactionRanks getPlayerRank(Player player, Faction faction) {
        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        if (!faction.getMembers().contains(player))
            return null;

        return FactionRanks.valueOf(yaml.getString("Rank-In_Faction"));

    }

    @Override
    public void setRankInFaction(Player player, Faction faction, FactionRanks rank) {
        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        if (!faction.getMembers().contains(player))
            return;

        yaml.set("Rank-In_Faction", rank);
    }

    @Override
    public boolean isInFaction(Player player) {
        return getPlayerFaction(player) != null;
    }

}
