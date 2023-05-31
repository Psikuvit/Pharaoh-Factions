package me.psikuvit.pharoahfactions.data.factions;

import me.psikuvit.pharoahfactions.factions.Faction;

public interface FactionsDataInterface {
    void createFactionData(Faction faction);
    void saveFactionData(Faction faction);
    void loadFactionData();
}
