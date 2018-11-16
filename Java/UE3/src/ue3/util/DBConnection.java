package ue3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Diese Klasse stellt eine Verbindung zum Oracle SQL Server her
 *
 * @author Mike
 */
public class DBConnection {

    private static Connection conn = null;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1111:db01", "ebz-sqk-u1", "testit2_");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Diese Methode git das Verbindungsobjekt zurück.
     *
     * @return Verbindungsobjekt
     */
    public static Connection getConnection() {
        return conn;
    }

}
