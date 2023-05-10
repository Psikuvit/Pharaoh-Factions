package me.psikuvit.pharoahfactions.commands.args;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import me.psikuvit.pharoahfactions.utils.FactionInvite;
import me.psikuvit.pharoahfactions.utils.FactionInviteMethods;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionInviteDenyArg extends CommandAbstract {

    public FactionInviteDenyArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player invited = (Player) sender;
        Player inviter = Bukkit.getPlayer(args[0]);

        FactionInvite factionInvite = FactionInviteMethods.getInviteByInviter(inviter);
        if (factionInvite == null) { // check if the inviter invited the invited
            Messages.sendMessage(invited, "&cCouldn't find invite from this player");
            return;
        }
        FactionInviteMethods.removeInvite(factionInvite); // remove the invite from the invitations list
        if (invited.isOnline()) { // check if player is online
            Messages.sendMessage(inviter, "&c" + invited.getName() + " denied your invitation");
        }

        PlayerDataInterface playerDataInterface = plugin.getPlayerData();
        playerDataInterface.getPendingInvites().put(invited, FactionInviteMethods.getFactionInvites()); // setting the invitations again after denying
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
}
