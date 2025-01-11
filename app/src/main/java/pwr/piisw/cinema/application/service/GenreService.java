package pwr.piisw.cinema.application.service;

import org.springframework.stereotype.Service;
import pwr.piisw.cinema.application.entity.Genre;
import pwr.piisw.cinema.application.repository.GenreRepository;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.GENRE_NOT_FOUND));
    }

    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    public List<Genre> findByNameIgnoreCase(String name) {
        return genreRepository.findByNameIgnoreCase(name);
    }

    public List<Genre> searchGenres(String keyword) {
        return genreRepository.searchGenres(keyword);
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteById(Integer id) {
        if (!genreRepository.existsById(id)) {
            throw new CinemaException(CinemaExceptionType.GENRE_NOT_FOUND);
        }
        genreRepository.deleteById(id);
    }
}