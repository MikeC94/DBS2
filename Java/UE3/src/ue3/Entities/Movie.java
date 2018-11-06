/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ue3.Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ue3.util.DBConnection;

/**
 *
 * @author Mike
 */
public class Movie {

    private int id;
    private String title;
    private int year;
    private char type;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public boolean importSQL(int movieID) {
        /*
        if (Properties.sqlc.connect()) {
            ResultSet rs = Properties.sqlc.executeQuery("SELECT * FROM Movie WHERE MovieID = " + movieID);
            try {
                if (rs.next()) {
                    this.setMovieID(rs.getInt("MovieID"));
                    this.setTitle(rs.getString("Title"));
                    this.setYear(rs.getInt("Year"));
                    //this.setType(rs.getString("Type"));        
                } else {
                    this.setMovieID(-1);
                    this.setTitle("Movie-ID unbekannt");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            Properties.sqlc.disconnect();
            return true;
        } else {
            return false;
        }
         */
        return false;
    }

    /**
     * Diese Methode fügt das aktuelle Movie-Objekt in SQL(Tablle Movie) ein.
     */
    public void insert() {

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String insert = "INSERT INTO Movie VALUES (";
            insert += "M_MovieID.nextval, ";
            insert += "'" + this.getTitle() + "', ";
            insert += this.getYear() + ", ";
            insert += "'" + this.getType() + "'";
            insert += ")";
            stmt.execute(insert);
            ResultSet rs = stmt.executeQuery("SELECT M_MovieID.currval FROM DUAL");
            if (rs.next()) {
                this.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Diese Methode gibt den Wert des Movie Objekts als String zurück.
     *
     * @return String Wert
     */
    @Override
    public String toString() {
        return "Kinofilm: " + this.getTitle() + " (" + this.getYear() + ")";
    }

}
