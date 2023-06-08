package me.psikuvit.pharoahfactions.commands.claims.args;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.claims.Claim;
import me.psikuvit.pharoahfactions.claims.ClaimUtils;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.utils.Messages;
import me.psikuvit.pharoahfactions.utils.TeleportationManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ClaimTPArg extends CommandAbstract {

    private final ClaimUtils claimUtils = ClaimUtils.getInstance();

    public ClaimTPArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player p = (Player) sender;
        Player toTP = Bukkit.getPlayer(args[0]);

        if (toTP == null) {
            Messages.sendMessage(p, "&cCouldn't find player");
            return;
        }

        if (p.equals(toTP)) {
            Messages.sendMessage(p, "&cYou can't teleport to yourself");
            return;
        }
        if (!claimUtils.hasClaim(toTP.getUniqueId())) {
            Messages.sendMessage(p, "&cThis player doesn't have a claim.");
            return;
        }

        Claim claim = claimUtils.getPlayerClaim(toTP);

        Location loc = claim.getSpawnLoc();

        TeleportationManager tpManager = new TeleportationManager(p, loc);
        tpManager.startTeleportation();
    }

    @Override
    public String correctArg() {
        return "/claim tp <player>";
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
