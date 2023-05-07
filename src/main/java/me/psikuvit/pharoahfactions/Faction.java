package me.psikuvit.pharoahfactions;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Faction {

    String name;
    List<Player> members;
    Player owner;
    UUID uuid;

    public Faction(String name, List<Player> members, Player owner, UUID uuid) {
        this.name = name;
        this.members = members;
        this.owner = owner;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public List<Player> getMembers() {
        return members;
    }

    public Player getOwner() {
        return owner;
    }

    public UUID getUuid() {
        return uuid;
    }
}
