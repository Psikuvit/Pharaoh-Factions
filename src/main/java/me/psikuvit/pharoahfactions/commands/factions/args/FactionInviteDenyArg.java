package me.psikuvit.pharoahfactions.commands.factions.args;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.factions.FactionInvite;
import me.psikuvit.pharoahfactions.factions.utils.FactionInviteMethods;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FactionInviteDenyArg extends CommandAbstract {

    private final FactionMethods factionMethods = FactionMethods.getInstance();
    private final FactionInviteMethods factionInviteMethods  = FactionInviteMethods.getInstance();
    private Player p;

    public FactionInviteDenyArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player invited = (Player) sender;
        Player inviter = Bukkit.getPlayer(args[0]);
        p = invited;
        if (inviter == null) {
            Messages.sendMessage(invited, "&cCouldn't find this player");
            return;
        }

        FactionInvite factionInvite = factionInviteMethods.getInviteByInviter(invited, inviter);

        if (factionInvite == null) { // check if the inviter invited the invited
            Messages.sendMessage(invited, "&cCouldn't find invite from this player");
            return;
        }
        if (invited.isOnline()) { // check if player is online
            Messages.sendMessage(inviter, "&c" + invited.getName() + " denied your invitation");
        }
        factionInviteMethods.removeInvite(invited, factionInvite); // remove the invite from the invitations list

        Messages.sendMessage(invited, "&bYou successfully denied the invite from " + invited.getName());
    }

    @Override
    public String correctArg() {
        return "/factions deny <player_name>";
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
            case 0: completions.add("deny");
            case 1 : {
                List<FactionInvite> factionInvites = factionInviteMethods.getFactionInvites(p);
                for (FactionInvite factionInvite : factionInvites) {
                    completions.add(factionInvite.getInviter().getDisplayName());
                }
            }
        }
        return completions;
    }
}
