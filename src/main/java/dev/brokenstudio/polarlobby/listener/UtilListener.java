package dev.brokenstudio.polarlobby.listener;

import dev.brokenstudio.polarlobby.database.building.BuildHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class UtilListener implements Listener {

    private BuildHandler buildHandler;

    public UtilListener() {
        this.buildHandler = BuildHandler.getInstance();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player)
            event.setCancelled(true);
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if(this.buildHandler.isBuilding(event.getPlayer()))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(this.buildHandler.isBuilding(event.getPlayer()))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(this.buildHandler.isBuilding(event.getPlayer()))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(this.buildHandler.isBuilding((Player) event.getWhoClicked()))
            return;
        if(event.getView().getBottomInventory() == event.getWhoClicked().getInventory() && event.getSlot() < 9){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHandItemSwap(PlayerSwapHandItemsEvent event){
        if(this.buildHandler.isBuilding(event.getPlayer()))
            return;
        event.setCancelled(true);
    }

}
