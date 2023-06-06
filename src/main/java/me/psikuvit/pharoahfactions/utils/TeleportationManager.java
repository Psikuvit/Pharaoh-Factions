package me.psikuvit.pharoahfactions.utils;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportationManager {
    private final Player player;
    private final Location destination;
    private BukkitRunnable countdownTask;
    private int countdown = 3;

    public TeleportationManager(Player player, Location destination) {
        this.player = player;
        this.destination = destination;
    }

    public void startTeleportation() {
        player.sendTitle("", ChatColor.RED + "Teleporting in " + countdown + " seconds", 0, 40, 10);
        Location tempLoc = player.getLocation();
        countdownTask = new BukkitRunnable() {
            @Override
            public void run() {
                countdown--;
                if (countdown <= 0) {
                    teleportPlayer();
                } else if (player.getLocation().getX() != tempLoc.getX() ||
                        player.getLocation().getY() != tempLoc.getY() ||
                        player.getLocation().getZ() != tempLoc.getZ()) {
                    cancelTeleportation();
                } else {
                    player.sendTitle("", ChatColor.RED + "Teleporting in " + countdown + " seconds", 0, 40, 10);
                }
            }
        };
        countdownTask.runTaskTimer(Pharaoh_Factions.getPlugin(Pharaoh_Factions.class), 20L, 20L); // Runs the task every second (20 ticks)
    }

    private void teleportPlayer() {
        player.teleport(destination);
        player.sendTitle("", ChatColor.GREEN + "Teleportation successful!", 10, 40, 10);
        countdownTask.cancel();
    }

    private void cancelTeleportation() {
        player.sendTitle("", ChatColor.YELLOW + "Teleportation canceled!", 10, 40, 10);
        countdownTask.cancel();
    }
}