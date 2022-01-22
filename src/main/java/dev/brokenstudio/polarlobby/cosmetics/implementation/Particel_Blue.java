package dev.brokenstudio.polarlobby.cosmetics.implementation;

import dev.brokenstudio.polarlobby.byteplayer.IBytePlayer;
import dev.brokenstudio.polarlobby.cosmetics.AbstractCosmetic;
import dev.brokenstudio.polarlobby.cosmetics.CosmeticType;
import dev.brokenstudio.polarlobby.cosmetics.NoneCosmetic;
import dev.brokenstudio.polarlobby.cosmetics.Rarity;
import dev.brokenstudio.polarlobby.utils.PolarItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;

public class Particel_Blue extends AbstractCosmetic {

    private LinkedList<Item> spawned;

    public Particle_Blue() {
        super("Blaue Spur", "Du magst Blau, ich auch!", CosmeticType.PARTICLES, new PolarItem(Material.BLUE_DYE), Rarity.EPIC);
        spawned = new LinkedList<>();
    }

    @Override
    public void select(Player player) {
        IBytePlayer.handler().getIBytePlayer(player.getUniqueId()).setParticles(this);
    }

    @Override
    public void use(Player player) {
        Location location = player.getLocation().subtract(0,0.2,0);
        Item one = location.getWorld().dropItem(location,new ItemStack(Material.BLUE_DYE));
        Item two = location.getWorld().dropItem(location,new ItemStack(Material.BLUE_STAINED_GLASS));
        Item three = location.getWorld().dropItem(location,new ItemStack(Material.BLUE_WOOL));
        Item four = location.getWorld().dropItem(location,new ItemStack(Material.BLUE_CONCRETE));

        spawned.add(one);
        spawned.add(three);
        spawned.add(two);
        spawned.add(four);

        //TODO IMPLEMENT DESPAWN TASK

    }

    @Override
    public void deselect(Player player) {
        IBytePlayer.handler().getIBytePlayer(player.getUniqueId()).setParticles(NoneCosmetic.getNone());
    }

}
