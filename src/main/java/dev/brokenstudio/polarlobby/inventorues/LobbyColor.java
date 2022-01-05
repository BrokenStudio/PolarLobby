package dev.brokenstudio.polarlobby.inventorues;

import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum LobbyColor {

    RED(new PolarItem(Material.RED_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    ORANGE(new PolarItem(Material.ORANGE_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    YELLOW(new PolarItem(Material.YELLOW_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    PINK(new PolarItem(Material.PINK_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    MAGENTA(new PolarItem(Material.MAGENTA_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    PURPLE(new PolarItem(Material.PURPLE_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    BROWN(new PolarItem(Material.BROWN_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    LIGHT_GREEN(new PolarItem(Material.LIME_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    DARK_GREEN(new PolarItem(Material.GREEN_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    CYAN(new PolarItem(Material.CYAN_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    LIGHT_BLUE(new PolarItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    DARK_BLUE(new PolarItem(Material.BLUE_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    WHITE(new PolarItem(Material.WHITE_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    LIGHT_GRAY(new PolarItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    DARK_GRAY(new PolarItem(Material.GRAY_STAINED_GLASS_PANE).name(" ").hideAttributes()),
    BLACK(new PolarItem(Material.BLACK_STAINED_GLASS_PANE).name(" ").hideAttributes());

    private ItemStack itemStack;

    LobbyColor(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    public ItemStack glass() {
        return itemStack;
    }
}
