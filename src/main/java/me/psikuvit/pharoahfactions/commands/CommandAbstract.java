package me.psikuvit.pharoahfactions.commands;

import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class CommandAbstract extends FactionMethods {
    protected Pharaoh_Factions plugin;
    public CommandAbstract(final Pharaoh_Factions plugin) {
        this.plugin = plugin;
    }

    public abstract void executeCommand(final String[] args, final CommandSender sender);

    public abstract String correctArg();

    public abstract boolean onlyPlayer();

    public abstract int requiredArg();

    public abstract int bypassArgLimit();

    public abstract List<String> tabComplete(final String[] args);
}