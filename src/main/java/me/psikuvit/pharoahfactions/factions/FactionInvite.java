package me.psikuvit.pharoahfactions.factions;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class FactionInvite {

    private final Player inviter;
    private final Faction faction;
    private final BaseComponent[] baseComponent;

    public FactionInvite(Player inviter, Faction faction) {
        this.inviter = inviter;
        this.faction = faction;

        baseComponent = new ComponentBuilder("You have been invited to join a faction by ")
                .color(ChatColor.GRAY)
                .append(inviter.getName())
                .color(ChatColor.GOLD)
                .append(".")
                .color(ChatColor.GRAY)
                .append("\n\n")
                .append("[Accept]")
                .color(ChatColor.GREEN)
                .bold(true)
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/factions accept " + inviter.getName()))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to accept the invitation").create()))
                .append("   ")
                .append("[Deny]")
                .color(ChatColor.RED)
                .bold(true)
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/factions deny " + inviter.getName()))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to deny the invitation").create()))
                .create();

    }

    public Faction getFaction() {
        return faction;
    }

    public Player getInviter() {
        return inviter;
    }

    public BaseComponent[] getInviteMessage() {
        return baseComponent;
    }

    @Override
    public String toString() {
        return "FactionInvite{" +
                "inviter=" + inviter +
                ", faction=" + faction +
                ", baseComponent=" + Arrays.toString(baseComponent) +
                '}';
    }
}
