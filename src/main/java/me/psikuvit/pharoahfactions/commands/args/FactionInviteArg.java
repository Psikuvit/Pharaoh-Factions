package me.psikuvit.pharoahfactions.commands.args;

import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import me.psikuvit.pharoahfactions.factions.FactionInvite;
import me.psikuvit.pharoahfactions.factions.utils.FactionInviteMethods;
import me.psikuvit.pharoahfactions.factions.utils.FactionRanks;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FactionInviteArg extends CommandAbstract {

    public FactionInviteArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player player = (Player) sender;

        Player invited = Bukkit.getPlayer(args[0]);

        if (invited == null) {
            Messages.sendMessage(player, "&cCouldn't find player");
            return;
        }

        if (player.equals(invited)) {
            Messages.sendMessage(player, "&cYou can't invite yourself");
            return;
        }

        PlayerDataInterface playerData = plugin.getPlayerData();
        if (!playerData.isInFaction(player)) { // check if player is in a faction
            Messages.sendMessage(player, "&cYou are not in a faction");
            return;
        }
        Faction faction = playerData.getPlayerFaction(player);
        if (playerData.getPlayerRank(player) == FactionRanks.MEMBER) {
            Messages.sendMessage(player, "&cYou're rank doesn't let you invite people");
            return;
        }

        List<FactionInvite> pendingInvites = FactionInviteMethods.getFactionInvites(invited);

        if (!pendingInvites.isEmpty()) {
            for (FactionInvite invite : pendingInvites) {
                if (invite.getFaction().equals(faction)) {
                    Messages.sendMessage(player, "&cPlayer Already is invited to this faction");
                    break;
                }

            }
            return;
        }

        FactionInvite factionInvite = new FactionInvite(player, invited, faction);
        pendingInvites.add(factionInvite);

        FactionInviteMethods.addPlayerInvite(invited, pendingInvites);

        invited.spigot().sendMessage(factionInvite.getInviteMessage());

        FactionInviteMethods.removeInviteTask(invited, factionInvite);

        Messages.sendMessage(player, "&3invite sent successfully");


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
    @Override
    public List<String> tabComplete(String[] args) {
        List<String> completions = new ArrayList<>();

        // Provide tab completions for the 'gui' argument
        if (args.length == 0) {
            completions.add("invite");
        }

        return completions;
    }
}
