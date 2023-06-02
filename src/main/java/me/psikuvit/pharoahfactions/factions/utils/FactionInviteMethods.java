package me.psikuvit.pharoahfactions.factions.utils;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.factions.FactionInvite;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FactionInviteMethods {

    private static final HashMap<UUID, List<FactionInvite>> factionInvites = new HashMap<>();

    /**
     * This method is used to get all pending invites
     *
     * @return pending invites
     */
    public static List<FactionInvite> getFactionInvites(Player player) {
        return factionInvites.get(player.getUniqueId());
    }

    public static List<FactionInvite> getFactionInvites(UUID uuid) {
        return factionInvites.get(uuid);
    }

    /**
     * This method is used to cache player invites
     *
     * @param invited to save invites for
     * @param invites to cache
     */
    public static void addPlayerInvite(Player invited, List<FactionInvite> invites) {
        factionInvites.put(invited.getUniqueId(), invites);
    }

    /**
     * This method is used to cache player invites
     *
     * @param invited to delete invites for
     * @param invites to delete
     */
    public static void removeInvite(Player invited, List<FactionInvite> invites) {
        factionInvites.remove(invited.getUniqueId(), invites);
    }

    public static FactionInvite getInviteByInviter(Player invited, Player inviter) {
        for (FactionInvite factionInvite : factionInvites.get(invited.getUniqueId())) {
            if (factionInvite.getInviter().equals(inviter) && factionInvite.getInvited().equals(invited)) {
                return factionInvite;
            }
        }
        return null;
    }

    public static void removeInviteTask(Player player, FactionInvite factionInvite) {
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
