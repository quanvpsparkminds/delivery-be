package net.sparkminds.delivery.response;

import lombok.Data;

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

    public RestaurantResponse(String email, String fullName, String phoneCode, String phoneNumber, Number countryId, Number cityId, String address, String postCode) {
        this.email = email;
        this.fullName = fullName;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.address = address;
        this.postCode = postCode;
    }

    public RestaurantResponse() {
    }
}
