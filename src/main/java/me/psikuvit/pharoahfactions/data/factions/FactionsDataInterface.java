package me.psikuvit.pharoahfactions.data.factions;

import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import org.bukkit.entity.Player;

import java.util.List;

public interface FactionsDataInterface {

    Pharaoh_Factions plugin = Pharaoh_Factions.getInstance();

    void createFactionData(Faction faction);
    void saveFactionData(Faction faction);
    void loadFactionData();
}
