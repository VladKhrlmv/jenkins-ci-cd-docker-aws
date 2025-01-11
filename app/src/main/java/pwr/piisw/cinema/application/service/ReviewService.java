package pwr.piisw.cinema.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.piisw.cinema.application.entity.Review;
import pwr.piisw.cinema.application.repository.ReviewRepository;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.REVIEW_NOT_FOUND));
    }

    public List<Review> findByMovieId(Integer movieId) {
        return reviewRepository.findByMovie_MovieId(movieId);
    }

    public List<Review> findByUserId(Integer userId) {
        return reviewRepository.findByUser_UserId(userId);
    }

    public List<Review> findByRatingGreaterThanEqual(BigDecimal rating) {
        return reviewRepository.findByRatingGreaterThanEqual(rating);
    }

    public List<Review> findByMovieTitle(String title) {
        return reviewRepository.findByMovieTitle(title);
    }

    public List<Review> findByUserEmail(String email) {
        return reviewRepository.findByUserEmail(email);
    }

    public List<Review> searchReviews(String keyword) {
        return reviewRepository.searchReviews(keyword);
    }

    @Transactional
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!reviewRepository.existsById(id)) {
            throw new CinemaException(CinemaExceptionType.REVIEW_NOT_FOUND);
        }
        reviewRepository.deleteById(id);
    }
}
