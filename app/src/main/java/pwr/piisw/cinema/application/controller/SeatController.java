package pwr.piisw.cinema.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Seat;
import pwr.piisw.cinema.application.service.SeatService;
import pwr.piisw.cinema.application.utils.StatusType;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@SecurityRequirement(name = "Keycloak")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<List<Seat>> findAll() {
        return ResponseEntity.ok(seatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(seatService.findById(id));
    }

    @GetMapping("/cinema/{cinemaId}")
    public ResponseEntity<List<Seat>> findByCinemaId(@PathVariable Integer cinemaId) {
        return ResponseEntity.ok(seatService.findByCinemaId(cinemaId));
    }

    @GetMapping("/screen/{screenNumber}/status/{status}")
    public ResponseEntity<List<Seat>> findByScreenNumberAndStatus(@PathVariable Integer screenNumber, @PathVariable StatusType status) {
        return ResponseEntity.ok(seatService.findByScreenNumberAndStatus(screenNumber, status));
    }

    @GetMapping("/available/{cinemaId}")
    public ResponseEntity<List<Seat>> findAvailableSeatsByCinema(@PathVariable Integer cinemaId) {
        return ResponseEntity.ok(seatService.findAvailableSeatsByCinema(cinemaId, StatusType.AVAILABLE));
    }

    @GetMapping("/row/{rowLabel}/seat/{seatNumber}")
    public ResponseEntity<List<Seat>> findByRowLabelAndSeatNumber(@PathVariable char rowLabel, @PathVariable int seatNumber) {
        return ResponseEntity.ok(seatService.findByRowLabelAndSeatNumber(rowLabel, seatNumber));
    }

    @PostMapping
    public ResponseEntity<Seat> save(@RequestBody Seat seat) {
        return new ResponseEntity<>(seatService.save(seat), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        seatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
