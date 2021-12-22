package dev.brokenstudio.polarlobby.hotbar;

import dev.brokenstudio.polarlobby.utils.PlayerHeadURLStorage;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import dev.brokenstudio.polarlobby.utils.Skull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HotbarItems {

    private final ItemStack[] hotbarItems;

    {
        hotbarItems = new ItemStack[]{null,null,null,null,null,null,null,null,null};
        _initialize();
    }

    private void _initialize(){
        hotbarItems[1] = new PolarItem(Skull
                .getCustomSkull("http://textures.minecraft.net/texture/bae216305b54f7b98fb589e4c94edfa70d085bf382cf5e8ca234f419495c7f8a"))
                .hideAttributes().name("§8•● §dNavigator §8▎ §7Rechtsklick §8●•");
        hotbarItems[2] = new PolarItem(Material.TOTEM_OF_UNDYING).hideAttributes().name("§8•● §bSpieler verstecken §8▎ §7Rechtsklick §8●•");
        hotbarItems[4] = new PolarItem(Material.STRUCTURE_VOID).hideAttributes().name("§8•● §cKein Gadget ausgewählt §8●•");
        hotbarItems[6] = new PolarItem(Material.FURNACE_MINECART).hideAttributes().name("§8•● §bSammlung §8▎ §7Rechtsklick §8●•");
    }

    public void setHotbar(Player player){
        player.getInventory().setContents(hotbarItems);
        player.getInventory().setItem(7, new PolarItem(Skull.getCustomSkull(PlayerHeadURLStorage.getInstance().getUrl(player.getUniqueId()))).name("§8•● §dProfil §8▎ §7Rechtsklick §8●•"));
    }

}
