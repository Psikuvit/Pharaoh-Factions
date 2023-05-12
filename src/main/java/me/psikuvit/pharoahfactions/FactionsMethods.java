package me.psikuvit.pharoahfactions;

import me.psikuvit.pharoahfactions.data.player.PlayerDataFiles;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface FactionsMethods {

    List<Faction> factions = new ArrayList<>();

    /**
     * This method is used to get all cached Factions
     * @return all cached Factions
     */
    default List<Faction> getFactions() {
        return factions;
    }

    /**
     * This method is used to cache the Faction
     * @param faction faction to cache
     */
    default void addFaction(Faction faction) {
        factions.add(faction);
    }

    /**
     * This method is used to delete the Faction
     * @param faction faction to delete
     */
    default void removeFaction(Faction faction) {
        factions.remove(faction);
    }

    /**
     * This method is used to add a Player to the Faction
     * @param faction faction to add player from it
     * @param player player to add
     */
    default void addPlayerToFaction(Faction faction, Player player) {
        faction.getMembers().add(player);
        PlayerDataInterface playerDataFiles = Pharaoh_Factions.getInstance().getPlayerData();
        playerDataFiles.setPlayerFaction(player, faction);
    }

    /**
     * This method is used to remove a Player from the Faction
     * @param faction faction to remove player from it
     * @param player player to remove
     */
    default void removePlayerFromFaction(Faction faction, Player player) {
        faction.getMembers().remove(player);
        PlayerDataInterface playerDataFiles = Pharaoh_Factions.getInstance().getPlayerData();
        playerDataFiles.removePlayerFaction(player, faction);
    }


    /**
     * This method is used to get the Faction from its uuid
     * @param uuid uuid of the faction
     * @return the faction
     */
    default Faction getFactionByUUID(UUID uuid) {
        for (Faction faction : factions) {
            if (faction.getUuid().equals(uuid))
                return faction;
        }
        return null;
    }

    /**
     * This method is used to get the Faction from its owner
     * @param owner owner of the faction
     * @return the faction
     */
    default Faction getFactionByOwner(Player owner) {
        for (Faction faction : factions) {
            if (faction.getOwner().equals(owner))
                return faction;
        }
        return null;
    }

}
