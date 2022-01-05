package dev.brokenstudio.polarlobby.inventorues;

import dev.brokenstudio.polarinvs.ClickableItem;
import dev.brokenstudio.polarinvs.content.InventoryContents;
import dev.brokenstudio.polarinvs.content.InventoryProvider;
import dev.brokenstudio.polarlobby.player.LobbySettings;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class InventoryProviders {

    public static class NavigatorProvider implements InventoryProvider {

        @Override
        public void init(Player player, InventoryContents contents) {
            contents.fillRow(1, LobbySettings.handler().getSettings(player).getColor().glass());
            contents.fillRow(5, LobbySettings.handler().getSettings(player).getColor().glass());
            contents.set(3, 2, new ClickableItem(new PolarItem(Material.IRON_SWORD).name("§8•● §4The§cHunt §8●•").lore("§8§oHorror & Survival").hideAttributes(),(event) -> {

            }));
            contents.set(3, 4, new ClickableItem(new PolarItem(Material.GRASS_BLOCK).name("§8•● §2Free§aBuild §8●•").lore("§8§oOldschool").hideAttributes(),(event) -> {

            }));
            contents.set(3, 6, new ClickableItem(new PolarItem(Material.DIAMOND_BOOTS).name("§8•● §6Switch§eFFA §8●•").lore("§8§oSolo & PvP").hideAttributes(),(event) -> {

            }));
            contents.set(3, 8, new ClickableItem(new PolarItem(Material.DIAMOND_BOOTS).name("§8•● §3Jump§bCity §8●•").lore("§8§oSolo & Parkour").hideAttributes(),(event) -> {

            }));
            contents.set(5, 3, new ClickableItem(new PolarItem(Material.PRISMARINE_SHARD).name("§8•● §5Coin§dSystem §8●•").lore().hideAttributes(),(event) -> {

            }));
            contents.set(5, 5, new ClickableItem(new PolarItem(Material.TOTEM_OF_UNDYING).name("§8•● §5Spawn §8●•").lore().hideAttributes(),(event) -> {

            }));
            contents.set(5, 7, new ClickableItem(new PolarItem(Material.RESPAWN_ANCHOR).name("§8•● §5Mystery§dCrates §8●•").lore().hideAttributes(),(event) -> {

            }));
        }
    }

}
