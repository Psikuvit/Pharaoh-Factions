package me.psikuvit.pharoahfactions.commands;

import me.psikuvit.pharoahfactions.commands.args.FactionCreateArg;
import me.psikuvit.pharoahfactions.commands.args.FactionsGUIArg;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import java.util.HashMap;
import java.util.Map;

public class CommandRegisterer implements CommandExecutor {
    private final Map<String, CommandAbstract> commandAbstractMap;

    public CommandRegisterer(final Pharaoh_Factions plugin) {
        this.commandAbstractMap = new HashMap<>();
        commandAbstractMap.put("create", new FactionCreateArg(plugin));
        commandAbstractMap.put("gui", new FactionsGUIArg(plugin));

    }

    public boolean onCommand(final @NotNull CommandSender commandSender, final @NotNull Command command, final @NotNull String label, String[] args) {
        if (args.length == 0) {
            return true;
        }
        final String subCommand = args[0];
        boolean found = false;
        for (final String cmdAlias : this.commandAbstractMap.keySet()) {
            if (cmdAlias.equalsIgnoreCase(subCommand)) {
                final int argsCount = args.length - 1;
                final boolean isSenderPlayer = commandSender instanceof Player;
                final CommandAbstract cmd = this.commandAbstractMap.get(cmdAlias);
                if (cmd.bypassArgLimit() == 0) {
                    if (argsCount > cmd.requiredArg()) {
                        commandSender.sendMessage(Messages.color("§cCorrect usage: §e" + cmd.correctArg()));
                        return true;
                    }
                    if (argsCount < cmd.requiredArg()) {
                        commandSender.sendMessage(Messages.color("§cCorrect usage: §e" + cmd.correctArg()));
                        return true;
                    }
                } else if (cmd.bypassArgLimit() > 0) {
                    if (argsCount < cmd.bypassArgLimit()) {
                        commandSender.sendMessage(Messages.color("§cCorrect usage: §e" + cmd.correctArg()));
                        return true;
                    }
                }
                if (!isSenderPlayer && cmd.onlyPlayer()) {
                    commandSender.sendMessage(Messages.MUST_BE_PLAYER);
                    return true;
                }
                args = this.move(args);
                cmd.executeCommand(args, commandSender);
                found = true;
                break;
            }
        }
        if (!found) {
            commandSender.sendMessage(Messages.INCORRECT_COMMAND);
        }
        return true;
    }

    private String[] move(final String[] args) {
        final String[] result = new String[args.length - 1];
        System.arraycopy(args, 1, result, 0, args.length - 1);
        return result;
    }
}
