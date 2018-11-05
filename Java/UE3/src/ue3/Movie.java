/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ue3;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mike
 */
public class Movie {

    private int movieID;
    private String title;
    private int year;
    private char type;

    public Movie() {
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
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

    }

    public void insert() {
        if (Properties.sqlc.connect()) {
            String insert = "INSERT INTO Movie VALUES (";
            insert += "M_MovieID.nextval, ";
            insert += "'" + this.getTitle() + "', ";
            insert += this.getYear() + ", ";
            insert += "'" + this.getType() + "'";
            insert += ")";
            if (Properties.sqlc.executeInsert(insert)) {
                ResultSet rs = Properties.sqlc.executeQuery("SELECT M_MovieID.currval FROM DUAL");
                try {
                    if (rs.next()) {
                        this.setMovieID(rs.getInt(1));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            Properties.sqlc.disconnect();
        }
    }

    @Override
    // Kinofilm: Star Wars (1977)
    public String toString() {
        return "Kinofilm: " + this.getTitle() + " (" + this.getYear() + ")";
    }

}
