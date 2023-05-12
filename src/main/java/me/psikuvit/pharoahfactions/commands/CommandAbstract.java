package me.psikuvit.pharoahfactions.commands;

import me.psikuvit.pharoahfactions.FactionsMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class CommandAbstract implements FactionsMethods {
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