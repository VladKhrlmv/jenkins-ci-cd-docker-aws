-- Create table for genres
CREATE TABLE genres
(
    genre_id SERIAL PRIMARY KEY,
    name     VARCHAR(50) UNIQUE NOT NULL
);

-- Create table for movies
CREATE TABLE movies
(
    movie_id         INT PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    release_year     INT,
    duration_minutes INT,
    rating           VARCHAR(10),
    description      TEXT,
    poster_url       VARCHAR(255),
    trailer_url      VARCHAR(255)
);
