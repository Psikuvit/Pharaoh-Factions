package me.psikuvit.pharoahfactions.data.player;

import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.factions.utils.FactionRanks;
import org.bukkit.entity.Player;

public interface PlayerDataInterface {
    Faction getPlayerFaction(Player player);
    void setPlayerFaction(Player player, Faction faction);
    void removePlayerFaction(Player player);
    FactionRanks getPlayerRank(Player player);
    void setRankInFaction(Player player, FactionRanks rank);
    boolean isInFaction(Player player);
    void savePlayerData();
    void loadFactionData();
}
