package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.NotificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotificationToken, Long> {

    Optional<NotificationToken> findByToken(String token);
}
