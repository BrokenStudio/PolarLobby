package dev.brokenstudio.polarlobby.utils;

import org.bukkit.inventory.ItemStack;

public class SkullStorage {

    private static SkullStorage storage;

    public ItemStack language;
    public ItemStack friends;
    public ItemStack settings;
    public ItemStack party;
    public ItemStack clan;
    public ItemStack stats;
    public ItemStack badges;

    {
        language = new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/5e7899b4806858697e283f084d9173fe487886453774626b24bd8cfecc77b3f"))
                .name("§8•● §dSprache §8●");
        friends = new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/1b2869abdfc6a7b0c025ce29ff81ae11fdd3899b5e63ea8915e8ff315b59b0"))
                .name("§8•● §dFreunde §8●•");
        settings = new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/30becf2754bb793fc2f73f604cfd666dcc76e809618cbf397886846542bda785"))
                .name("§8•● §dEinstellungen §8●•");
        party =  new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/7c5fd3bd1f3b4be7aa886a6fb06cd916775c4d9e7a7283ac99cae171e971f1"))
                .name("§8•● §dParty §8●•");
        clan =  new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/b6193c2830c52af546fe6d18000a4f27e48d22f2496dd42073b9f209c955fdb5"))
                .name("§8•● §dClan §8●•");
        stats = new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/a1d1cf289165fbb112d8921d47708e49fb70925739b1cbd1798daff422806e8a"))
                .name("§8•● §dStatistiken §8●•");
        badges = new PolarItem(Skull.getCustomSkull("http://textures.minecraft.net/texture/db3c3485cbd1e1162a7d9ec9db8b9c7ee4a3f983ec770c6aaafaf477f50b5"))
                .name("§8•● §dAbzeichen §8●•");

    }

    public static SkullStorage getStorage() {
        if(storage == null)
            storage = new SkullStorage();
        return storage;
    }
}
