package ue3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ue3.test.Test;
import ue3.util.DBConnection;

/**
 *
 * @author mike
 */
public class ReadMovieDB {

    public static void main(String[] args) throws SQLException {

        /*
        int movieID = -1;

        if (args.length > 0) {
            movieID = Integer.parseInt(args[0]);
        }
         */
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MovieID, Title FROM Movie ORDER BY MovieID");
            System.out.println("Folgende Filme(ID | Titel) sind vorhanden:");
            while (rs.next()) {
                System.out.println(rs.getString("MovieID") + " | " + rs.getString("Title"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
}
