package net.sparkminds.delivery.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String phoneNumber;
}
