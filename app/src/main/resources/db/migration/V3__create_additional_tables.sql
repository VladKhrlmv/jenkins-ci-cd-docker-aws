-- Create table for cinemas
CREATE TABLE cinemas
(
    cinema_id     INT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    address       TEXT,
    city          VARCHAR(100),
    contact_phone VARCHAR(20)
);

-- Create table for showings
CREATE TABLE showtimes
(
    show_id       INT PRIMARY KEY,
    movie_id      INT REFERENCES movies (movie_id),
    cinema_id     INT REFERENCES cinemas (cinema_id),
    start_time    TIMESTAMP NOT NULL,
    screen_number INT,
    price         DECIMAL(5, 2)
);

-- Create table for users
CREATE TABLE users
(
    user_id       INT PRIMARY KEY,
    email         VARCHAR(100) UNIQUE NOT NULL,
    name          VARCHAR(100)        NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    role          role_type DEFAULT 'customer'
);

-- Create table for reviews
CREATE TABLE reviews
(
    review_id   INT PRIMARY KEY,
    user_id     INT REFERENCES users (user_id),
    movie_id    INT REFERENCES movies (movie_id),
    rating      NUMERIC(2, 1),
    comment     TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create table for seats
CREATE TABLE seats
(
    seat_id       INT PRIMARY KEY,
    cinema_id     INT REFERENCES cinemas (cinema_id),
    screen_number INT,
    row_label     CHAR(1),
    seat_number   INT,
    status        status_type DEFAULT 'available'
);

-- Create table for tickets
CREATE TABLE tickets
(
    ticket_id    INT PRIMARY KEY,
    user_id      INT REFERENCES users (user_id),
    show_id      INT REFERENCES showtimes (show_id),
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create table for booked seats
CREATE TABLE booked_seats
(
    ticket_id          INT,
    seat_id            INT,
    PRIMARY KEY (ticket_id, seat_id),
    reservation_expiry TIMESTAMP,
    FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id),
    FOREIGN KEY (seat_id) REFERENCES seats (seat_id)
);

-- Create a junction table for movie genres
CREATE TABLE movie_genres
(
    movie_id INT,
    genre_id INT,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id)
);