package ue3.Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ue3.util.DBConnection;

/**
 * Diese Klasse stellt ein Genre da
 *
 * @author A-Team
 */
public class Genre {

    private int id;
    private String genre;

    /**
     * Diese Methode gibt die ID zurück
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Diese Methode setzte die ID
     *
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Diese Methode gibt den Namen des Gerne zurück
     *
     * @return Name
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Diese Methode setzt den Namen des Gernre
     *
     * @param genre Name
     */
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
     * Diese Methode aktualisiert das aktuelle Genre-Objekt in SQL.
     */
    public void update() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPADATE Genre SET Genre = ";
            update += "'" + this.getGenre() + "'";
            update += "WHERE GenreID = " + this.getId();
            stmt.execute(update);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode löscht das aktuelle Genre-Objekt und seine Referenzen in
     * SQL.
     */
    public void delete() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String delete;
            delete = "DELETE FROM MovieGenre WHERE GenreID = " + this.getId();
            stmt.execute(delete);
            delete = "DELETE FROM Genre WHERE GenreID = " + this.getId();
            stmt.execute(delete);
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
