package me.psikuvit.pharoahfactions.commands.claims.args;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.claims.Claim;
import me.psikuvit.pharoahfactions.claims.ClaimUtils;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class SpawnLocArg extends CommandAbstract {

    public SpawnLocArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player p = (Player) sender;

        if (ClaimUtils.hasClaim(p.getUniqueId())) {
            Messages.sendMessage(p, "&cYou don't have a claim to invite people");
            return;
        }

        Claim claim = ClaimUtils.getPlayerClaim(p);
        Location loc = p.getLocation();
        claim.setSpawnLoc(loc);

        Messages.sendMessage(p, "&bClaim spawn point was modified successfully");

    }

    @Override
    public String correctArg() {
        return "/claim setspawnpoint";
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
