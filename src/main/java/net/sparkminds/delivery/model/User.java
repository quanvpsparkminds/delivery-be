package net.sparkminds.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(
        name = "t_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_code")
    private String phoneCode;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "image")
    private String image;

    public User() {
    }

    public User(String firstName, String lastName, String email, LocalDate birthday, String phoneNumber, String password, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.image = image;
    }
}

