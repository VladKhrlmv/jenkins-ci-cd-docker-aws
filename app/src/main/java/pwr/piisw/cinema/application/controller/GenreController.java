package pwr.piisw.cinema.application.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Genre;
import pwr.piisw.cinema.application.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@SecurityRequirement(name = "Keycloak")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> findAll() {
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(genreService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Genre>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(genreService.findByName(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Genre>> searchGenres(@RequestParam String keyword) {
        return ResponseEntity.ok(genreService.searchGenres(keyword));
    }

    @PostMapping
    public ResponseEntity<Genre> save(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.save(genre), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
