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

    void createPlayer(Player player);
    Faction getPlayerFaction(Player player);
    void setPlayerFaction(Player player, Faction faction);
    void removePlayerFaction(Player player, Faction faction);
    FactionRanks getPlayerRank(Player player, Faction faction);
    void setRankInFaction(Player player, Faction faction, FactionRanks rank);
    boolean isInFaction(Player player);
    HashMap<Player, List<FactionInvite>> pendingInvites = new HashMap<>();
    default HashMap<Player, List<FactionInvite>> getPendingInvites() {
        return pendingInvites;
    }


}
