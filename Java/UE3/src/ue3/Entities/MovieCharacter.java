package ue3.Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ue3.util.DBConnection;

/**
 * Diese Klasse stellt einen Filmcharakter da
 *
 * @author A-Team
 */
public class MovieCharacter {

    private int id;
    private String character;
    private String alias;
    private int pos;
    private int playerId;
    private int movieId;
    private Person person;

    /**
     * Diese Methode gibt die ID zurück.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Diese Methode setzt den ID
     *
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Diese Methode gibt den Namen des Filmcharakters zurück.
     *
     * @return Namen des Filmcharakters
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Diese Methode setzt den Namen des Filmcharakters.
     *
     * @param character Name
     */
    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * Diese Methode gibt den Alias zurück.
     *
     * @return Alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Diese Methode setzt den Alias.
     *
     * @param alias Alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Diese Methode gibt die Position zurück.
     *
     * @return Position
     */
    public int getPos() {
        return pos;
    }

    /**
     * Diese Methode setzt die Position.
     *
     * @param pos Position
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * Diese Methode gibt die Person ID zurück.
     *
     * @return Person ID
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Diese Methode setzt die Person ID.
     *
     * @param playerId Person ID
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Diese Methode gibt die Movie ID zurück.
     *
     * @return Movie ID
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Diese Methode setzt den Movie ID.
     *
     * @param movieId Movie ID
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * Diese Methode gibt die Person zurück.
     *
     * @return Person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Diese Methode import die zugehörige Person aus SQL
     */
    public void importPerson() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Person p WHERE personid = " + this.getPlayerId());
            if (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt("personid"));
                p.setName(rs.getString("name"));
                if (!rs.getString("sex").equals("")) {
                    p.setSex(rs.getString("sex").charAt(0));
                }
                this.person = p;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode fügt den aktuellen Filmcharakter in SQL ein.
     */
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

    /**
     * Diese Methode akutalisiert den aktuellen Film Charakter in SQL.
     */
    public void update() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE MovieCharacter SET ";
            update += "Character = '" + this.getCharacter() + "', ";
            update += "Alias = '" + this.getAlias() + "', ";
            update += "Position = " + this.getAlias() + ", ";
            update += "WHERE MovCharID = " + this.getId();
            stmt.execute(update);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode löscht das aktuelle Film Charakter Objekt in SQL.
     */
    public void delete() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String delete = "DELTE FROM MovieCharacter WHERE MovCharID = " + this.getId();
            stmt.execute(delete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode gibt den Wert des Moviecharakters als String zurück.
     *
     * @return Wert als String
     */
    @Override
    public String toString() {
        String ret;
        ret = this.getCharacter();
        if (this.getPerson() != null) {
            ret += ": " + this.getPerson();
        }
        return ret;
    }

}
