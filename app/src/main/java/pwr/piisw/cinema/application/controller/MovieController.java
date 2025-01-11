package pwr.piisw.cinema.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Movie;
import pwr.piisw.cinema.application.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@SecurityRequirement(name = "Keycloak")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String keyword) {
        return ResponseEntity.ok(movieService.searchMovies(keyword));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Movie>> findByTitleContainingIgnoreCase(@PathVariable String title) {
        return ResponseEntity.ok(movieService.findByTitleContainingIgnoreCase(title));
    }

    @GetMapping("/genre/{genreName}")
    public ResponseEntity<List<Movie>> findByGenresName(@PathVariable String genreName) {
        return ResponseEntity.ok(movieService.findByGenresName(genreName));
    }

    @GetMapping("/year/{releaseYear}")
    public ResponseEntity<List<Movie>> findByReleaseYear(@PathVariable Integer releaseYear) {
        return ResponseEntity.ok(movieService.findByReleaseYear(releaseYear));
    }

    @PostMapping
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cinema/{cinemaName}")
    public ResponseEntity<List<Movie>> findMoviesByCinemaName(@PathVariable String cinemaName) {
        return ResponseEntity.ok(movieService.findMoviesByCinemaName(cinemaName));
    }
}
