package net.sparkminds.delivery.response;

import lombok.Data;
import net.sparkminds.delivery.enums.ERestaurant;

@Data
public class RestaurantResponse {
    private String email;

    private String fullName;

    private String phoneCode;

    private String phoneNumber;

    private Number countryId;

    private Number cityId;

    private String address;

    private String postCode;

    private ERestaurant type;

    private String image;

    public RestaurantResponse(String email, String fullName, String phoneCode, String phoneNumber, Number countryId, Number cityId, String address, String postCode, ERestaurant type, String image) {
        this.email = email;
        this.fullName = fullName;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.address = address;
        this.postCode = postCode;
        this.type = type;
        this.image = image;
    }

    public RestaurantResponse() {
    }
}
