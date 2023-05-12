package me.psikuvit.pharoahfactions.data.factions;

import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.FactionsMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;

public interface FactionsDataInterface extends FactionsMethods {

    Pharaoh_Factions plugin = Pharaoh_Factions.getInstance();

    void createFactionData(Faction faction);
    void saveFactionData(Faction faction);
    void loadFactionData();
}
