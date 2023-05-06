package me.psikuvit.pharoahfactions;

import me.psikuvit.pharoahfactions.Commands.CommandRegisterer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pharaoh_Factions extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("factions").setExecutor(new CommandRegisterer(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
