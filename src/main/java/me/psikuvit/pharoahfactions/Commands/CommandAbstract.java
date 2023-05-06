package me.psikuvit.pharoahfactions.Commands;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import org.bukkit.command.CommandSender;

public abstract class CommandAbstract
{
    protected Pharaoh_Factions plugin;

    public CommandAbstract(final Pharaoh_Factions plugin) {
        this.plugin = plugin;
    }

    public abstract void executeCommand(final String[] p0, final CommandSender p1);

    public abstract String correctArg();

    public abstract boolean onlyPlayer();

    public abstract int requiredArg();
}