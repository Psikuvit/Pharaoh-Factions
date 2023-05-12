package me.psikuvit.pharoahfactions.commands.args;

import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.FactionsMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import me.psikuvit.pharoahfactions.utils.FactionInvite;
import me.psikuvit.pharoahfactions.utils.FactionInviteMethods;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FactionInviteAcceptArg extends CommandAbstract {

    private Player p;

    public FactionInviteAcceptArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player invited = (Player) sender;
        Player inviter = Bukkit.getPlayer(args[0]);

        p = invited;

        FactionInvite factionInvite = FactionInviteMethods.getInviteByInviter(inviter);
        if (factionInvite == null) { // check if the inviter invited the invited
            Messages.sendMessage(invited, "&cCouldn't find invite from this player");
            return;
        }
        FactionInviteMethods.removeInvite(factionInvite); // remove the invite from the invitations list
        if (invited.isOnline()) { // check if player is online
            Messages.sendMessage(inviter, "&c" + invited.getName() + " accepted your invitation");
        }
        PlayerDataInterface playerDataInterface = plugin.getPlayerData();
        playerDataInterface.getPendingInvites().put(invited, FactionInviteMethods.getFactionInvites()); // setting the invitations again after denying

        Faction faction = factionInvite.getFaction();
        addPlayerToFaction(faction, invited);

        Messages.sendMessage(invited, "&bYou successfully joined the " + faction.getName() + " faction");
    }

    @Override
    public String correctArg() {
        return "/factions accept <player>";
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
        switch (args.length) {
            case 0: completions.add("accept");
            case 1 : {
                PlayerDataInterface playerDataInterface = plugin.getPlayerData();
                List<FactionInvite> factionInvites = playerDataInterface.getPendingInvites().get(p);
                for (FactionInvite factionInvite : factionInvites) {
                    completions.add(factionInvite.getInviter().getDisplayName());
                }
            }
        }

        return completions;
    }
}
