import java.util.*;
import java.sql.*;
import java.io.*;

public class ReadMovieDB {

    public static void main (String[] args) {

        Scanner s = new Scanner(System.in);
        String movieID = "";//args [0];
        String user = "";
        String password = "";

        System.out.print("Bitte Benutzername eingeben: ");
        user = s.nextLine();
        System.out.print("Bitte Passwort eingeben: ");
        password = s.nextLine();


        Connection con = null;
        Statement stmt = null;
        ResultSet rs  = null;
        try {
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1111:db01",
                user,
                password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Genre");
            while (rs.next()) {
                System.out.println(rs.getString("Genre"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 

    }

}