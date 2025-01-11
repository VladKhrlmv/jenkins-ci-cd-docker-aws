package pwr.piisw.cinema.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.BookedSeat;
import pwr.piisw.cinema.application.entity.BookedSeatId;
import pwr.piisw.cinema.application.service.BookedSeatService;

import java.util.List;

@RestController
@RequestMapping("/api/bookedSeats")
@SecurityRequirement(name = "Keycloak")
public class BookedSeatController {

    private final BookedSeatService bookedSeatService;

    public BookedSeatController(BookedSeatService bookedSeatService) {
        this.bookedSeatService = bookedSeatService;
    }

    @GetMapping
    public ResponseEntity<List<BookedSeat>> findAll() {
        return ResponseEntity.ok(bookedSeatService.findAll());
    }

    @GetMapping("/{ticketId}/{seatId}")
    public ResponseEntity<BookedSeat> findById(@PathVariable Integer ticketId, @PathVariable Integer seatId) {
        return ResponseEntity.ok(bookedSeatService.findById(new BookedSeatId(ticketId, seatId)));
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<BookedSeat>> findByTicketId(@PathVariable Integer ticketId) {
        return ResponseEntity.ok(bookedSeatService.findByTicketId(ticketId));
    }

    @GetMapping("/seat/{seatId}")
    public ResponseEntity<List<BookedSeat>> findBySeatId(@PathVariable Integer seatId) {
        return ResponseEntity.ok(bookedSeatService.findBySeatId(seatId));
    }

    @GetMapping("/expired")
    public ResponseEntity<List<BookedSeat>> findExpiredBookings() {
        return ResponseEntity.ok(bookedSeatService.findExpiredBookings());
    }

    @PostMapping
    public ResponseEntity<BookedSeat> save(@RequestBody BookedSeat bookedSeat) {
        return new ResponseEntity<>(bookedSeatService.save(bookedSeat), HttpStatus.CREATED);
    }

    @DeleteMapping("/{ticketId}/{seatId}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer ticketId, @PathVariable Integer seatId) {
        bookedSeatService.deleteById(new BookedSeatId(ticketId, seatId));
        return ResponseEntity.noContent().build();
    }
}
