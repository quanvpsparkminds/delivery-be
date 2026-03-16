package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Optional<Delivery> findByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
