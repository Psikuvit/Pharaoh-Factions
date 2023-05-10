package me.psikuvit.pharoahfactions.commands.args;

import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.FactionsMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import me.psikuvit.pharoahfactions.utils.FactionInvite;
import me.psikuvit.pharoahfactions.utils.FactionInviteMethods;
import me.psikuvit.pharoahfactions.utils.FactionRanks;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class FactionInviteArg extends CommandAbstract {

    public FactionInviteArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player player = (Player) sender;

        Player invited = Bukkit.getPlayer(args[0]);

        PlayerDataInterface playerData = Pharaoh_Factions.getInstance().getPlayerData();
        if (!playerData.isInFaction(player)) { // check if player is in a faction
            Messages.sendMessage(player, "&cYou are not in a faction");
            return;
        }
        Faction faction = playerData.getPlayerFaction(player);
        if (playerData.getPlayerRank(player, faction) == FactionRanks.MEMBER) {
            Messages.sendMessage(player, "&cYou're rank doesn't let you invite people");
            return;
        }

        HashMap<Player, List<FactionInvite>> pendingInvites = playerData.getPendingInvites();

        if (!pendingInvites.isEmpty()) {
            for (List<FactionInvite> invites : pendingInvites.values()) {
                for (FactionInvite factionInvite : invites) {
                    if (factionInvite.getFaction().equals(faction)) {
                        Messages.sendMessage(player, "&cPlayer Already is invited to this faction");
                        break;
                    }
                }
            }
            return;
        }

        FactionInvite factionInvite = new FactionInvite(player, invited, faction);
        FactionInviteMethods.addInvite(factionInvite);

        pendingInvites.put(invited, FactionInviteMethods.getFactionInvites());

        invited.spigot().sendMessage(factionInvite.getInviteMessage());

        FactionInviteMethods.removeInviteTask(factionInvite);


    }

    @Override
    public String correctArg() {
        return "/factions invite <player_name>";
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
}
