package me.psikuvit.pharoahfactions.factions.utils;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.factions.FactionInvite;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class FactionInviteMethods {

    private static final List<FactionInvite> factionInvites = new ArrayList<>();

    /**
     * This method is used to get all pending invites
     * @return pending invites
     */
    public static List<FactionInvite> getFactionInvites() {
        return factionInvites;
    }

    /**
     * This method is used to cache the invite
     * @param invite to cache
     */
    public static void addInvite(FactionInvite invite) {
        getFactionInvites().add(invite);
    }

    /**
     * This method is used to delete the invite
     * @param invite to delete
     */
    public static void removeInvite(FactionInvite invite) {
        getFactionInvites().remove(invite);
    }

    public static FactionInvite getInviteByInviter(Player inviter) {
        for (FactionInvite factionInvite : getFactionInvites()) {
            if (factionInvite.getInviter().equals(inviter)) {
                return factionInvite;
            }
        }
        return null;
    }

    public static void removeInviteTask(FactionInvite factionInvite) {
        new BukkitRunnable() {
            int time = 0;
            @Override
            public void run() {
                time++;
                if (time == 300) {
                    if (!getFactionInvites().isEmpty()) {
                        removeInvite(factionInvite);
                        cancel();
                    }
                }
            }
        }.runTaskTimer(Pharaoh_Factions.getPlugin(Pharaoh_Factions.class), 6000, 0);
    }

}
