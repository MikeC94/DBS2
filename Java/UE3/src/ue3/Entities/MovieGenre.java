package ue3.Entities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import ue3.util.DBConnection;

/**
 * Diese Klasse stellt die Assoziation zwischen Movie und Gerne da.
 *
 * @author A-Team
 */
public class MovieGenre {

    private int id;
    private int genreId;
    private int movieId;

    /**
     * Diese Methode gibt die Id zurück
     *
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Diese Methode setzt die Id
     *
     * @param id
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Diese Methode gibt die Gernre Id zurück.
     *
     * @return Genre Id
     */
    public int getGenreId() {
        return genreId;
    }

    /**
     * Diese Methode setzt den Genre Id
     *
     * @param genreId Genre Id
     */
    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    /**
     * Diese Methode gibt die Movie Id zurück.
     *
     * @return Movie Id
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Diese Methode setzt die Movie Id.
     *
     * @param movieId Movie Id
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Diese Methode fügt das aktuelle MovieGenre Objekt in SQL ein.
     */
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

    /**
     * Diese Methode löscht das aktuelle MovieGenre Objekt in SQL.
     */
    public void delete() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String delete = "DELETE FROM MovieGenre WHERE ";
            delete += " GenreID = " + this.getGenreId();
            delete += " AND";
            delete += " MovieID = " + this.getMovieId();
            stmt.execute(delete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
