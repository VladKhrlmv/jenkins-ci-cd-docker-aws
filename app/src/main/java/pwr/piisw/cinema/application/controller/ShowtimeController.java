package pwr.piisw.cinema.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Showtime;
import pwr.piisw.cinema.application.service.ShowtimeService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@SecurityRequirement(name = "Keycloak")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public ResponseEntity<List<Showtime>> findAll() {
        return ResponseEntity.ok(showtimeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(showtimeService.findById(id));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Showtime>> findByMovieId(@PathVariable Integer movieId) {
        return ResponseEntity.ok(showtimeService.findByMovieId(movieId));
    }

    @GetMapping("/cinema/{cinemaId}")
    public ResponseEntity<List<Showtime>> findByCinemaId(@PathVariable Integer cinemaId) {
        return ResponseEntity.ok(showtimeService.findByCinemaId(cinemaId));
    }

    @GetMapping("/range")
    public ResponseEntity<List<Showtime>> findByStartTimeBetween(@RequestParam Timestamp startTime, @RequestParam Timestamp endTime) {
        return ResponseEntity.ok(showtimeService.findByStartTimeBetween(startTime, endTime));
    }

    @GetMapping("/movieTitle/{title}")
    public ResponseEntity<List<Showtime>> findByMovieTitle(@PathVariable String title) {
        return ResponseEntity.ok(showtimeService.findByMovieTitle(title));
    }

    @GetMapping("/cinema/{cinemaId}/movie/{movieId}")
    public ResponseEntity<List<Showtime>> findByCinemaAndMovie(@PathVariable Integer cinemaId, @PathVariable Integer movieId) {
        return ResponseEntity.ok(showtimeService.findByCinemaAndMovie(cinemaId, movieId));
    }

    @PostMapping
    public ResponseEntity<Showtime> save(@RequestBody Showtime showtime) {
        return new ResponseEntity<>(showtimeService.save(showtime), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        showtimeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
