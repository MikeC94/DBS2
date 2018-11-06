/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ue3.test;

import java.sql.SQLException;
import ue3.Entities.Genre;
import ue3.Entities.Movie;
import ue3.Entities.MovieCharacter;
import ue3.Entities.MovieGenre;
import ue3.Entities.Person;
import ue3.util.DBConnection;

/**
 *
 * @author Mike
 */
public class Test {

    /*
    public static void main(String[] args) throws SQLException {
        testInsert();
    }
     */
    public static void testInsert() throws SQLException {
        boolean ok = false;
        try {
            Person person = new Person();
            person.setName("Karl Tester");
            person.setSex('M');
            person.insert();

            Movie movie = new Movie();
            movie.setTitle("Die tolle Komoedie");
            movie.setYear(2012);
            movie.setType('C');
            movie.insert();

            MovieCharacter chr = new MovieCharacter();
            chr.setMovieId(movie.getId());
            chr.setPlayerId(person.getId());
            chr.setCharacter("Hauptrolle");
            chr.setAlias(null);
            chr.setPos(1);
            chr.insert();

            Genre genre = new Genre();
            genre.setGenre("Unklar");
            genre.insert();

            MovieGenre movieGenre = new MovieGenre();
            movieGenre.setGenreId(genre.getId());
            movieGenre.setMovieId(movie.getId());
            movieGenre.insert();

            DBConnection.getConnection().commit();
            ok = true;
        } finally {
            if (!ok) {
                DBConnection.getConnection().rollback();
            }
        }
        System.out.println(ok);
    }
}
