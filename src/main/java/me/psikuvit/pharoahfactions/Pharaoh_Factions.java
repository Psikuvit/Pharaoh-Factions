package me.psikuvit.pharoahfactions;

import me.psikuvit.pharoahfactions.Commands.CommandRegisterer;
import me.psikuvit.pharoahfactions.menusystem.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Pharaoh_Factions extends JavaPlugin {

    static Pharaoh_Factions plugin;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getCommand("factions").setExecutor(new CommandRegisterer(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
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
}
