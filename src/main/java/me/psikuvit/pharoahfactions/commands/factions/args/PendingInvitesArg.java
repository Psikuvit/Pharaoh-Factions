package me.psikuvit.pharoahfactions.commands.factions.args;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.factions.utils.FactionInviteMethods;
import me.psikuvit.pharoahfactions.menusystem.menu.PlayerPendingInvitesGUI;
import me.psikuvit.pharoahfactions.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PendingInvitesArg extends CommandAbstract {

    public PendingInvitesArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player p = (Player) sender;
        if (FactionInviteMethods.getFactionInvites(p) == null) {
            Messages.sendMessage(p, "&cYou have no pending invites");
            return;
        }
        new PlayerPendingInvitesGUI(plugin.getPlayerMenuUtility(p), plugin).open(p);

    }

    @Override
    public String correctArg() {
        return "/factions pendinginvites";
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

    @Override
    public List<String> tabComplete(String[] args) {
        List<String> completions = new ArrayList<>();

        // Provide tab completions for the 'gui' argument
        if (args.length == 0) {
            completions.add("pendinginvites");
        }

        return completions;
    }
    }
