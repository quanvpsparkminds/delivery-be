package net.sparkminds.delivery.service.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private LocalDate birthday;

    @NotBlank
    private String phoneCode;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Size(min = 6)
    private String password;

    private String image;

    @Data
    public static class UpdateUserRequest {
        private String firstName;

        private String lastName;

        private LocalDate birthday;

        private String phoneNumber;

        private String image;
    }
}
