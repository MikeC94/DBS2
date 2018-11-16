package ue3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ue3.Entities.Movie;
import ue3.util.DBConnection;

/**
 *
 * @author A-Team
 */
public class MovieFactory {

    /**
     * Diese Methode sucht einen Film anhand seiner Id in SQL
     *
     * @param id MovieId
     *
     * @return Film
     */
    public static Movie findById(int id) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Movie WHERE MovieID = " + id;
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return createMovieFromRS(rs);
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Diese Methode sucht nach Filmen anhand des Titels in SQL.
     *
     * @param title Titel
     * @return Filmliste
     */
    public static List<Movie> findByTitle(String title) {
        try {
            List<Movie> movies = new ArrayList<>();
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Movie WHERE Title = '" + title + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Movie m = createMovieFromRS(rs);
                if (m != null) {
                    movies.add(m);
                }
            }
            return movies;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Diese Methode erstellt aus einem ResultSet ein Film
     *
     * @param rs Resultset
     * @return Film
     */
    private static Movie createMovieFromRS(ResultSet rs) throws SQLException {
        Movie m = new Movie();
        m.setId(rs.getInt("MovieID"));
        m.setTitle(rs.getString("Title"));
        if (!rs.getString("Type").equals("")) {
            m.setType(rs.getString("Type").charAt(0));
        }
        m.setYear(rs.getInt("Year"));
        m.importCharacter();
        m.importGenre();
        return m;
    }

}
