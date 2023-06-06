package me.psikuvit.pharoahfactions.claims;

import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class Claim {

    private final String name;
    private final List<Chunk> chunks;
    private Location spawnLoc;
    private final List<UUID> members;

    public Claim(String name, List<Chunk> chunks, Location spawnLoc, List<UUID> members) {
        this.name = name;
        this.chunks = chunks;
        this.spawnLoc = spawnLoc;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public Location getSpawnLoc() {
        return spawnLoc;
    }

    public void setSpawnLoc(Location spawnLoc) {
        this.spawnLoc = spawnLoc;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public List<UUID> getMembers() {
        return members;
    }
}
