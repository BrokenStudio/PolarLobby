package dev.brokenstudio.polarlobby.cosmetics;

import org.bukkit.entity.Player;

public class NoneCosmetic extends AbstractCosmetic {

    private static NoneCosmetic instnance;

    public NoneCosmetic() {
        super("None","",null, null, null);
    }

    @Override
    public void select(Player player) {

    }

    @Override
    public void use(Player player) {

    }

    @Override
    public void deselect(Player player) {

    }

    public static NoneCosmetic getNone() {
        if(instnance == null) instnance = new NoneCosmetic();
        return instnance;
    }
}
