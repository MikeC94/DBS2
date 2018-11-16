-- Übung 3 Iteration 1

-- Löschen
--################################################################################
-- Sequencen löschen
DROP SEQUENCE G_GenreID; 
DROP SEQUENCE M_MovieID; 
DROP SEQUENCE P_PersonID; 
DROP SEQUENCE MC_MovCharID; 

-- Daten löschen
DELETE FROM MovieGenre;
DELETE FROM MovieCharacter;
DELETE FROM Person;
DELETE FROM Genre;
DELETE FROM Movie;

-- Tabellen löschen
DROP TABLE MovieGenre;
DROP TABLE MovieCharacter;
DROP TABLE Person;
DROP TABLE Genre;
DROP TABLE Movie;

-- Tabellen erstellen
--################################################################################

-- Tabelle Genre erstellen
CREATE TABLE Genre (
    GenreID INT,
    Genre VARCHAR(100),
    CONSTRAINT pk_G_GenreID PRIMARY KEY(GenreID),
    CONSTRAINT uk_G_Genre UNIQUE(Genre) 
);
CREATE SEQUENCE G_GenreID;

-- Tabelle Movie ertstellen
CREATE TABLE Movie (
    MovieID INT,
    Title VARCHAR(100),
    Year INT,
    Type CHAR,
    CONSTRAINT pk_M_MovieID PRIMARY KEY(MovieID)
);
CREATE SEQUENCE M_MovieID;

-- Tabelle MovieGenre erstellen 
CREATE TABLE MovieGenre (
    GenreID INT,
    MovieID INT,
    CONSTRAINT pk_MG_ID PRIMARY KEY(GenreID, MovieID),
    CONSTRAINT fk_MG_GenreID FOREIGN KEY(GenreID) REFERENCES Genre(GenreID),
    CONSTRAINT fk_MG_MovieID FOREIGN KEY(MovieID) REFERENCES Movie(MovieID)
);

-- Tabelle Person erstellen
CREATE TABLE Person (
    PersonID INT,
    Name VARCHAR(100),
    Sex CHAR,
    CONSTRAINT pk_P_PersonID PRIMARY KEY(PersonID),
    CONSTRAINT uk_P_Name UNIQUE(Name)
);
CREATE SEQUENCE P_PersonID;

-- Tabelle MovieCharacter erstellen
CREATE TABLE MovieCharacter (
    MovCharID INT,
    Character VARCHAR(100),
    Alias VARCHAR(100),
    Position INT,
    PersonID INT,
    MovieID INT,
    CONSTRAINT pk_MC_MovCharID PRIMARY KEY(MovCharID),
    CONSTRAINT fk_MC_PersonID FOREIGN KEY(PersonID) REFERENCES Person(PersonID),
    CONSTRAINT fk_MC_MovieID FOREIGN KEY(MovieID) REFERENCES Movie(MovieID)
);
CREATE SEQUENCE MC_MovCharID;

-- Tabellen befüllen
--################################################################################

-- Tabelle Movie befüllen
INSERT INTO Movie VALUES(M_MovieID.nextval, 'Star Wars - Eine neue Hoffnung', 1977, 'C');
INSERT INTO Movie VALUES(M_MovieID.nextval, 'Forrest Gump', 1994, 'C');
INSERT INTO Movie VALUES(M_MovieID.nextval, 'Iron Man', 2008, 'C');

-- Tabelle Genre befüllen
INSERT INTO Genre VALUES(G_GenreID.nextval, 'Science-Fiction');
INSERT INTO Genre VALUES(G_GenreID.nextval, 'Fantasy');
INSERT INTO Genre VALUES(G_GenreID.nextval, 'Drama');
INSERT INTO Genre VALUES(G_GenreID.nextval, 'Dramedy');
INSERT INTO Genre VALUES(G_GenreID.nextval, 'Thriller');

-- Tabelle MovieGenre
INSERT INTO MovieGenre VALUES(1, 1);
INSERT INTO MovieGenre VALUES(2, 1);
INSERT INTO MovieGenre VALUES(3, 2);
INSERT INTO MovieGenre VALUES(4, 2);
INSERT INTO MovieGenre VALUES(1, 3);
INSERT INTO MovieGenre VALUES(5, 3);

-- Tabelle Person befüllen
INSERT INTO Person VALUES(P_PersonID.nextval, 'Carrie Fischer', 'w');
INSERT INTO Person VALUES(P_PersonID.nextval, 'Mark Hamill', 'm');
INSERT INTO Person VALUES(P_PersonID.nextval, 'Harrison Ford', 'm');
INSERT INTO Person VALUES(P_PersonID.nextval, 'Tom Hanks', 'm');
INSERT INTO Person VALUES(P_PersonID.nextval, 'Robert Downy junior', 'm');
INSERT INTO Person VALUES(P_PersonID.nextval, 'Gwyneth Paltrow', 'w');

-- Tabelle Movie Character befüllen
INSERT INTO MovieCharacter VALUES(MC_MovCharID.nextval, 'Leia Organa', NULL, NULL, 1, 1); 
INSERT INTO MovieCharacter VALUES(MC_MovCharID.nextval, 'Luke Skywalker', NULL, NULL, 2, 1); 
INSERT INTO MovieCharacter VALUES(MC_MovCharID.nextval, 'Han Solo', NULL, NULL, 3, 1); 
INSERT INTO MovieCharacter VALUES(MC_MovCharID.nextval, 'Forrest Gump', NULL, NULL, 4, 2); 
INSERT INTO MovieCharacter VALUES(MC_MovCharID.nextval, 'Tony Stark', NULL, NULL, 5, 3);
INSERT INTO MovieCharacter VALUES(MC_MovCharID.nextval, 'Pepper Pots', NULL, NULL, 6, 3);