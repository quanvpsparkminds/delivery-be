package net.sparkminds.delivery.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String phoneNumber;

    public UserResponse(String firstName, String lastName, String email, LocalDate birthday, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

    public UserResponse() {
    }
}
