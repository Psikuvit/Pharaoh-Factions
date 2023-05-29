package me.psikuvit.pharoahfactions;

import me.psikuvit.pharoahfactions.commands.CommandRegisterer;
import me.psikuvit.pharoahfactions.data.factions.FactionsDataFiles;
import me.psikuvit.pharoahfactions.data.factions.FactionsDataInterface;
import me.psikuvit.pharoahfactions.data.player.PlayerDataFiles;
import me.psikuvit.pharoahfactions.data.player.PlayerDataInterface;
import me.psikuvit.pharoahfactions.listeners.InventoryClickEventListener;
import me.psikuvit.pharoahfactions.menusystem.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Pharaoh_Factions extends JavaPlugin {

    private final HashMap<UUID, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private FactionsDataInterface factionsData;
    private PlayerDataInterface playerData;

    @Override
    public void onEnable() {
        // Plugin startup logic
        factionsData = new FactionsDataFiles(this);
        playerData = new PlayerDataFiles(this);

        Objects.requireNonNull(getCommand("factions")).setExecutor(new CommandRegisterer(this));
        Objects.requireNonNull(getCommand("factions")).setTabCompleter(new CommandRegisterer(this));
        getServer().getPluginManager().registerEvents(new InventoryClickEventListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PlayerMenuUtility getPlayerMenuUtility(Player p) {
        return playerMenuUtilityMap.computeIfAbsent(p.getUniqueId(), PlayerMenuUtility::new);
    }
    public FactionsDataInterface getFactionsData() {
        return factionsData;
    }
    public PlayerDataInterface getPlayerData() {
        return playerData;
    }
}
