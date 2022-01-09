package dev.brokenstudio.polarlobby.database.building;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;

public class BuildHandler {

    private final static LinkedList<Player> building = new LinkedList<>();
    private final static HashMap<Player, ItemStack[]> inventories = new HashMap<>();

    private static BuildHandler instance;

    public void setBuilding(Player player, boolean building){
        if(building){
            inventories.put(player, player.getInventory().getContents().clone());
            player.getInventory().clear();
            BuildHandler.building.add(player);
            player.setGameMode(GameMode.CREATIVE);
        }else{
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);
            BuildHandler.building.remove(player);
            player.getInventory().setContents(inventories.get(player));
        }
    }

    public boolean isBuilding(Player player){
        return building.contains(player);
    }

    public static BuildHandler getInstance() {
        if (instance == null)
            instance = new BuildHandler();
        return instance;
    }
}
