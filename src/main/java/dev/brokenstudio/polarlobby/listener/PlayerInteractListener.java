package dev.brokenstudio.polarlobby.listener;

import dev.brokenstudio.polarlobby.Lobby;
import org.bukkit.Bukkit;
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
        event.getItem().getItemMeta().getDisplayName();


        Player player = event.getPlayer();
        Material type = event.getMaterial();
        Bukkit.broadcastMessage("" + type);
        if(type == Material.PLAYER_HEAD){
            String name = event.getItem().getItemMeta().getDisplayName();
            Bukkit.broadcastMessage(name);
            if(name.equalsIgnoreCase("§8•● §dNavigator §8▎ §7Rechtsklick §8●•")){
                Lobby.getInstance().getInventoryHandler().getNavigator().open(player);
            }else if(name.equalsIgnoreCase("§8•● §dProfil §8▎ §7Rechtsklick §8●•")){

            }
        }else if(type == Material.TOTEM_OF_UNDYING){

        }else if(type == Material.FURNACE_MINECART){

        }

    }

}

