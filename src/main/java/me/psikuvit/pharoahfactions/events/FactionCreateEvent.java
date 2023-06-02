package me.psikuvit.pharoahfactions.events;

import me.psikuvit.pharoahfactions.factions.Faction;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class FactionCreateEvent extends Event {

    private final HandlerList HANDLERS = new HandlerList();
    private final Faction faction;
    public FactionCreateEvent(Faction faction) {
        this.faction = faction;
    }

    public Faction getFaction() {
        return this.faction;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
