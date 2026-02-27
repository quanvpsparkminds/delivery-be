package net.sparkminds.delivery.service.dto.Restaurant;

import lombok.Data;
import net.sparkminds.delivery.enums.ERestaurant;

@Data
public class GetRestaurantRequest {
    private ERestaurant type;
    private String fullName;
    private String address;
    private int countryId;
    private int cityId;
}
