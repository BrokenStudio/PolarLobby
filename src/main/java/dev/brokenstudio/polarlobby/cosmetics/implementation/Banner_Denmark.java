package dev.brokenstudio.polarlobby.cosmetics.implementation;

import dev.brokenstudio.polarlobby.byteplayer.IBytePlayer;
import dev.brokenstudio.polarlobby.cosmetics.AbstractCosmetic;
import dev.brokenstudio.polarlobby.cosmetics.CosmeticType;
import dev.brokenstudio.polarlobby.cosmetics.NoneCosmetic;
import dev.brokenstudio.polarlobby.cosmetics.Rarity;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;

public class Banner_Denmark extends AbstractCosmetic {

    public Banner_Denmark(){
        super("Dänemark","Show your flag.", CosmeticType.BANNER, new PolarItem(Material.RED_BANNER).addPattern(new Pattern(DyeColor.WHITE, PatternType.HALF_HORIZONTAL))
                .addPattern(new Pattern(DyeColor.RED, PatternType.STRIPE_TOP)).addPattern(new Pattern(DyeColor.WHITE, PatternType.STRIPE_CENTER)), Rarity.RARE);
    }

    @Override
    public void select(Player player) {
        IBytePlayer iBytePlayer = IBytePlayer.handler().getIBytePlayer(player.getUniqueId());
        iBytePlayer.getHelmet().deselect(player);
        iBytePlayer.setHelmet(this);
        player.getInventory().setHelmet(getItem());

    }

    @Override
    public void use(Player player) {

    }

    @Override
    public void deselect(Player player) {
        IBytePlayer.handler().getIBytePlayer(player.getUniqueId()).setHelmet(NoneCosmetic.getNone());
        player.getInventory().setHelmet(null);
    }
}
