package net.sparkminds.delivery.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String phoneCode;
    private String phoneNumber;
    private String image;

    public UserResponse(Long id, String firstName, String lastName, String email, LocalDate birthday, String phoneCode, String phoneNumber, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public UserResponse() {
    }
}
