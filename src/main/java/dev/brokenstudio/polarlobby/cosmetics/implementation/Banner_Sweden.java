package dev.brokenstudio.polarlobby.cosmetics.implementation;

import dev.brokenstudio.polarlobby.cosmetics.AbstractCosmetic;
import dev.brokenstudio.polarlobby.cosmetics.CosmeticType;
import dev.brokenstudio.polarlobby.cosmetics.Rarity;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;

public class Banner_Sweden extends AbstractCosmetic {

    public Banner_Sweden(){
        super("Schweden","Show your flag.", CosmeticType.BANNER, new PolarItem(Material.BLUE_BANNER).addPattern(new Pattern(DyeColor.YELLOW, PatternType.HALF_HORIZONTAL))
                .addPattern(new Pattern(DyeColor.BLUE, PatternType.STRIPE_TOP)).addPattern(new Pattern(DyeColor.YELLOW, PatternType.STRIPE_CENTER)), Rarity.RARE);
    }

    @Override
    public void select(Player player) {
        //TODO REMOVE CURRENT HELMET USING COSMETIC
        player.getInventory().setHelmet(getItem());

    }

    @Override
    public void use(Player player) {

    }

    @Override
    public void deselect(Player player) {
        player.getInventory().setHelmet(null);
    }
}
