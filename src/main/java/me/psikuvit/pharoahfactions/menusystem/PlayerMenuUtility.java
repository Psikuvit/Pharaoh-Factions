package me.psikuvit.pharoahfactions.menusystem;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerMenuUtility {

    private final UUID owner;
    public PlayerMenuUtility(UUID owner) {
        this.owner = owner;
    }

    public UUID getOwner() {
        return owner;
    }
}

