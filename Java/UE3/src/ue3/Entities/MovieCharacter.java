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
public class MovieCharacter {

    private int id;
    private String character;
    private String alias;
    private int pos;
    private int playerId;
    private int movieId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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
            String insert = "INSERT INTO MovieCharacter VALUES(";
            insert += "MC_MovCharID.nextval, ";
            insert += "'" + this.getCharacter() + "', ";
            insert += "'" + this.alias + "'" + ", ";
            insert += this.pos + ", ";
            insert += this.getPlayerId() + ", ";
            insert += this.getMovieId();
            insert += ")";
            stmt.execute(insert);
            ResultSet rs = stmt.executeQuery("SELECT MC_MovCharID.currval FROM DUAL");
            if (rs.next()) {
                this.setId(rs.getInt(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
