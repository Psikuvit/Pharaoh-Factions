package me.psikuvit.pharoahfactions;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FactionsMethods {

    static List<Faction> factions = new ArrayList<>();

    /**
     * This method is used to get all cached Factions
     * @return all cached Factions
     */
    public static List<Faction> getFactions() {
        return factions;
    }

    /**
     * This method is used to cache the Faction
     * @param faction faction to cache
     */
    public static void addFaction(Faction faction) {
        factions.add(faction);
    }

    /**
     * This method is used to delete the Faction
     * @param faction faction to delete
     */
    public static void removeFaction(Faction faction) {
        factions.remove(faction);
    }

    /**
     * This method is used to add a Player to the Faction
     * @param faction faction to add player from it
     * @param player player to add
     */
    public static void addPlayerToFaction(Faction faction, Player player) {
        faction.getMembers().add(player);
    }

    /**
     * This method is used to remove a Player from the Faction
     * @param faction faction to remove player from it
     * @param player player to remove
     */
    public static void removePlayerFromFaction(Faction faction, Player player) {
        faction.getMembers().remove(player);
    }

    /**
     * This method is used to get the Faction from its uuid
     * @param uuid uuid of the faction
     * @return the faction
     */
    public static Faction getFactionByUUID(UUID uuid) {
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
    public static Faction getFactionByOwner(Player owner) {
        for (Faction faction : factions) {
            if (faction.getOwner().equals(owner))
                return faction;
        }
        return null;
    }

}
