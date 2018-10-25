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



