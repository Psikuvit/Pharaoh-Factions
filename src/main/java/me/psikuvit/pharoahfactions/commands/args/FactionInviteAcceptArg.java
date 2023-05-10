package me.psikuvit.pharoahfactions.commands.args;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import org.bukkit.command.CommandSender;

public class FactionInviteAcceptArg extends CommandAbstract {

    public FactionInviteAcceptArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {

    }

    @Override
    public String correctArg() {
        return null;
    }

    @Override
    public boolean onlyPlayer() {
        return false;
    }

    @Override
    public int requiredArg() {
        return 0;
    }

    @Override
    public int bypassArgLimit() {
        return 0;
    }
}
