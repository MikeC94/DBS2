/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ue3.Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ue3.util.DBConnection;

/**
 *
 * @author Mike
 */
public class Person {

    private int id;
    private String name;
    private char sex;

    public Person() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

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
     * Diese Methode gibt den Wert des Person Objekts als String zurück.
     *
     * @return String Wert
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
