package me.psikuvit.pharoahfactions.utils;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class FactionInviteMethods {

    static List<FactionInvite> factionInvites = new ArrayList<>();

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
            @Override
            public void run() {
                removeInvite(factionInvite);

            }
        }.runTaskTimer(Pharaoh_Factions.getInstance(), 300, 0);
    }

}
