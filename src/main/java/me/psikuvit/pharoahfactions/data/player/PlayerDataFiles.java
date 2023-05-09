package me.psikuvit.pharoahfactions.data.player;

import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.FactionsMethods;
import me.psikuvit.pharoahfactions.data.factions.FactionsDataInterface;
import me.psikuvit.pharoahfactions.utils.FactionRanks;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataFiles implements PlayerDataInterface {
    @Override
    public void createPlayer(Player player) {
        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        if (!file.exists()) {
            file.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Faction getPlayerFaction(Player player) {
        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        UUID uuid = UUID.fromString(yaml.getString("Faction"));

        return FactionsMethods.getFactionByUUID(uuid);
    }

    @Override
    public void setPlayerFaction(Player player, Faction faction) {
        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        yaml.set("Faction", faction.getUuid());
        yaml.set("Rank-In_Faction", faction.getMembersRank().get(player));

        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public FactionRanks getRankInFaction(Player player, Faction faction) {
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

}
