package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    Optional<Restaurant> findByEmail(String email);

    boolean existsByEmail(String email);
}
