package net.sparkminds.delivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "Restaurant",
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


    public Restaurant(String email, String password, String fullName, String phoneCode, String phoneNumber, int countryId, int cityId, String address, String postCode) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.address = address;
        this.postCode = postCode;
    }

    public Restaurant() {
    }
}
