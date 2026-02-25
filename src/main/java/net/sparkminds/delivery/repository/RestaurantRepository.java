package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.Restaurant;
import net.sparkminds.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByEmail(String email);

    boolean existsByEmail(String email);
}
