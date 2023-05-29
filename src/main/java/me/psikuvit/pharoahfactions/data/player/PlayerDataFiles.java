package me.psikuvit.pharoahfactions.data.player;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.factions.utils.FactionRanks;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataFiles implements PlayerDataInterface {

    protected Pharaoh_Factions plugin;
    public PlayerDataFiles(final Pharaoh_Factions plugin) {
        this.plugin = plugin;
    }
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


        String s = yaml.getString("Faction");
        if (s == null) {
            Messages.log("Couldn't find get player faction ID");
            return null;
        }

        UUID uuid = UUID.fromString(s);

        return FACTION_METHODS.getFactionByUUID(uuid);
    }

    @Override
    public void setPlayerFaction(Player player, Faction faction) {
        File file = new File(plugin.getDataFolder(), "Players-Data/" + player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        yaml.set("Faction", String.valueOf(faction.getUuid()));
        yaml.set("Rank-In_Faction", FactionRanks.MEMBER);

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
        return getPlayerFaction(player) == null;
    }

}
