package pwr.piisw.cinema.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.piisw.cinema.application.entity.Genre;
import pwr.piisw.cinema.application.entity.Movie;
import pwr.piisw.cinema.application.repository.MovieRepository;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.MOVIE_NOT_FOUND));
    }

    public List<Movie> findByTitleContainingIgnoreCase(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> findByGenresName(String genreName) {
        return movieRepository.findByGenres_Name(genreName);
    }

    public List<Movie> findByReleaseYear(Integer releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    public List<Movie> findByGenre(Genre genre) {
        return movieRepository.findByGenre(genre);
    }

    public List<Movie> findMoviesByReleaseYearRange(Integer startYear, Integer endYear) {
        return movieRepository.findMoviesByReleaseYearRange(startYear, endYear);
    }

    public List<Movie> searchMovies(String keyword) {
        return movieRepository.searchMovies(keyword);
    }

    public List<Movie> findMoviesByCinemaName(String cinemaName) {
        return movieRepository.findMoviesByCinemaName(cinemaName);
    }

    @Transactional
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new CinemaException(CinemaExceptionType.MOVIE_NOT_FOUND);
        }
        movieRepository.deleteById(id);
    }
}