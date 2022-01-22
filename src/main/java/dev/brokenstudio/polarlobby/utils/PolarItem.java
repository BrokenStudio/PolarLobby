package dev.brokenstudio.polarlobby.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.Dye;

import java.util.Arrays;

public class PolarItem extends ItemStack {

    public PolarItem(Material material){
        super(material);
    }

    public PolarItem(ItemStack itemStack){
        super(itemStack);
    }

    public PolarItem amount(int amount){
        setAmount(amount);
        return this;
    }

    public PolarItem name(String name){
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(name);
        setItemMeta(meta);
        return this;
    }

    public PolarItem configName(String name){
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        setItemMeta(meta);
        return this;
    }

    public PolarItem lore(String... lore){
        ItemMeta meta = getItemMeta();
        meta.setLore(Arrays.asList(lore));
        setItemMeta(meta);
        return this;
    }

    public PolarItem durability(int durability){
        setDurability((short) durability);
        return this;
    }

    public PolarItem enchant(Enchantment enchantment){
        addUnsafeEnchantment(enchantment, 1);
        return this;
    }

    public PolarItem enchant(Enchantment enchantment, int level){
        addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public PolarItem glow(){
        addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        setItemMeta(meta);
        return this;
    }

    public PolarItem glow(boolean glow){
        if(glow){
            glow();
        }
        return this;
    }

    public PolarItem addItemFlags(ItemFlag... flags){
        ItemMeta meta = getItemMeta();
        meta.addItemFlags(flags);
        setItemMeta(meta);
        return this;
    }

    public PolarItem unbreakable(){
        ItemMeta meta = getItemMeta();
        meta.setUnbreakable(true);
        setItemMeta(meta);
        return this;
    }

    public PolarItem hideAttributes(){
        ItemMeta meta = getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        setItemMeta(meta);
        return this;
    }

    public PolarItem color(Color color){
        if (getType() == Material.LEATHER_BOOTS || getType() == Material.LEATHER_CHESTPLATE
                || getType() == Material.LEATHER_HELMET || getType() == Material.LEATHER_LEGGINGS) {
            LeatherArmorMeta meta = (LeatherArmorMeta) getItemMeta();
            meta.setColor(color);
            setItemMeta(meta);
            return this;
        }
        if (getType() == Material.POTION) {
            PotionMeta potionMeta = (PotionMeta) getItemMeta();
            potionMeta.setColor(color);
            setItemMeta(potionMeta);
            return this;
        } else {
            throw new IllegalArgumentException("Color only applicable for leather armor and potions!");
        }
    }

    public PolarItem addPattern(Pattern pattern){
        if(!getType().toString().contains("BANNER")){
            throw new IllegalArgumentException("Pattern only applicable to banner!");
        }

        BannerMeta meta = (BannerMeta) getItemMeta();
        meta.addPattern(pattern);
        setItemMeta(meta);
        return this;
    }

}
