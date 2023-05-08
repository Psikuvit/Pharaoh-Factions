package me.psikuvit.pharoahfactions.commands;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import org.bukkit.command.CommandSender;

public abstract class CommandAbstract
{
    protected Pharaoh_Factions plugin;

    public CommandAbstract(final Pharaoh_Factions plugin) {
        this.plugin = plugin;
    }

    public abstract void executeCommand(final String[] args, final CommandSender sender);

    public abstract String correctArg();

    public abstract boolean onlyPlayer();

    public abstract int requiredArg();

    public abstract int bypassArgLimit();
}