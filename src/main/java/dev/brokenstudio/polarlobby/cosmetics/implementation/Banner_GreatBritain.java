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

public class Banner_GreatBritain extends AbstractCosmetic {

    public Banner_GreatBritain(){
        super("Großbritannien","Show your flag.", CosmeticType.BANNER, new PolarItem(Material.BLUE_BANNER).addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_CENTER))
                .addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE)).addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNRIGHT)).addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_DOWNLEFT)).addPattern(new Pattern(DyeColor.RED, PatternType.CROSS)).addPattern(new Pattern(DyeColor.RED, PatternType.STRAIGHT_CROSS)), Rarity.RARE);
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
