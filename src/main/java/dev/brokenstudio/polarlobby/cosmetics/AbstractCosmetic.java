package dev.brokenstudio.polarlobby.cosmetics;

import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.entity.Player;

public abstract class AbstractCosmetic {

    private final String name;
    private final String description;
    private final CosmeticType cosmeticType;
    private final PolarItem item;
    private final Rarity rarity;


    public AbstractCosmetic(String name, String description, CosmeticType cosmeticType, PolarItem item, Rarity rarity) {
        this.name = name;
        this.description = description;
        this.cosmeticType = cosmeticType;
        this.item = item;
        this.rarity = rarity;
        this.item.name("§8•● " + this.rarity.color + this.name + " §8●•");
        this.item.lore("§7Kategorie§8: §f" + this.cosmeticType.name,"§7Seltenheit§8: " + this.rarity.displayname()," ", "§7" + this.description, " ");
    }

    public abstract void select(Player player);
    public abstract void use(Player player);
    public abstract void deselect(Player player);

    public String getName() {
        return name;
    }

    public CosmeticType getCosmeticType() {
        return cosmeticType;
    }

    public PolarItem getItem() {
        return item;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
