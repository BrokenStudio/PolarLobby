package dev.brokenstudio.polarlobby.listener;

import dev.brokenstudio.polarlobby.Lobby;
import dev.brokenstudio.polarlobby.database.building.BuildHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private BuildHandler buildHandler;

    {
        buildHandler = BuildHandler.getInstance();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(this.buildHandler.isBuilding(event.getPlayer()))
            return;

        if(event.getAction() == Action.PHYSICAL){
            event.setCancelled(true);
            return;
        }

        if(!event.getAction().toString().contains("RIGHT"))return;

        if(event.getItem() == null)return;
        if(event.getItem().getType() == Material.AIR )return;
        if(event.getItem().getItemMeta() == null)return;
        event.getItem().getItemMeta().getDisplayName();



        Player player = event.getPlayer();
        Material type = event.getMaterial();
        if(type == Material.PLAYER_HEAD){
            String name = event.getItem().getItemMeta().getDisplayName();
            if(name.equalsIgnoreCase("§8•● §dNavigator §8▎ §7Rechtsklick §8●•")){
                Lobby.getInstance().getInventoryHandler().getNavigator().open(player);
            }else if(name.equalsIgnoreCase("§8•● §dProfil §8▎ §7Rechtsklick §8●•")){

            }
        }else if(type == Material.TOTEM_OF_UNDYING){
            Lobby.getInstance().getInventoryHandler().getHider().open(player);
        }else if(type == Material.FURNACE_MINECART){

        }

    }

}

