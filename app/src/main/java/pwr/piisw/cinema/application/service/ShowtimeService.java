package pwr.piisw.cinema.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.piisw.cinema.application.entity.Showtime;
import pwr.piisw.cinema.application.repository.ShowtimeRepository;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    public Showtime findById(Integer id) {
        return showtimeRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.SHOWTIME_NOT_FOUND));
    }

    public List<Showtime> findByMovieId(Integer movieId) {
        return showtimeRepository.findByMovie_MovieId(movieId);
    }

    public List<Showtime> findByCinemaId(Integer cinemaId) {
        return showtimeRepository.findByCinema_CinemaId(cinemaId);
    }

    public List<Showtime> findByStartTimeBetween(Timestamp startTime, Timestamp startTime2) {
        return showtimeRepository.findByStartTimeBetween(startTime, startTime2);
    }

    public List<Showtime> findByMovieTitle(String title) {
        return showtimeRepository.findByMovieTitle(title);
    }

    public List<Showtime> findByCinemaAndMovie(Integer cinemaId, Integer movieId) {
        return showtimeRepository.findByCinemaAndMovie(cinemaId, movieId);
    }

    @Transactional
    public Showtime save(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!showtimeRepository.existsById(id)) {
            throw new CinemaException(CinemaExceptionType.SHOWTIME_NOT_FOUND);
        }
        showtimeRepository.deleteById(id);
    }
}
