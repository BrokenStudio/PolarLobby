package dev.brokenstudio.polarlobby.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getItem() == null)return;
        if(event.getItem().getType() == Material.AIR )return;
        if(event.getItem().getItemMeta() == null)return;
        if(event.getItem().getItemMeta().getDisplayName() == null)return;

        

        Player player = event.getPlayer();
        Material type = event.getMaterial();

        if(type == Material.LEGACY_SKULL_ITEM){
            String name = event.getItem().getItemMeta().getDisplayName();
            if(name.equalsIgnoreCase("§8•● §dNavigator §8▎ §7Rechtsklick §8●•")){

            }else if(name.equalsIgnoreCase("§8•● §dProfil §8▎ §7Rechtsklick §8●•")){

            }
        }else if(type == Material.TOTEM_OF_UNDYING){

        }else if(type == Material.FURNACE_MINECART){

        }

    }

}

