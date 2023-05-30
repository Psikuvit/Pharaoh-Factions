package me.psikuvit.pharoahfactions.data.player;

import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.factions.FactionInvite;
import me.psikuvit.pharoahfactions.factions.utils.FactionRanks;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public interface PlayerDataInterface {
    FactionMethods FACTION_METHODS = new FactionMethods();

    Faction getPlayerFaction(Player player);
    void setPlayerFaction(Player player, Faction faction);
    void removePlayerFaction(Player player);
    FactionRanks getPlayerRank(Player player);
    void setRankInFaction(Player player, FactionRanks rank);
    boolean isInFaction(Player player);
    HashMap<Player, List<FactionInvite>> pendingInvites = new HashMap<>();
    default HashMap<Player, List<FactionInvite>> getPendingInvites() {
        return pendingInvites;
    }
    void savePlayerData();
    void loadFactionData();
}
