package me.psikuvit.pharoahfactions.claims;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class Claim {
    private final List<Chunk> chunks;
    private final Location claimLoc;
    private final List<UUID> members;

    public Claim(List<Chunk> chunks, Location claimLoc, List<UUID> members) {
        this.chunks = chunks;
        this.claimLoc = claimLoc;
        this.members = members;
    }
    public List<Chunk> getChunks() {
        return chunks;
    }

    public Location getClaimLoc() {
        return claimLoc;
    }

    public List<UUID> getMembers() {
        return members;
    }
}
