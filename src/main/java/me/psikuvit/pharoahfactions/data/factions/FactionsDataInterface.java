package me.psikuvit.pharoahfactions.data.factions;

import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;

public interface FactionsDataInterface {
    FactionMethods FACTION_METHODS = new FactionMethods();
    void createFactionData(Faction faction);
    void saveFactionData(Faction faction);
    void loadFactionData();
}
