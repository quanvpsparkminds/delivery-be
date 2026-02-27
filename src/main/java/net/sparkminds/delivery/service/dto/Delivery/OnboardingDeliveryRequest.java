package net.sparkminds.delivery.service.dto.Delivery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OnboardingDeliveryRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private LocalDateTime birthday;

    @NotNull
    private Integer countryId;

    @NotNull
    private Integer cityId;

    @NotBlank
    private String address;

    private String postCode;

    @NotBlank
    private String avatar;
}
