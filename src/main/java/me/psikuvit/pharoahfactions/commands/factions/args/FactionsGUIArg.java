package me.psikuvit.pharoahfactions.commands.factions.args;

import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.menusystem.menu.FactionsGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FactionsGUIArg extends CommandAbstract {
    public FactionsGUIArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player player = (Player) sender;
        new FactionsGUI(plugin.getPlayerMenuUtility(player), plugin).open(player);

    }

    @Override
    public String correctArg() {
        return "/factions gui";
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
            completions.add("gui");
        }

        return completions;
    }
}
