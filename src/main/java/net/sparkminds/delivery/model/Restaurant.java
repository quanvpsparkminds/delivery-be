package net.sparkminds.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import net.sparkminds.delivery.enums.ERestaurant;

@Entity
@Table(
        name = "t_restaurant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)

@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "full_name", nullable = false, length = 256)
    private String fullName;

    @Column(name = "phone_code", nullable = false)
    private String phoneCode;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "country_id", nullable = false)
    private int countryId;

    @Column(name = "city_id", nullable = false)
    private int cityId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "postCode")
    private String postCode;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERestaurant type;

    @Column(name = "image")
    private String image;

    @Column(name = "lng")
    private String lng;

    @Column(name = "lat")
    private String lat;


    public Restaurant(Long id,
                      String email,
                      String password,
                      String fullName,
                      String phoneCode,
                      String phoneNumber,
                      int countryId,
                      int cityId,
                      String address,
                      String postCode,
                      ERestaurant type,
                      String image,
                      String lng,
                      String lat
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.address = address;
        this.postCode = postCode;
        this.type = type;
        this.image = image;
        this.lng = lng;
        this.lat = lat;
    }

    public Restaurant() {
    }
}
