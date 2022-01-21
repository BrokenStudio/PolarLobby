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

public class Banner_Thailand extends AbstractCosmetic {

    public Banner_Thailand(){
        super("Thailand","Show your flag.", CosmeticType.BANNER, new PolarItem(Material.WHITE_BANNER).addPattern(new Pattern(DyeColor.BLUE, PatternType.STRIPE_CENTER))
                .addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_SMALL)).addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT)).addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_LEFT)), Rarity.RARE);
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
