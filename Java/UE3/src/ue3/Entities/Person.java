package ue3.Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ue3.util.DBConnection;

/**
 * Diese Klasse stellt eine Person da
 *
 * @author A-Teams
 */
public class Person {

    private int id;
    private String name;
    private char sex;

    /**
     * Diese Methode gibt die Id zurück.
     *
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Diese Methode setzt die Id.
     *
     * @param id Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Diese Mehtode gibt den Namen zurück.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Diese Methode setzt den Namen.
     *
     * @param name Namen
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Diese Methode gibt das Geschlecht zurück. 'M' oder 'F'
     *
     * @return Geschlecht
     */
    public char getSex() {
        return sex;
    }

    /**
     * Diese Methode setzt das Geschlecht. 'M' oder 'F'
     *
     * @param sex Geschlecht
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

    /**
     * Diese Methode fügt das aktuelle Person-Objekt in SQL(Tablle Movie) ein.
     */
    public void insert() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String insert = "INSERT INTO Person VALUES(";
            insert += "P_PersonID.nextval, ";
            insert += "'" + this.getName() + "', ";
            insert += "'" + this.getSex() + "'";
            insert += ")";
            stmt.execute(insert);
            ResultSet rs = stmt.executeQuery("SELECT P_PersonID.currval FROM DUAL");
            if (rs.next()) {
                this.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode aktualisiert das aktuelle Objekt in SQL.
     */
    public void update() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String update = "UPDATE Person SET ";
            update += "Name = '" + this.getName() + "', ";
            update += "Sex = '" + this.getSex() + "'";
            update += " WHERE PersonID = " + this.getId();
            stmt.execute(update);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode löscht das aktuelle Person Objekt in SQL.
     */
    public void delete() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String delete = "DELETE FROM Person WHERE PersonID = " + this.getId();
            stmt.execute(delete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Diese Methode gibt den Wert des Person Objekts als String zurück.
     *
     * @return String Wert
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
