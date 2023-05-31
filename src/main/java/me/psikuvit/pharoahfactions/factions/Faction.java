package me.psikuvit.pharoahfactions.factions;

import me.psikuvit.pharoahfactions.factions.utils.FactionRanks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Faction {

    private final HashMap<Player, FactionRanks> membersRank;
    private final String name;
    private final List<Player> members;
    private final Player owner;
    private final UUID uuid;
    private final List<String> description;

    public Faction(String name, List<Player> members, Player owner, UUID uuid, List<String> description) {
        this.name = name;
        this.members = members;
        this.owner = owner;
        this.uuid = uuid;
        this.description = description;
        membersRank = new HashMap<>();
        for (Player player : members) {
            if (player.equals(owner)) {
                membersRank.put(player, FactionRanks.OWNER);
            } else {
                membersRank.put(player, FactionRanks.MEMBER);
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<Player> getMembers() {
        return members;
    }

    public Player getOwner() {
        return owner;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<String> getDescription() {
        return description;
    }

    public HashMap<Player, FactionRanks> getMembersRank() {
        return membersRank;
    }

}
