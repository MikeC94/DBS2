/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ue3.Entities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import ue3.util.DBConnection;

/**
 *
 * @author Mike
 */
public class MovieGenre {

    private int id;
    private int genreId;
    private int movieId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void insert() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String insert = "INSERT INTO MovieGenre VALUES (";
            insert += this.getGenreId() + ", ";
            insert += this.getMovieId();
            insert += ")";
            stmt.execute(insert);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
