package dev.brokenstudio.polarlobby.database.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MariaDBConnection {

    private final Connection connection;
    private final ArrayList<Statement> queries;
    private final ArrayList<ResultSet> results;

    public MariaDBConnection(Connection connection) {
        this.connection = connection;
        this.queries = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public MariaDBConnection update(String qry){
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(qry);
            queries.add(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ResultSet getResult(String qry){
        ResultSet rs = null;
        try {
            Statement statement = this.connection.createStatement();
            rs = statement.executeQuery(qry);
            results.add(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void close(){
            results.forEach(cr -> {
                try {
                    cr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        queries.forEach(cr -> {
            try {
                cr.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
