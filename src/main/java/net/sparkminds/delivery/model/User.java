package net.sparkminds.delivery.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(
        name = "users",
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

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private LocalDate birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String email, LocalDate birthday, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}

