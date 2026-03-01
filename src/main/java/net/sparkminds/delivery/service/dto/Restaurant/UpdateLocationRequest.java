package net.sparkminds.delivery.service.dto.Restaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateLocationRequest {
    @NotBlank
    private String lng;

    @NotBlank
    private String lat;
}
