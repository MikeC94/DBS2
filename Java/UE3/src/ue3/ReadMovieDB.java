package ue3;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mike
 */
public class ReadMovieDB {

    public static void main(String[] args) {

      /*  Movie m = new Movie();
        m.setTitle("Harry Potter und der Stein der Weisen");
        m.setYear(2000);
        m.setType('C');
        m.insert();
        
        System.out.println(m);*/
        
        
        int movieID = -1;

        if (args.length > 0) {
            movieID = Integer.parseInt(args[0]);
        }
        if (movieID == 0) {
            movieID = 1;
        }

        Movie m = new Movie();
        if (m.importSQL(movieID)) {
            System.out.println(m);
        } else {
            if (Properties.sqlc.connect()) {

                ResultSet rs = Properties.sqlc.executeQuery("SELECT MovieID, Title FROM Movie ORDER BY MovieID");
                System.out.println("Folgende Filme(ID | Titel) sind vorhanden:");
                try {
                    while (rs.next()) {
                        System.out.println(rs.getString("MovieID") + " | " + rs.getString("Title"));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                Properties.sqlc.disconnect();
            }
        }
    }

}
