package net.sparkminds.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import net.sparkminds.delivery.enums.EDeliveryStatus;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "t_delivery",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_code", nullable = false)
    private String phoneCode;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EDeliveryStatus status;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "city_id")
    private int cityId;

    @Column(name = "address")
    private String address;

    @Column(name = "postCode")
    private String postCode;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "current_lng")
    private String currentLng;

    @Column(name = "current_Lat")
    private String current_Lat;

    @Column(name = "is_onboarding")
    private boolean isOnboarding;
}
