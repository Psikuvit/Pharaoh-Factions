package me.psikuvit.pharoahfactions.commands.args;

import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.FactionsMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.utils.FactionRanks;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class FactionDeleteArg extends CommandAbstract {

    public FactionDeleteArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player player = (Player) sender;
        if (plugin.getPlayerData().isInFaction(player)) {
            Messages.sendMessage(player, "&cYou are not in a faction");
            return;
        }
        Faction faction = plugin.getPlayerData().getPlayerFaction(player);
        if (faction.getMembersRank().get(player) != FactionRanks.OWNER) {
            Messages.sendMessage(player, "&cYou are not the owner of this faction to run this command");
            return;
        }
        if (!faction.getMembers().isEmpty()) {
            for (Player player1 : faction.getMembers()) {
                removePlayerFromFaction(faction, player1);
            }
        } else {
            removeFaction(faction);
        }
    }

    @Override
    public String correctArg() {
        return "/factions delete <faction_name>";
    }

    @Override
    public boolean onlyPlayer() {
        return true;
    }

    @Override
    public int requiredArg() {
        return 1;
    }

    @Override
    public int bypassArgLimit() {
        return 0;
    }

    @Override
    public List<String> tabComplete(String[] args) {
        return null;
    }
}
