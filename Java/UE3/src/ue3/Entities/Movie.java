package ue3.Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ue3.util.DBConnection;

/**
 * Diese Klasse stellt einen Film da
 *
 * @author A-Team
 */
public class Movie {

    private int id;
    private String title;
    private int year;
    private char type;
    private List<Genre> genre;
    private List<MovieCharacter> character;

    /**
     * Diese Methode gibt die ID zurück
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Diese Methode setzt die ID des Films
     *
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Diese Methode gibt den Titel des Films zurück.
     *
     * @return Titel
     */
    public String getTitle() {
        return title;
    }

    /**
     * Diese Methode setzt den Titel des Films.
     *
     * @param title Titel des Films
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Diese Mehtode gibt das Jahr des Films zurück.
     *
     * @return Jahr des Films
     */
    public int getYear() {
        return year;
    }

    /**
     * Diese Mehtode setzt das Jahr des Films.
     *
     * @param year Jahr
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Diese Methode ermittelt den Typen des Films. C = Kinofilm
     *
     * @return Typ
     */
    public char getType() {
        return type;
    }

    /**
     * Diese Methode setzt den Typen des Films. C = Kinofilm
     *
     * @param type Typ
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * Diese Methode setzt die Genre.
     */
    public void importGenre() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM moviegenre mg JOIN genre g ON mg.genreid = g.genreid AND mg.movieid = " + this.getId());
            this.genre = new ArrayList<>();
            while (rs.next()) {
                Genre g = new Genre();
                g.setId(rs.getInt("genreid"));
                g.setGenre(rs.getString("genre"));
                this.genre.add(g);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode setzt die Filmcharaktere.
     */
    public void importCharacter() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MovieCharacter where movieid = " + this.getId());
            this.character = new ArrayList<>();
            while (rs.next()) {
                MovieCharacter mc = new MovieCharacter();
                mc.setId(rs.getInt("movcharid"));
                mc.setCharacter(rs.getString("character"));
                mc.setAlias(rs.getString("alias"));
                mc.setPos(rs.getInt("position"));
                mc.setPlayerId(rs.getInt("personid"));
                mc.setMovieId(rs.getInt("movieid"));
                mc.importPerson();
                this.character.add(mc);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
     * Diese Methode aktualisiert das aktuelle Movie Objekt in SQL
     */
    public void update() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE Movie SET ";
            update += "Title = '" + this.getTitle() + "', ";
            update += "Year = " + this.getYear() + ", ";
            update += "Type = '" + this.getType() + "' ";
            update += "WHERE MovieID = " + this.getId();
            stmt.execute(update);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode löscht das aktuelle Objekt und seine Referenzen in SQL.
     */
    public void delete() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String delete;
            delete = "DELETE FROM MovieGenre WHERE MovieID = " + this.getId();
            stmt.execute(delete);
            delete = "DELETE FROM MovieCharacter WHERE MovieID = " + this.getId();
            stmt.execute(delete);
            delete = "DELTE FROM Movie WHERE MovieID = " + this.getId();
            stmt.execute(delete);
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

        String ret;

        // Film
        ret = "Kinofilm: " + this.getTitle() + " (" + this.getYear() + ")";

        // Genre 
        ret += "\r\n";
        ret += "Genre:";
        for (int i = 0; i < this.genre.size(); i++) {
            if (i != 0) {
                ret += " | ";
            } else {
                ret += " ";
            }
            ret += this.genre.get(i);
        }

        // Filmcharaktere
        ret += "\r\n";
        ret += "Darsteller:";
        for (int i = 0; i < this.character.size(); i++) {
            ret += "\r\n";
            ret += this.character.get(i);
        }

        return ret;
    }

}
