package net.sparkminds.delivery.model;

import jakarta.persistence.*;
import lombok.Data;
import net.sparkminds.delivery.enums.EOrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "t_order"
)
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> items = new ArrayList<>();

    @Column
    private Float deliveryFee;

    @Column
    private Float totalAmount;

    @Column
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private EOrderStatus status;

    @Column
    private LocalDateTime createdAt;
}
