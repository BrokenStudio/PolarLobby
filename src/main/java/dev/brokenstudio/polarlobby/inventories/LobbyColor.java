package dev.brokenstudio.polarlobby.inventories;

import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum LobbyColor {

    RED(new PolarItem(Material.RED_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§cRot"),
    ORANGE(new PolarItem(Material.ORANGE_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§6Orange"),
    YELLOW(new PolarItem(Material.YELLOW_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§eGelb"),
    PINK(new PolarItem(Material.PINK_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§dPink"),
    MAGENTA(new PolarItem(Material.MAGENTA_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§dMagenta"),
    PURPLE(new PolarItem(Material.PURPLE_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§5Lila"),
    BROWN(new PolarItem(Material.BROWN_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§8Braun"),
    LIGHT_GREEN(new PolarItem(Material.LIME_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§aGrün"),
    DARK_GREEN(new PolarItem(Material.GREEN_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§2Dunkelgrün"),
    CYAN(new PolarItem(Material.CYAN_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§bTürkis"),
    LIGHT_BLUE(new PolarItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§9Blau"),
    DARK_BLUE(new PolarItem(Material.BLUE_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§1Dunkelblau"),
    WHITE(new PolarItem(Material.WHITE_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§fWeiß"),
    LIGHT_GRAY(new PolarItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§7Grau"),
    DARK_GRAY(new PolarItem(Material.GRAY_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§8Dunkelgrau"),
    BLACK(new PolarItem(Material.BLACK_STAINED_GLASS_PANE).name(" ").hideAttributes(),"§8Schwarz");

    private ItemStack itemStack;
    private String displayname;

    LobbyColor(ItemStack itemStack, String diplayname){
        this.itemStack = itemStack;
        this.displayname = diplayname;
    }

    public ItemStack glass() {
        return itemStack;
    }

    public String getDisplayname() {
        return displayname;
    }
}
