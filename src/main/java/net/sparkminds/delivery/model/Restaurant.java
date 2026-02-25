package net.sparkminds.delivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false, length = 256)
    private String fullName;

    @Column(name = "number_code", nullable = false)
    private String numberCode;

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


    public Restaurant(String email, String password, String fullName, String numberCode, String phoneNumber, int countryId, int cityId, String address, String postCode) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.numberCode = numberCode;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.address = address;
        this.postCode = postCode;
    }

    public Restaurant() {
    }
}
