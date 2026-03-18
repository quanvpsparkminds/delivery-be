package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
