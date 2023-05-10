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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Pharaoh_Factions extends JavaPlugin {

    static Pharaoh_Factions plugin;
    private final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    FactionsDataInterface factionsData;
    PlayerDataInterface playerData;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        factionsData = new FactionsDataFiles();
        playerData = new PlayerDataFiles();

        getCommand("factions").setExecutor(new CommandRegisterer(this));
        getServer().getPluginManager().registerEvents(new InventoryClickEventListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p); //Return the object by using the provided player
        }
    }


    public static Pharaoh_Factions getInstance() {
        return plugin;
    }

    public FactionsDataInterface getFactionsData() {
        return factionsData;
    }
    public PlayerDataInterface getPlayerData() {
        return playerData;
    }
}
