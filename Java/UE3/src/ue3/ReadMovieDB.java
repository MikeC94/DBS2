package ue3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import ue3.Entities.Movie;
import ue3.util.DBConnection;

/**
 * Übung 3 - Iteration 2
 *
 * @author A-Team
 */
public class ReadMovieDB {

    /**
     * Main-Methode
     *
     * @param args String Parameter
     */
    public static void main(String[] args) {

        // ID/ Titel übergeben Film anzeigen
        if (args.length > 0) {
            String id = args[0];
            if (isInteger(id)) {
                Movie m = MovieFactory.findById(Integer.parseInt(id));
                if (m != null) {
                    System.out.println(m);
                } else {
                    System.out.println("Die übergebenen ID oder der Titel ist nicht in der Datenbank vorhanden!");
                }
            } else {
                List<Movie> ms = MovieFactory.findByTitle(id);
                if (!ms.isEmpty()) {
                    for (Movie m : ms) {
                        System.out.println(m);
                    }
                } else {
                    System.out.println("Die übergebenen ID oder der Titel ist nicht in der Datenbank vorhanden!");
                }
            }

        } // keine ID übergeben alle ID's mit Titel ausgeben
        else {
            try {
                Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT MovieID, Title FROM Movie ORDER BY MovieID");
                System.out.println("Folgende Filme(ID | Titel) sind vorhanden:");
                while (rs.next()) {
                    System.out.println(rs.getString("MovieID") + " | " + rs.getString("Title"));
                }
                conn.close();
            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
        }
    }

    /**
     * Diese Methode überprüft ob die übergebene Zeichenkettte ein Zahl ist.
     *
     * @param input Zeichenkette.
     *
     * @return wahr/falsch
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
