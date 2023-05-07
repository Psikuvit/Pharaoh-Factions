package me.psikuvit.pharoahfactions;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FactionsMethods {

    static List<Faction> factions = new ArrayList<>();

    public static List<Faction> getFactions() {
        return factions;
    }

    public static void addFaction(Faction faction) {
        factions.add(faction);
    }
    public static void removeFaction(Faction faction) {
        factions.remove(faction);
    }
    public static Faction getFactionByUUID(UUID uuid) {
        for (Faction faction : factions) {
            if (faction.getUuid().equals(uuid))
                return faction;
        }
        return null;
    }
    public static Faction getFactionByOwner(Player owner) {
        for (Faction faction : factions) {
            if (faction.getOwner().equals(owner))
                return faction;
        }
        return null;
    }
}
