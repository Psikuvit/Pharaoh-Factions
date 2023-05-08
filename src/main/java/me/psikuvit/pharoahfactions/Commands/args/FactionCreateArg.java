package me.psikuvit.pharoahfactions.Commands.args;

import me.psikuvit.pharoahfactions.Commands.CommandAbstract;
import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.FactionsMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.Utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FactionCreateArg extends CommandAbstract {
    public FactionCreateArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player player = (Player) sender;

        String description = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        Faction faction = new Faction(args[0], List.of(player), player, UUID.randomUUID(), List.of(description));
        FactionsMethods.addFaction(faction);
        Messages.sendMessage(player, "&bFaction named: &f" + args[0] + " &bwas created");

    }

    @Override
    public String correctArg() {
        return "/factions create <name> <description>";
    }

    @Override
    public boolean onlyPlayer() {
        return true;
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
