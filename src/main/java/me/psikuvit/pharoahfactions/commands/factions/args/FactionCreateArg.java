package me.psikuvit.pharoahfactions.commands.factions.args;

import me.psikuvit.pharoahfactions.events.FactionCreateEvent;
import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class FactionCreateArg extends CommandAbstract {

    private final FactionMethods factionMethods = FactionMethods.getInstance();

    public FactionCreateArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player player = (Player) sender;
        PlayerDataInterface playerData = plugin.getPlayerData();
        if (playerData.isInFaction(player)) {
            Messages.sendMessage(player, "&cYou already have a faction");
            return;
        }

        String description = String.join(" ", Arrays.copyOfRange(args, 1, args.length)); // gets infinite args above 1 to get the description

        Faction faction = new Faction(args[0], Collections.singletonList(player), player, UUID.randomUUID(), Collections.singletonList(description)); // creates the Faction

        factionMethods.addFaction(faction); // cache the Faction

        playerData.setPlayerFaction(player, faction);

        Messages.sendMessage(player, "&bFaction named: &f" + args[0] + " &bwas created");

        FactionCreateEvent factionCreateEvent = new FactionCreateEvent(faction);
        Bukkit.getPluginManager().callEvent(factionCreateEvent);
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

    @Override
    public List<String> tabComplete(String[] args) {
        List<String> completions = new ArrayList<>();

        // Provide tab completions for the 'gui' argument
        if (args.length == 0) {
            completions.add("create");
        }

        return completions;
    }
}
