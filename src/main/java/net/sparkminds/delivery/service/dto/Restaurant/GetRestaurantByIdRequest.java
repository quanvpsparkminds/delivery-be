package net.sparkminds.delivery.service.dto.Restaurant;

import lombok.Data;

@Data
public class GetRestaurantByIdRequest {
    private Float lat;
    private Float lng;
}
