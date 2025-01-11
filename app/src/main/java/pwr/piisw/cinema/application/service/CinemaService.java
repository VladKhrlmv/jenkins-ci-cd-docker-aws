package pwr.piisw.cinema.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.piisw.cinema.application.entity.Cinema;
import pwr.piisw.cinema.application.repository.CinemaRepository;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.util.List;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Cinema findById(Integer id) {
        return cinemaRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.CINEMA_NOT_FOUND));
    }

    public List<Cinema> findByCity(String city) {
        return cinemaRepository.findByCity(city);
    }

    public List<Cinema> findCinemasByName(String name) {
        return cinemaRepository.findCinemasByName(name);
    }

    public List<Cinema> searchCinemas(String keyword) {
        return cinemaRepository.searchCinemas(keyword);
    }

    @Transactional
    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Transactional
    public void updateAddress(Integer cinemaId, String address) {
        if (!cinemaRepository.existsById(cinemaId)) {
            throw new CinemaException(CinemaExceptionType.CINEMA_NOT_FOUND);
        }
        cinemaRepository.updateAddress(cinemaId, address);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!cinemaRepository.existsById(id)) {
            throw new CinemaException(CinemaExceptionType.CINEMA_NOT_FOUND);
        }
        cinemaRepository.deleteById(id);
    }

    @Transactional
    public void deleteCinemasByCity(String city) {
        cinemaRepository.deleteCinemasByCity(city);
    }
}