package net.sparkminds.delivery.service;

import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.model.User;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.service.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BaseException(
                    "EMAIL_EXISTS",
                    "Email already exists",
                    HttpStatus.BAD_REQUEST
            );
        }

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getBirthday(),
                request.getPhoneNumber(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);
    }
}
