package me.psikuvit.pharoahfactions.commands.args;

import me.psikuvit.pharoahfactions.commands.CommandAbstract;
import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.menusystem.menu.FactionsGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionsGUIArg extends CommandAbstract {
    public FactionsGUIArg(Pharaoh_Factions plugin) {
        super(plugin);
    }

    @Override
    public void executeCommand(String[] args, CommandSender sender) {
        Player player = (Player) sender;
        new FactionsGUI(Pharaoh_Factions.getPlayerMenuUtility(player)).open(player);

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
}
