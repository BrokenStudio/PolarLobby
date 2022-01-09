package dev.brokenstudio.polarlobby;

import dev.brokenstudio.polarinvs.InventoryManager;
import dev.brokenstudio.polarlobby.commands.SetCommand;
import dev.brokenstudio.polarlobby.database.DatabaseHandler;
import dev.brokenstudio.polarlobby.database.sql.MariaDBConnection;
import dev.brokenstudio.polarlobby.inventories.InventoryHandler;
import dev.brokenstudio.polarlobby.inventories.opener.SpecialInventoryOpener;
import dev.brokenstudio.polarlobby.listener.PlayerConnectionListener;
import dev.brokenstudio.polarlobby.listener.PlayerInteractListener;
import dev.brokenstudio.polarlobby.listener.UtilListener;
import dev.brokenstudio.polarlobby.player.PlayerUtils;
import dev.brokenstudio.polarlobby.utils.Locations;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class Lobby extends JavaPlugin {

    public static String PREFIX = "§8•● §5Byte§dFox §8●• §7";
    private static Lobby instance;
    private DatabaseHandler databaseHandler;
    private Locations locations;
    private PlayerUtils playerUtils;
    private InventoryHandler inventoryHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        databaseHandler = new DatabaseHandler(getConfig());
        MariaDBConnection connection = databaseHandler.getMariaDBHandler().getConnection();
        connection.update("CREATE TABLE IF NOT EXISTS `lobby_data` (`key` VARCHAR(100),`value` LONGTEXT);");
        try {
            ResultSet rs = connection.getResult("SELECT `value` FROM `lobby_data` WHERE `key`='locations';");
            if(rs.next())
                locations = Locations.fromJson(rs.getString("value"));
            else
                locations = new Locations();
        }catch (SQLException ex){
            locations = new Locations();
        }
        CompletableFuture.runAsync(() ->{
           MariaDBConnection asyncConnection = databaseHandler.getMariaDBHandler().getConnection();
           asyncConnection.update("INSERT INTO `lobby_data` (`key`,`value`) VALUES ('locations','empty');")
                   .close();
        });
        connection.close();
        playerUtils = new PlayerUtils();
        InventoryManager.getDefaultManager().registerOpener(new SpecialInventoryOpener());
        inventoryHandler = new InventoryHandler();
        register();
    }

    @Override
    public void onDisable() {
        MariaDBConnection connection = databaseHandler.getMariaDBHandler().getConnection();
        connection.update("UPDATE `lobby_data` SET `value`='"+locations.toJson()+"' WHERE `key`='locations';").close();
    }

    private void register(){
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new UtilListener(), this);

        getCommand("set").setExecutor(new SetCommand());
    }

    public static Lobby getInstance() {
        return instance;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public Locations getLocations() {
        return locations;
    }

    public PlayerUtils getPlayerUtils() {
        return playerUtils;
    }

    public static String getPrefix() {
        return PREFIX;
    }

    public InventoryHandler getInventoryHandler() {
        return inventoryHandler;
    }
}
