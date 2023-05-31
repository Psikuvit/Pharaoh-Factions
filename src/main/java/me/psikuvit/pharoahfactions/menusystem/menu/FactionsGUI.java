package me.psikuvit.pharoahfactions.menusystem.menu;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.factions.Faction;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.utils.Messages;
import me.psikuvit.pharoahfactions.utils.Utils;
import me.psikuvit.pharoahfactions.menusystem.PaginatedMenu;
import me.psikuvit.pharoahfactions.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class FactionsGUI extends PaginatedMenu {

    public FactionsGUI(PlayerMenuUtility playerMenuUtility, Pharaoh_Factions plugin) {
        super(playerMenuUtility, plugin);
    }

    @Override
    public String getMenuName() {
        return "MainMenu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        List<Faction> factions = FactionMethods.getFactions();
        if (e.getCurrentItem().getType() == Material.BARRIER) {
            p.closeInventory();
        } else if (e.getCurrentItem().getType() == Material.DARK_OAK_BUTTON) {
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Left")) {
                if (page == 0) {
                    p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                } else {
                    page = page - 1;
                    super.open(p);
                }
            } else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Right")) {
                if (!((index + 1) >= factions.size())) {
                    page = page + 1;
                    super.open(p);
                } else {
                    p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                }
            }
        }
    }

    @Override
    public void setMenuItems() {
        addMenuBorder();

        List<Faction> factions = FactionMethods.getFactions();

        for (int i = 0; i < getMaxItemsPerPage(); i++) {
            index = getMaxItemsPerPage() * page + i;
            if (index >= factions.size()) break;
            if (factions.get(index) != null) {
                ///////////////////////////
                Faction faction = factions.get(index);

                //Create an item from our collection and place it into the inventory
                ItemStack playerItem = new ItemStack(Utils.getPlayerSkull(faction.getOwner()));
                ItemMeta playerMeta = playerItem.getItemMeta();
                playerMeta.setDisplayName(Messages.color(faction.getName()));
                playerMeta.setLore(Messages.color(faction.getDescription()));
                playerItem.setItemMeta(playerMeta);

                inventory.addItem(playerItem);
            }
        }
    }
}
