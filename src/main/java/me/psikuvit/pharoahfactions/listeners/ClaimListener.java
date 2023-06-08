package me.psikuvit.pharoahfactions.listeners;

import me.psikuvit.pharoahfactions.claims.Claim;
import me.psikuvit.pharoahfactions.claims.ClaimUtils;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

public class ClaimListener implements Listener {

    private final ClaimUtils claimUtils = ClaimUtils.getInstance();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlock();
        Chunk chunk = block.getChunk();

        Claim claim = claimUtils.getClaimByChunk(chunk);
        if (claim == null) {
            return;
        }

        UUID owner = claimUtils.getClaimOwner(claim);

        if (owner != (p.getUniqueId()) || claim.getMembers().contains(p.getUniqueId())) {
            Messages.sendMessage(p, "&cYou don't have access to break blocks in this chunk");
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlock();
        Chunk chunk = block.getChunk();

        Claim claim = claimUtils.getClaimByChunk(chunk);
        if (claim == null) {
            return;
        }
        UUID owner = claimUtils.getClaimOwner(claim);

        if (owner != (p.getUniqueId()) || claim.getMembers().contains(p.getUniqueId())) {
            Messages.sendMessage(p, "&cYou don't have access to place blocks in this chunk");
            e.setCancelled(true);
        }
    }
}
