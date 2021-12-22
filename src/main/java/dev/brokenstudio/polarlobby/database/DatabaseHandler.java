package dev.brokenstudio.polarlobby.database;

import org.bukkit.configuration.file.FileConfiguration;

public class DatabaseHandler {

    private final MariaDBHandler mariaDBHandler;

    public DatabaseHandler(FileConfiguration config){
        mariaDBHandler = new MariaDBHandler(config.getString("MariaDB.host"),
                config.getString("MariaDB.database"),
                config.getString("MariaDB.username"),
                config.getString("MariaDB.password"));
    }

    public MariaDBHandler getMariaDBHandler() {
        return mariaDBHandler;
    }
}
