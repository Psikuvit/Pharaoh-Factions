package me.psikuvit.pharoahfactions.commands.claims.args;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.claims.Claim;
import me.psikuvit.pharoahfactions.claims.ClaimUtils;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ClaimArg extends CommandAbstract {

    public ClaimArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player p = (Player) sender;
        Chunk chunk = p.getLocation().getChunk();

        if (ClaimUtils.isChunkTaken(chunk)) {
            Messages.sendMessage(p, "&cChunk already taken");
            return;
        }
        List<Chunk> chunks = new ArrayList<>();
        chunks.add(chunk);
        Location loc = p.getLocation();

        Claim claim = new Claim(chunks, loc, Collections.emptyList());
        ClaimUtils.addPlayerClaim(p, claim);

        Messages.sendMessage(p, "&bClaimed this chunk");
    }

    @Override
    public String correctArg() {
        return "/claim claim";
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
