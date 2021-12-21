package dev.brokenstudio.polarlobby;

import dev.brokenstudio.polarlobby.database.DatabaseHandler;
import dev.brokenstudio.polarlobby.utils.Locations;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {

    private static Lobby instance;
    private DatabaseHandler databaseHandler;
    private Locations locations;

    @Override
    public void onEnable() {
        instance = this;
        databaseHandler = new DatabaseHandler();
        locations = Locations.fromJson("");
        register();
    }

    @Override
    public void onDisable() {

    }

    private void register(){

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
}
