/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uebungsblatt3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class ReadMovieDB {

    public static void main (String[] args) {

        Scanner s = new Scanner(System.in);
        String movieID = "";//args [0];
        String user;
        String password;

        System.out.print("Bitte Benutzername eingeben: ");
        user = s.nextLine();
        System.out.print("Bitte Passwort eingeben: ");
        password = s.nextLine();


        Connection con;
        Statement stmt;
        ResultSet rs;
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