package pwr.piisw.cinema.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pwr.piisw.cinema.application.entity.Ticket;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByUser_UserId(Integer userId);

    List<Ticket> findByShowtime_ShowId(Integer showId);

    @Query("SELECT t FROM Ticket t WHERE t.user.email = :email")
    List<Ticket> findByUserEmail(@Param("email") String email);

    @Query("SELECT t FROM Ticket t WHERE t.bookingDate BETWEEN :startDate AND :endDate")
    List<Ticket> findTicketsByBookingDateRange(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    @Query("SELECT t FROM Ticket t WHERE t.showtime.cinema.cinemaId = :cinemaId")
    List<Ticket> findByCinemaId(@Param("cinemaId") Integer cinemaId);
}