package net.sparkminds.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import net.sparkminds.delivery.enums.EFood;

@Entity
@Table(
        name = "menu"
)
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "origin_price")
    private Float originPrice;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EFood type;

    @Column(name = "image")
    private String image;

    public Menu() {
    }

    public Menu(String image, EFood type, Float originPrice, Float price, String name, Restaurant restaurant) {
        this.image = image;
        this.type = type;
        this.originPrice = originPrice;
        this.price = price;
        this.name = name;
        this.restaurant = restaurant;
    }
}
