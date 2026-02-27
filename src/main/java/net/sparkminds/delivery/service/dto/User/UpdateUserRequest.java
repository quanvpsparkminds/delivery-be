package net.sparkminds.delivery.service.dto.User;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String phoneNumber;

    private String image;
}
