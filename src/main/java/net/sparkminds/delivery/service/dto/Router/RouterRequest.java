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

    public RouterRequest() {
    }

    public RouterRequest(Float startLng, Float startLat, Float endLng, Float endLat) {
        this.startLng = startLng;
        this.startLat = startLat;
        this.endLng = endLng;
        this.endLat = endLat;
    }
}
