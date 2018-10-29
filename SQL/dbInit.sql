-- Tabellen erstellen
--################################################################################
-- Tabelle Genre erstellen
CREATE TABLE Genre (
    GenreID INT,
    Genre VARCHAR(100),
    CONSTRAINT pk_G_GenreID PRIMARY KEY(GenreID),
    CONSTRAINT uk_G_Genre UNIQUE(Genre)    
);

-- Tabelle Movie ertstellen
CREATE TABLE Movie (
    MovieID INT,
    Title VARCHAR(100),
    Year INT,
    Type CHAR,
    CONSTRAINT pk_M_MovieID PRIMARY KEY(MovieID)
);

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


-- Tabellen befüllen
--################################################################################
-- Tabelle Movie befüllen
INSERT INTO Movie VALUES(1, 'Star Wars - Eine neue Hoffnung', 1977, 'C');
INSERT INTO Movie VALUES(2, 'Forrest Gump', 1994, 'C');
INSERT INTO Movie VALUES(3, 'Iron Man', 2008, 'C');

-- Tabelle Genre befüllen
INSERT INTO Genre VALUES(1, 'Science-Fiction');
INSERT INTO Genre VALUES(2, 'Fantasy');
INSERT INTO Genre VALUES(3, 'Drama');
INSERT INTO Genre VALUES(4, 'Dramedy');
INSERT INTO Genre VALUES(6, 'Thriller');

-- Tabelle MovieGenre
INSERT INTO MovieGenre VALUES(1, 1);
INSERT INTO MovieGenre VALUES(2, 1);
INSERT INTO MovieGenre VALUES(3, 2);
INSERT INTO MovieGenre VALUES(4, 2);
INSERT INTO MovieGenre VALUES(1, 3);
INSERT INTO MovieGenre VALUES(6, 3);

-- Tabelle Person befüllen
INSERT INTO Person VALUES(1, 'Carrie Fischer', 'w');
INSERT INTO Person VALUES(2, 'Mark Hamill', 'm');
INSERT INTO Person VALUES(3, 'Harrison Ford', 'm');
INSERT INTO Person VALUES(4, 'Tom Hanks', 'm');
INSERT INTO Person VALUES(5, 'Robert Downy junior', 'm');
INSERT INTO Person VALUES(6, 'Gwyneth Paltrow', 'w');

-- Tabelle Movie Character befüllen
INSERT INTO MovieCharacter VALUES(1, 'Leia Organa', NULL, NULL, 1, 1); 
INSERT INTO MovieCharacter VALUES(2, 'Luke Skywalker', NULL, NULL, 2, 1); 
INSERT INTO MovieCharacter VALUES(3, 'Han Solo', NULL, NULL, 3, 1); 
INSERT INTO MovieCharacter VALUES(4, 'Forrest Gump', NULL, NULL, 4, 2); 
INSERT INTO MovieCharacter VALUES(5, 'Tony Stark', NULL, NULL, 5, 3);
INSERT INTO MovieCharacter VALUES(6, 'Pepper Pots', NULL, NULL, 6, 3);