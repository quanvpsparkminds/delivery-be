package net.sparkminds.delivery.service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRestaurantRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(max = 256)
    private String fullName;

    @NotBlank
    private String phoneCode;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private int countryId;

    @NotNull
    private int cityId;

    @NotBlank
    private String address;

    private String postCode;
}
