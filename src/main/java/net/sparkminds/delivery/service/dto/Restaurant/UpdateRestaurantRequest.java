package net.sparkminds.delivery.service.dto.Restaurant;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateRestaurantRequest {
    @Size(max = 256)
    private String fullName;

    private String phoneCode;

    private String phoneNumber;

    private int countryId;

    private int cityId;

    private String address;

    private String postCode;

    private String image;
}
