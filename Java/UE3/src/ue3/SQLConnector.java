package ue3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mike
 */
public class SQLConnector {

    private String conString;
    private String user;
    private String password;
    private Connection con;
    private Statement stmt;

    public SQLConnector(
            String server,
            String port,
            String database,
            String user,
            String password) {
        this.conString = "jdbc:oracle:thin:@" + server
                + ":" + port + ":" + database;
        this.user = user;
        this.password = password;

    }

    public boolean connect() {
        try {
            con = DriverManager.getConnection(
                    this.conString,
                    this.user,
                    this.password);
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
}
