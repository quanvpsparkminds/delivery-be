package net.sparkminds.delivery.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantResponse {
    private UUID id;

    private String email;

    private String fullName;

    private String numberCode;

    private String phoneNumber;

    private Number countryId;

    private Number cityId;

    private String address;

    private String postCode;

    public RestaurantResponse(UUID id, String email, String fullName, String numberCode, String phoneNumber, Number countryId, Number cityId, String address, String postCode) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.numberCode = numberCode;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.address = address;
        this.postCode = postCode;
    }

    public RestaurantResponse() {
    }
}
