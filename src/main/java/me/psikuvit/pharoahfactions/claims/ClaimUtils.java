package me.psikuvit.pharoahfactions.claims;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ClaimUtils {

    private final HashMap<UUID, Claim> playerClaims;

    private static ClaimUtils INSTANCE;

    private ClaimUtils(){
        this.playerClaims = new HashMap<>();
    }

    public static ClaimUtils getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ClaimUtils();
        }
        return INSTANCE;
    }

    /**
     * This method is used to get all pending invites
     *
     * @param uuid of the player to get claims
     * @return pending invites
     */

    public Claim getPlayerClaim(UUID uuid) {
        return playerClaims.getOrDefault(uuid, null);
    }

    /**
     * This method is used to get all pending invites
     *
     * @param player to get claims
     * @return pending invites
     */
    public Claim getPlayerClaim(Player player) {
        return getPlayerClaim(player.getUniqueId());
    }

    /**
     * This method is used to cache player invites
     *
     * @param owner to save claims for
     * @param claim to cache
     */
    public void addClaim(Player owner, Claim claim) {
        playerClaims.put(owner.getUniqueId(), claim);
    }

    /**
     * This method is used to cache player invites
     *
     * @param owner to delete invites for
     * @param claim to delete
     */
    public void removeClaim(Player owner, Claim claim) {
        playerClaims.remove(owner.getUniqueId(), claim);
    }

    public boolean isChunkTaken(Chunk chunk) {
        for (UUID uuid : playerClaims.keySet()) {
            Claim claim = playerClaims.get(uuid);
            return claim.getChunks().contains(chunk);

        }
        return false;
    }
    public Claim getClaimByChunk(Chunk chunk) {
        for (UUID uuid : playerClaims.keySet()) {
            Claim claim = playerClaims.get(uuid);
            if (claim.getChunks().contains(chunk)) {
                return claim;
            }
        }
        return null;
    }
    public UUID getClaimOwner(Claim claim) {
        for (UUID uuid : playerClaims.keySet()) {
            if (playerClaims.get(uuid) == claim) {
                return uuid;
            }
        }
        return null;
    }
    public boolean hasClaim(UUID uuid) {
        return playerClaims.containsKey(uuid);
    }
}