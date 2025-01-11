package pwr.piisw.cinema.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pwr.piisw.cinema.application.entity.BookedSeat;
import pwr.piisw.cinema.application.entity.BookedSeatId;

import java.util.List;

@Repository
public interface BookedSeatRepository extends JpaRepository<BookedSeat, BookedSeatId> {

    List<BookedSeat> findByTicket_TicketId(Integer ticketId);

    List<BookedSeat> findBySeat_SeatId(Integer seatId);

    @Query("SELECT bs FROM BookedSeat bs WHERE bs.reservationExpiry < CURRENT_TIMESTAMP")
    List<BookedSeat> findExpiredBookings();
}