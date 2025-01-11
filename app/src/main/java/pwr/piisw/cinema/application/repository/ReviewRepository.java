package pwr.piisw.cinema.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pwr.piisw.cinema.application.entity.Review;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByMovie_MovieId(Integer movieId);

    List<Review> findByUser_UserId(Integer userId);

    List<Review> findByRatingGreaterThanEqual(BigDecimal rating);

    @Query("SELECT r FROM Review r WHERE r.movie.title = :title")
    List<Review> findByMovieTitle(@Param("title") String title);

    @Query("SELECT r FROM Review r WHERE r.user.email = :email")
    List<Review> findByUserEmail(@Param("email") String email);

    @Query("SELECT r FROM Review r WHERE r.comment LIKE %:keyword%")
    List<Review> searchReviews(@Param("keyword") String keyword);
}