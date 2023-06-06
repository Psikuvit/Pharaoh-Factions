package me.psikuvit.pharoahfactions.claims;

import me.psikuvit.pharoahfactions.factions.FactionInvite;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.*;

public class ClaimUtils {

    private static final HashMap<UUID, Claim> playerClaims = new HashMap<>();

    /**
     * This method is used to get all pending invites
     *
     * @param uuid of the player to get claims
     * @return pending invites
     */

    public static Claim getPlayerClaim(UUID uuid) {
        return playerClaims.getOrDefault(uuid, null);
    }

    /**
     * This method is used to get all pending invites
     *
     * @param player to get claims
     * @return pending invites
     */
    public static Claim getPlayerClaim(Player player) {
        return getPlayerClaim(player.getUniqueId());
    }

    /**
     * This method is used to cache player invites
     *
     * @param owner to save claims for
     * @param claim to cache
     */
    public static void addClaim(Player owner, Claim claim) {
        playerClaims.put(owner.getUniqueId(), claim);
    }

    /**
     * This method is used to cache player invites
     *
     * @param owner to delete invites for
     * @param claim to delete
     */
    public static void removeClaim(Player owner, Claim claim) {
        playerClaims.remove(owner.getUniqueId(), claim);
    }

    public static boolean isChunkTaken(Chunk chunk) {
        for (UUID uuid : playerClaims.keySet()) {
            Claim claim = playerClaims.get(uuid);
            return claim.getChunks().contains(chunk);

        }
        return false;
    }
    public static Claim getClaimByChunk(Chunk chunk) {
        for (UUID uuid : playerClaims.keySet()) {
            Claim claim = playerClaims.get(uuid);
            if (claim.getChunks().contains(chunk)) {
                return claim;
            }
        }
        return null;
    }
    public static UUID getClaimOwner(Claim claim) {
        for (UUID uuid : playerClaims.keySet()) {
            if (playerClaims.get(uuid) == claim) {
                return uuid;
            }
        }
        return null;
    }
    public static boolean hasClaim(UUID uuid) {
        return playerClaims.containsKey(uuid);
    }
}