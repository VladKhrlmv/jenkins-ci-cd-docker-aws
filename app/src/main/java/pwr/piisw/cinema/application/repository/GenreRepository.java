package pwr.piisw.cinema.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pwr.piisw.cinema.application.entity.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findByName(String name);

    @Query("SELECT g FROM Genre g WHERE LOWER(g.name) = LOWER(:name)")
    List<Genre> findByNameIgnoreCase(@Param("name") String name);

    @Query("SELECT g FROM Genre g WHERE g.name LIKE %:keyword%")
    List<Genre> searchGenres(@Param("keyword") String keyword);
}