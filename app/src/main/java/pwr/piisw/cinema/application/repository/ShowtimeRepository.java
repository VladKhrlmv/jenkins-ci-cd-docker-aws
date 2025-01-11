package pwr.piisw.cinema.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pwr.piisw.cinema.application.entity.Showtime;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

    List<Showtime> findByMovie_MovieId(Integer movieId);

    List<Showtime> findByCinema_CinemaId(Integer cinemaId);

    List<Showtime> findByStartTimeBetween(Timestamp startTime, Timestamp startTime2);

    @Query("SELECT s FROM Showtime s WHERE s.movie.title = :title")
    List<Showtime> findByMovieTitle(@Param("title") String title);

    @Query("SELECT s FROM Showtime s WHERE s.cinema.cinemaId = :cinemaId AND s.movie.movieId = :movieId")
    List<Showtime> findByCinemaAndMovie(@Param("cinemaId") Integer cinemaId, @Param("movieId") Integer movieId);
}