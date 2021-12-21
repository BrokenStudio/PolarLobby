package dev.brokenstudio.polarlobby.database;

import dev.brokenstudio.polarlobby.database.sql.MariaDBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBHandler {

    private final String host, user, database, password;

    public MariaDBHandler(String host, String database, String user, String password) {
        this.host = host;
        this.user = user;
        this.database = database;
        this.password = password;
    }

    public MariaDBConnection getConnection(){
        try {
            return new MariaDBConnection(DriverManager.getConnection("jdbc:mariadb://"+this.host+":3306/"+this.database+"?user="+this.user+"&password="+this.password+"&useSSL=false"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MariaDBConnection getConnection(String hst, String db, String usr, String pwd){
        try {
            return new MariaDBConnection(DriverManager.getConnection("jdbc:mariadb://"+this.host+":3306/"+this.database+"?user="+this.user+"&password="+this.password+"&useSSL=false"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
