package dev.brokenstudio.polarlobby.database;

public class DatabaseHandler {

    private final MariaDBHandler mariaDBHandler;

    {
        mariaDBHandler = new MariaDBHandler("localhost","lobby","lobby","startstart32");
    }

    public MariaDBHandler getMariaDBHandler() {
        return mariaDBHandler;
    }
}
