package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.Country;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @EntityGraph(attributePaths = "cities")
    @Query("select c from Country c")
    List<Country> findAllWithCities();
}
