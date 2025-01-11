package pwr.piisw.cinema.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Review;
import pwr.piisw.cinema.application.service.ReviewService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@SecurityRequirement(name = "Keycloak")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findAll() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Review>> findByMovieId(@PathVariable Integer movieId) {
        return ResponseEntity.ok(reviewService.findByMovieId(movieId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> findByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(reviewService.findByUserId(userId));
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Review>> findByRatingGreaterThanEqual(@PathVariable BigDecimal rating) {
        return ResponseEntity.ok(reviewService.findByRatingGreaterThanEqual(rating));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Review>> searchReviews(@RequestParam String keyword) {
        return ResponseEntity.ok(reviewService.searchReviews(keyword));
    }

    @PostMapping
    public ResponseEntity<Review> save(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.save(review), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
