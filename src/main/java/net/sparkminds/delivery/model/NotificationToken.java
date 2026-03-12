package net.sparkminds.delivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "t_notification_token"
)
@Data
public class NotificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
