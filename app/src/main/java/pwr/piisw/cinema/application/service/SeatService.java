package pwr.piisw.cinema.application.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pwr.piisw.cinema.application.entity.Seat;
import pwr.piisw.cinema.application.repository.SeatRepository;
import pwr.piisw.cinema.application.utils.StatusType;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    public Seat findById(Integer id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.SEAT_NOT_FOUND));
    }

    public List<Seat> findByCinemaId(Integer cinemaId) {
        return seatRepository.findByCinema_CinemaId(cinemaId);
    }

    public List<Seat> findByScreenNumberAndStatus(Integer screenNumber, StatusType status) {
        return seatRepository.findByScreenNumberAndStatus(screenNumber, status);
    }

    public List<Seat> findAvailableSeatsByCinema(Integer cinemaId, StatusType status) {
        return seatRepository.findAvailableSeatsByCinema(cinemaId, status);
    }

    public List<Seat> findByRowLabelAndSeatNumber(char rowLabel, int seatNumber) {
        return seatRepository.findByRowLabelAndSeatNumber(rowLabel, seatNumber);
    }

    @Transactional
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!seatRepository.existsById(id)) {
            throw new CinemaException(CinemaExceptionType.SEAT_NOT_FOUND);
        }
        seatRepository.deleteById(id);
    }

}
