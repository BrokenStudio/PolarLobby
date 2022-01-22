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

public class Particel_Builder extends AbstractCosmetic {

    private LinkedList<Item> spawned;

    public Particle_Builder() {
        super("Architekten Spur", "Du baust gerne Gebäude, ich auch!", CosmeticType.PARTICLES, new PolarItem(Material.CRAFTING_TABLE), Rarity.ULTIMATE);
        spawned = new LinkedList<>();
    }

    @Override
    public void select(Player player) {
        IBytePlayer.handler().getIBytePlayer(player.getUniqueId()).setParticles(this);
    }

    @Override
    public void use(Player player) {
        Location location = player.getLocation().subtract(0,0.2,0);
        Item one = location.getWorld().dropItem(location,new ItemStack(Material.CRAFTING_TABLE));
        Item two = location.getWorld().dropItem(location,new ItemStack(Material.FURNACE));
        Item three = location.getWorld().dropItem(location,new ItemStack(Material.GRASS_BLOCK));
        Item four = location.getWorld().dropItem(location,new ItemStack(Material.COBBLESTONE));

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
