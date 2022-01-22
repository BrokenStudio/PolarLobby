package dev.brokenstudio.polarlobby.cosmetics.implementation;

import dev.brokenstudio.polarlobby.byteplayer.IBytePlayer;
import dev.brokenstudio.polarlobby.cosmetics.AbstractCosmetic;
import dev.brokenstudio.polarlobby.cosmetics.CosmeticType;
import dev.brokenstudio.polarlobby.cosmetics.NoneCosmetic;
import dev.brokenstudio.polarlobby.cosmetics.Rarity;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Cloth_Green extends AbstractCosmetic {

    public Cloth_Green() {
        super("Grüne Kleidung", "Grün ist toll!", CosmeticType.CLOTH, new PolarItem(Material.LEATHER_CHESTPLATE).color(Color.GREEN), Rarity.RARE);
    }

    @Override
    public void select(Player player) {
        IBytePlayer iBytePlayer = IBytePlayer.handler().getIBytePlayer(player.getUniqueId());
        iBytePlayer.getCloth().deselect(player);
        iBytePlayer.setCloth(this);
        player.getInventory().setChestplate(new PolarItem(Material.LEATHER_CHESTPLATE).color(Color.GREEN).name("§8•● " + getRarity().color() + getName() + " §8●•"));
        player.getInventory().setLeggings(new PolarItem(Material.LEATHER_LEGGINGS).color(Color.GREEN).name("§8•● " + getRarity().color() + getName() + " §8●•"));
        player.getInventory().setBoots(new PolarItem(Material.LEATHER_BOOTS).color(Color.GREEN).name("§8•● " + getRarity().color() + getName() + " §8●•"));
    }

    @Override
    public void use(Player player) {

    }

    @Override
    public void deselect(Player player) {
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        IBytePlayer.handler().getIBytePlayer(player.getUniqueId()).setCloth(NoneCosmetic.getNone());
    }
}
