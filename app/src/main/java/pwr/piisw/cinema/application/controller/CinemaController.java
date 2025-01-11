package pwr.piisw.cinema.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Cinema;
import pwr.piisw.cinema.application.service.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
@SecurityRequirement(name = "Keycloak")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public ResponseEntity<List<Cinema>> findAll() {
        return ResponseEntity.ok(cinemaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(cinemaService.findById(id));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Cinema>> findByCity(@PathVariable String city) {
        return ResponseEntity.ok(cinemaService.findByCity(city));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Cinema>> findCinemasByName(@PathVariable String name) {
        return ResponseEntity.ok(cinemaService.findCinemasByName(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Cinema>> searchCinemas(@RequestParam String keyword) {
        return ResponseEntity.ok(cinemaService.searchCinemas(keyword));
    }

    @PostMapping
    public ResponseEntity<Cinema> save(@RequestBody Cinema cinema) {
        return new ResponseEntity<>(cinemaService.save(cinema), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Void> updateAddress(@PathVariable Integer id, @RequestParam String address) {
        cinemaService.updateAddress(id, address);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        cinemaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/city/{city}")
    public ResponseEntity<Void> deleteCinemasByCity(@PathVariable String city) {
        cinemaService.deleteCinemasByCity(city);
        return ResponseEntity.noContent().build();
    }
}
