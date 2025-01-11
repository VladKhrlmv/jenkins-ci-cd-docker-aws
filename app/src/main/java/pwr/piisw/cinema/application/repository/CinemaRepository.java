package pwr.piisw.cinema.application.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pwr.piisw.cinema.application.entity.Cinema;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    List<Cinema> findByCity(String city);

    @Query("select c from Cinema c where c.name = :name")
    List<Cinema> findCinemasByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("update Cinema c set c.address = :address where c.cinemaId = :cinemaId")
    void updateAddress(@Param("cinemaId") Integer cinemaId, @Param("address") String address);

    @Query(value = "SELECT c FROM Cinema c WHERE c.city = :city AND c.name = :name")
    List<Cinema> findCinemasByCityAndName(@Param("city") String city, @Param("name") String name);

    @Modifying
    @Transactional
    @Query("delete from Cinema c where c.city = :city")
    void deleteCinemasByCity(@Param("city") String city);

    @Query("SELECT c FROM Cinema c WHERE c.city LIKE %:keyword% OR c.name LIKE %:keyword% OR c.address LIKE %:keyword%")
    List<Cinema> searchCinemas(@Param("keyword") String keyword);
}