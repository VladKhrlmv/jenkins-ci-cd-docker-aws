-- Insert genres
INSERT INTO genres (name)
VALUES ('Action'),
       ('Romance'),
       ('Comedy'),
       ('Horror'),
       ('Drama');

-- Insert movies
INSERT INTO movies (movie_id, title, release_year, duration_minutes, rating, description, poster_url, trailer_url)
VALUES (1, 'The Big Adventure', 2022, 120, 'PG-13', 'An epic journey of heroism and discovery.',
        'http://example.com/poster1.jpg', 'http://example.com/trailer1.mp4'),
       (2, 'Love in the Air', 2021, 95, 'R', 'A romantic story set in Paris.', 'http://example.com/poster2.jpg',
        'http://example.com/trailer2.mp4');

-- Insert cinemas
INSERT INTO cinemas (cinema_id, name, address, city, contact_phone)
VALUES (1, 'Downtown Cinema', '123 Main St, Downtown', 'Metro City', '123-456-7890'),
       (2, 'Sunset Cinema', '456 Sunset Blvd', 'Sunset Town', '987-654-3210');

-- Insert showings
INSERT INTO showtimes (show_id, movie_id, cinema_id, start_time, screen_number, price)
VALUES (1, 1, 1, '2024-06-01 14:00:00', 5, 12.50),
       (2, 2, 1, '2024-06-01 16:30:00', 5, 10.00);

-- Insert users
INSERT INTO users (user_id, email, name, password_hash, role)
VALUES (1, 'john.doe@example.com', 'John Doe', 'hashedpassword1', 'customer'),
       (2, 'admin@example.com', 'Admin User', 'hashedpassword2', 'admin');

-- Insert reviews
INSERT INTO reviews (review_id, user_id, movie_id, rating, comment, review_date)
VALUES (1, 1, 1, 8.5, 'Great movie, highly recommended!', '2024-05-12 12:34:56');

-- Insert seats
INSERT INTO seats (seat_id, cinema_id, screen_number, row_label, seat_number, status)
VALUES (1, 1, 5, 'A', 1, 'available'),
       (2, 1, 5, 'A', 2, 'available');

-- Insert tickets
INSERT INTO tickets (ticket_id, user_id, show_id, booking_date)
VALUES (1, 1, 1, '2024-05-11 10:00:00');

-- Insert booked seats
INSERT INTO booked_seats (ticket_id, seat_id, reservation_expiry)
VALUES (1, 1, '2024-06-01 13:00:00');

-- Insert movie genres
INSERT INTO movie_genres (movie_id, genre_id)
VALUES (1, 1),
       (2, 2);
