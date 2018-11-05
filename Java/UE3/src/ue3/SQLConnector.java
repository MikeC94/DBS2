package ue3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mike
 */
public class SQLConnector {

    private String conString;
    private Connection con;
    private Statement stmt;
    private boolean connect;

    public SQLConnector() {
        this.conString = "jdbc:oracle:thin:@" + Properties.server
                + ":" + Properties.port + ":" + Properties.database;
    }

    public boolean connect() {
        try {
            con = DriverManager.getConnection(
                    this.conString,
                    Properties.username,
                    Properties.password);
            connect = true;
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connect = false;
            return false;
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            connect = false;
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            stmt = con.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public boolean executeInsert(String insert) {
        try {
            stmt = con.createStatement();
            stmt.execute(insert);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
