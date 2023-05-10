package me.psikuvit.pharoahfactions.commands.args;

import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.Faction;
import me.psikuvit.pharoahfactions.FactionsMethods;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.data.factions.FactionsDataInterface;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import me.psikuvit.pharoahfactions.utils.Messages;
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

        String description = String.join(" ", Arrays.copyOfRange(args, 1, args.length)); // gets infinite args above 1 to get the description

        Faction faction = new Faction(args[0], List.of(player), player, UUID.randomUUID(), List.of(description)); // creates the Faction

        FactionsMethods.addFaction(faction); // cache the Faction

        FactionsDataInterface factionsData = plugin.getFactionsData();
        factionsData.saveFactionData(faction);

        PlayerDataInterface playerData = plugin.getPlayerData();
        playerData.createPlayer(player);
        playerData.setPlayerFaction(player, faction);

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
        return 2;
    }
}
