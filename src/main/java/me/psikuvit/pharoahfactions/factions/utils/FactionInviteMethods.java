package me.psikuvit.pharoahfactions.factions.utils;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.claims.ClaimUtils;
import me.psikuvit.pharoahfactions.factions.FactionInvite;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FactionInviteMethods {

    private final HashMap<UUID, List<FactionInvite>> factionInvites;
    private static FactionInviteMethods INSTANCE;

    private FactionInviteMethods(){
        this.factionInvites = new HashMap<>();
    }

    public static FactionInviteMethods getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FactionInviteMethods();
        }
        return INSTANCE;
    }

    /**
     * This method is used to get all pending invites
     *
     * @return pending invites
     */
    public List<FactionInvite> getFactionInvites(Player player) {
        return factionInvites.get(player.getUniqueId());
    }

    public List<FactionInvite> getFactionInvites(UUID uuid) {
        return factionInvites.get(uuid);
    }

    /**
     * This method is used to cache player invites
     *
     * @param invited to save invites for
     * @param invite to cache
     */
    public void addPlayerInvite(Player invited, FactionInvite invite) {
        List<FactionInvite> x = factionInvites.get(invited.getUniqueId());
        x.add(invite);
        factionInvites.put(invited.getUniqueId(), x);
    }

    /**
     * This method is used to cache player invites
     *
     * @param invited to delete invites for
     * @param invite to delete
     */
    public void removeInvite(Player invited, FactionInvite invite) {
        List<FactionInvite> x = factionInvites.get(invited.getUniqueId());
        x.remove(invite);
        factionInvites.put(invited.getUniqueId(), x);
    }

    public FactionInvite getInviteByInviter(Player invited, Player inviter) {
        for (FactionInvite factionInvite : factionInvites.get(invited.getUniqueId())) {
            if (factionInvite.getInviter().equals(inviter)) {
                return factionInvite;
            }
        }
        return null;
    }

    public void removeInviteTask(Player player, FactionInvite factionInvite) {
        new BukkitRunnable() {
            int time = 0;

            @Override
            public void run() {
                time++;
                if (time == 300) {
                    getFactionInvites(player).remove(factionInvite);
                    cancel();

                }
            }
        }.runTaskTimer(Pharaoh_Factions.getPlugin(Pharaoh_Factions.class), 6000, 0);
    }

}
