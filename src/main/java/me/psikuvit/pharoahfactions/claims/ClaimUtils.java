package me.psikuvit.pharoahfactions.claims;

import me.psikuvit.pharoahfactions.factions.FactionInvite;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.*;

public class ClaimUtils {

    private static final HashMap<UUID, List<Claim>> playerClaims = new HashMap<>();

    /**
     * This method is used to get all pending invites
     *
     * @param player to get claims
     * @return pending invites
     */
    public static List<Claim> getPlayerClaims(Player player) {
        return playerClaims.get(player.getUniqueId());
    }

    public static List<Claim> getPlayerClaims(UUID uuid) {
        return playerClaims.get(uuid);
    }

    /**
     * This method is used to cache player invites
     *
     * @param owner to save claims for
     * @param claim to cache
     */
    public static void addPlayerClaim(Player owner, Claim claim) {
        List<Claim> x = playerClaims.get(owner.getUniqueId());
        x.add(claim);
        playerClaims.put(owner.getUniqueId(), x);
    }

    /**
     * This method is used to cache player invites
     *
     * @param owner to delete invites for
     * @param claim to delete
     */
    public static void removePlayerClaim(Player owner, Claim claim) {
        List<Claim> x = playerClaims.get(owner.getUniqueId());
        x.remove(claim);
        playerClaims.put(owner.getUniqueId(), x);
    }

    public static boolean isChunkTaken(Chunk chunk) {
        for (UUID uuid : playerClaims.keySet()) {
            List<Claim> claims = playerClaims.get(uuid);
            for (Claim claim : claims) {
                return claim.getChunks().contains(chunk);
            }
        }
        return false;
    }
    public static Claim getClaimByChunk(Chunk chunk) {
        for (UUID uuid : playerClaims.keySet()) {
            List<Claim> claims = playerClaims.get(uuid);
            for (Claim claim : claims) {
                if (claim.getChunks().contains(chunk)) {
                    return claim;
                }
            }
        }
        return null;
    }
    public static UUID getClaimOwner(Claim claim) {
        for (UUID uuid : playerClaims.keySet()) {
            if (playerClaims.get(uuid).contains(claim)) {
                return uuid;
            }
        }
        return null;
    }
}