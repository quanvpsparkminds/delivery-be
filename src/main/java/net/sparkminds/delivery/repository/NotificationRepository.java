package net.sparkminds.delivery.repository;

import net.sparkminds.delivery.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


    @Query("SELECT DISTINCT o FROM Notification o\n" +
            "LEFT JOIN FETCH o.user\n" +
            "WHERE o.user.id = :id")
    List<Notification> findByUser(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Notification o SET o.isSeen = true WHERE o.user.id = :userId")
    void markAllAsSeen(@Param("userId") Long userId);
}
