package me.psikuvit.pharoahfactions.menusystem.menu;

import me.psikuvit.pharoahfactions.Pharaoh_Factions;
import me.psikuvit.pharoahfactions.factions.utils.FactionInviteMethods;
import me.psikuvit.pharoahfactions.factions.utils.FactionMethods;
import me.psikuvit.pharoahfactions.menusystem.PaginatedMenu;
import me.psikuvit.pharoahfactions.menusystem.PlayerMenuUtility;
import me.psikuvit.pharoahfactions.factions.FactionInvite;
import me.psikuvit.pharoahfactions.utils.Messages;
import me.psikuvit.pharoahfactions.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class PlayerPendingInvitesGUI extends PaginatedMenu {

    private final FactionInviteMethods factionInviteMethods = FactionInviteMethods.getInstance();

    public PlayerPendingInvitesGUI(PlayerMenuUtility playerMenuUtility, Pharaoh_Factions plugin) {
        super(playerMenuUtility, plugin);
    }

    @Override
    public String getMenuName() {
        return "Your pending invites";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        List<FactionInvite> invites = factionInviteMethods.getFactionInvites(playerMenuUtility.getOwner());
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
                if (!((index + 1) >= invites.size())) {
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

        List<FactionInvite> invites = factionInviteMethods.getFactionInvites(playerMenuUtility.getOwner());

        for (int i = 0; i < getMaxItemsPerPage(); i++) {
            index = getMaxItemsPerPage() * page + i;
            if (index >= invites.size()) break;
            if (invites.get(index) != null) {
                ///////////////////////////
                FactionInvite invite = invites.get(index);

                //Create an item from our collection and place it into the inventory
                ItemStack playerItem = new ItemStack(Utils.getPlayerSkull(invite.getInviter()));
                ItemMeta playerMeta = playerItem.getItemMeta();
                playerMeta.setDisplayName(Messages.color(invite.getInviter().getName()));
                List<String> lore = Collections.singletonList(invite.getFaction().getName());
                playerMeta.setLore(Messages.color(lore));
                playerItem.setItemMeta(playerMeta);

                inventory.addItem(playerItem);
            }
        }
    }
}
