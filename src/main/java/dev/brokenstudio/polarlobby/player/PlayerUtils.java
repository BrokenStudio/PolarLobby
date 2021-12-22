package dev.brokenstudio.polarlobby.player;

import dev.brokenstudio.polarlobby.hotbar.HotbarItems;
import dev.brokenstudio.polarlobby.utils.PlayerHeadURLStorage;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import dev.brokenstudio.polarlobby.utils.Skull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {

    private HotbarItems hotbarItems;

    {
        hotbarItems = new HotbarItems();
    }

    public void join(Player player){
        hotbarItems.setHotbar(player);
    }

}
