package me.psikuvit.pharoahfactions.data.player;

import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.utils.FactionRanks;
import org.bukkit.entity.Player;

public interface PlayerDataInterface {

    Pharaoh_Factions plugin = Pharaoh_Factions.getInstance();

    void createPlayer(Player player);
    Faction getPlayerFaction(Player player);
    void setPlayerFaction(Player player, Faction faction);
    FactionRanks getRankInFaction(Player player, Faction faction);
    void setRankInFaction(Player player, Faction faction, FactionRanks rank);
}
