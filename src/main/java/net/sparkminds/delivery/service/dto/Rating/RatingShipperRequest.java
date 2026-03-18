package net.sparkminds.delivery.service.dto.Rating;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RatingShipperRequest {
    @NotNull
    private UUID idOrder;

    @NotNull
    private Long idShipper;

    @NotNull
    private Integer rating;
}
