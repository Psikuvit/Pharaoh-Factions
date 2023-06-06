package me.psikuvit.pharoahfactions.commands.factions.args;

import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.factions.utils.FactionRanks;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FactionDeleteArg extends CommandAbstract {

    public FactionDeleteArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player player = (Player) sender;
        if (!plugin.getPlayerData().isInFaction(player)) {
            Messages.sendMessage(player, "&cYou are not in a faction");
            return;
        }
        Faction faction = plugin.getPlayerData().getPlayerFaction(player);
        if (faction.getMembersRank().get(player) != FactionRanks.OWNER) {
            Messages.sendMessage(player, "&cYou are not the owner of this faction to run this command");
            return;
        }
        Messages.sendMessage(player, "&bFaction was deleted");
        removeFaction(faction);
    }

    @Override
    public String correctArg() {
        return "/factions delete";
    }

    @Override
    public boolean onlyPlayer() {
        return true;
    }

    @Override
    public int requiredArg() {
        return 0;
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
