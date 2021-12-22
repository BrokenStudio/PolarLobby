package dev.brokenstudio.polarlobby;

import dev.brokenstudio.polarlobby.database.DatabaseHandler;
import dev.brokenstudio.polarlobby.listener.PlayerConnectionListener;
import dev.brokenstudio.polarlobby.player.PlayerUtils;
import dev.brokenstudio.polarlobby.utils.Locations;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {

    private static Lobby instance;
    private DatabaseHandler databaseHandler;
    private Locations locations;
    private PlayerUtils playerUtils;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        databaseHandler = new DatabaseHandler(getConfig());
        locations = Locations.fromJson("");
        playerUtils = new PlayerUtils();
        register();
    }

    @Override
    public void onDisable() {

    }

    private void register(){
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
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
}
