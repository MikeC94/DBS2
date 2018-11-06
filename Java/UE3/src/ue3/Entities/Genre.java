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
public class Genre {
    
    private int id;
    private String genre;
    
    public Genre() {
    }
    
    public int getId() {
        return id;
    }
    
    private void setId(int id) {
        this.id = id;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    /**
     * Diese Methode fügt das aktuelle Genre-Objekt in SQL(Tablle Movie) ein.
     */
    public void insert() {
        
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String insert = "INSERT INTO Genre VALUES(";
            insert += "G_GenreID.nextval, ";
            insert += "'" + this.getGenre() + "'";
            insert += ")";
            stmt.execute(insert);
            ResultSet rs = stmt.executeQuery("SELECT G_GenreID.currval FROM DUAL");
            if (rs.next()) {
                this.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * Diese Methode gibt den Wert des Genre Objekts als String zurück.
     *
     * @return String Wert
     */
    @Override
    public String toString() {
        return this.getGenre();
    }
    
}
