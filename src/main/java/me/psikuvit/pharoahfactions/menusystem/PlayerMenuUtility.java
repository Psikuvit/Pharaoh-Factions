package me.psikuvit.pharoahfactions.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private final Player owner;
    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }
}

