package net.sparkminds.delivery.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.sparkminds.delivery.enums.ELogin;

@Data
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private ELogin mode;
}
