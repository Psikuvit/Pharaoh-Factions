package me.psikuvit.pharoahfactions.claims;

import org.bukkit.Chunk;

import java.util.UUID;

public class Claim {

    private final UUID Owner;
    private final Chunk chunk;

    public Claim(UUID Owner, Chunk chunk) {
        this.Owner = Owner;
        this.chunk = chunk;
    }
    public UUID getOwner() {
        return Owner;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
