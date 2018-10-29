/*
Iteration 2 Auslesen der Daten mit JDBC
In dieser Iteration sollen Sie ein erstes JDBC-Programm schreiben. Das Programm soll ein
Java-Kommandozeilenprogramm sein, welches Daten zu einem Film aus der Datenbank
ausliest und ausgibt. Die Ausgabe soll in etwa folgendermaßen aussehen:
Kinofilm: Star Wars (1977)
Genre: Action | Abenteuer | Fantasy | Sci-Fi
Darsteller:
 Luke Skywalker: Mark Hamill
 Han Solo: Harrison Ford
 ...
Als Kommandozeilenparameter soll die ID des Films übergeben werden, dessen Daten dann
ausgegeben werden.
Testen Sie Ihr Programm anhand der in Iteration 1 erstellten Beispieldaten.
Anforderungen
- Das Kommandozeilenprogramm heiß ReadMovieDB.java
- Der Aufruf sieht so aus: java ReadMovieDB <ID>
- Wenn keine Movie ID übergeben ist dann wird eine Liste der vorhandene IDs mit Title
ausgegeben.
- Wenn eine Movie ID übergeben wird dann wird eine detaillierte Beschreibung des
Films ausgegeben
- Das Programm kann mit unbekannten IDs umgehen. 
 */
package ue3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class ReadMovieDB {

    public static void main (String[] args) {

        Scanner s = new Scanner(System.in);
        String movieID = "";
        String user;
        String password;

        if (args.length > 0) {
            movieID = args[0];
        }
        
        System.out.print("Bitte Benutzername eingeben: ");
        user = s.nextLine();
        System.out.print("Bitte Passwort eingeben: ");
        password = s.nextLine();


        SQLConnector con = new SQLConnector("localhost", "1111", "db01", user,
                password);
        if (con.connect()) {
            
            if (movieID.equals("")){
                ResultSet rs = con.executeQuery("SELECT MovieID, Title FROM Movie");
                System.out.println("Folgende Filme(ID | Titel) sind vorhanden:");
                try {
                    while(rs.next()){
                    System.out.println(rs.getString("MovieID") + " | " + rs.getString("Title"));   
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                ;
            }
            
            con.disconnect();
        }

    }

}