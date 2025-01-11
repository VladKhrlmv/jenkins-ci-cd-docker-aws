package pwr.piisw.cinema.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.piisw.cinema.application.entity.BookedSeat;
import pwr.piisw.cinema.application.entity.BookedSeatId;
import pwr.piisw.cinema.application.repository.BookedSeatRepository;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.util.List;

@Service
public class BookedSeatService {

    private final BookedSeatRepository bookedSeatRepository;

    public BookedSeatService(BookedSeatRepository bookedSeatRepository) {
        this.bookedSeatRepository = bookedSeatRepository;
    }

    public List<BookedSeat> findAll() {
        return bookedSeatRepository.findAll();
    }

    public BookedSeat findById(BookedSeatId id) {
        return bookedSeatRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.SEAT_NOT_FOUND));
    }

    public List<BookedSeat> findByTicketId(Integer ticketId) {
        return bookedSeatRepository.findByTicket_TicketId(ticketId);
    }

    public List<BookedSeat> findBySeatId(Integer seatId) {
        return bookedSeatRepository.findBySeat_SeatId(seatId);
    }

    public List<BookedSeat> findExpiredBookings() {
        return bookedSeatRepository.findExpiredBookings();
    }

    @Transactional
    public BookedSeat save(BookedSeat bookedSeat) {
        return bookedSeatRepository.save(bookedSeat);
    }

    @Transactional
    public void deleteById(BookedSeatId id) {
        bookedSeatRepository.deleteById(id);
    }
}
