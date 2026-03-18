package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, JpaSpecificationExecutor<Order> {
    @Query("SELECT DISTINCT o FROM Order o\n" +
            "LEFT JOIN FETCH o.items\n" +
            "LEFT JOIN FETCH o.user\n" +
            "LEFT JOIN FETCH o.restaurant\n" +
            "LEFT JOIN FETCH o.delivery\n" +
            "WHERE o.id = :id")
    Optional<Order> findByIdWithItems(@Param("id") UUID id);
}
