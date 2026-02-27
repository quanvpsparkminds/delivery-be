package net.sparkminds.delivery.service.dto.Delivery;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDeliveryRequest {
    @NotBlank
    private String phoneCode;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
