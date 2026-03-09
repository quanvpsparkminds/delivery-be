package net.sparkminds.delivery.service.dto.Router;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RouterRequest {
    @NotNull
    private Float startLng;

    @NotNull
    private Float startLat;

    @NotNull
    private Float endLng;

    @NotNull
    private Float endLat;
}
