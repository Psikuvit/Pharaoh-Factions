package me.psikuvit.pharoahfactions.commands.claims.args;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.claims.Claim;
import me.psikuvit.pharoahfactions.claims.ClaimUtils;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AddMemberArg extends CommandAbstract {

    private final ClaimUtils claimUtils = ClaimUtils.getInstance();

    public AddMemberArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player p = (Player) sender;
        Player member = Bukkit.getPlayer(args[0]);

        if (member == null) {
            Messages.sendMessage(p, "&cCouldn't find player");
            return;
        }

        if (p.equals(member)) {
            Messages.sendMessage(p, "&cYou can't invite yourself");
            return;
        }

        if (claimUtils.hasClaim(p.getUniqueId())) {
            Messages.sendMessage(p, "&cYou don't have a claim to invite people");
            return;
        }

        Claim claim = claimUtils.getPlayerClaim(p);

        List<UUID> uuids = claim.getMembers();
        uuids.add(member.getUniqueId());

        Messages.sendMessage(p, "&e" + member.getName() + " &b was added to the claim!.");
    }

    @Override
    public String correctArg() {
        return "/claim addmember <player>";
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
