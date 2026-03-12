
package net.sparkminds.delivery.service.dto.Delivery;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LocationDeliveryRequest {
    @NotNull
    private Float lat;

    @NotNull
    private Float lng;

    @NotNull
    private String id;
}
