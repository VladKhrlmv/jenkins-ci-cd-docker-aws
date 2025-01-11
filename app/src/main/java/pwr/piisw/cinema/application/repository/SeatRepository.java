package pwr.piisw.cinema.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pwr.piisw.cinema.application.entity.Seat;
import pwr.piisw.cinema.application.utils.StatusType;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findByCinema_CinemaId(Integer cinemaId);

    List<Seat> findByScreenNumberAndStatus(int screenNumber, StatusType status);

    @Query("SELECT s FROM Seat s WHERE s.cinema.cinemaId = :cinemaId AND s.status = :status")
    List<Seat> findAvailableSeatsByCinema(@Param("cinemaId") Integer cinemaId, @Param("status") StatusType status);

    @Query("SELECT s FROM Seat s WHERE s.rowLabel = :rowLabel AND s.seatNumber = :seatNumber")
    List<Seat> findByRowLabelAndSeatNumber(@Param("rowLabel") char rowLabel, @Param("seatNumber") int seatNumber);
}