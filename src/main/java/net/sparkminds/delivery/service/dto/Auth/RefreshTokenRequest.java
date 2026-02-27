package net.sparkminds.delivery.service.dto.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;
}
