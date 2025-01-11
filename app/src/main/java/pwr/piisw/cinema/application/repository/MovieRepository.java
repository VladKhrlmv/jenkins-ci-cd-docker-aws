package pwr.piisw.cinema.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pwr.piisw.cinema.application.entity.Genre;
import pwr.piisw.cinema.application.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByGenres_Name(String genreName);

    List<Movie> findByReleaseYear(Integer releaseYear);

    @Query("SELECT m FROM Movie m JOIN Showtime s ON m.movieId = s.movie.movieId JOIN Cinema c ON s.cinema.cinemaId = c.cinemaId WHERE c.name = :cinemaName")
    List<Movie> findMoviesByCinemaName(@Param("cinemaName") String cinemaName);

    @Query("SELECT m FROM Movie m WHERE :genre MEMBER OF m.genres")
    List<Movie> findByGenre(@Param("genre") Genre genre);

    @Query("SELECT m FROM Movie m WHERE m.releaseYear BETWEEN :startYear AND :endYear")
    List<Movie> findMoviesByReleaseYearRange(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);

    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:keyword% OR m.description LIKE %:keyword%")
    List<Movie> searchMovies(@Param("keyword") String keyword);
}