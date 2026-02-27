package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.component.JwtUtil;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.model.Delivery;
import net.sparkminds.delivery.model.Restaurant;
import net.sparkminds.delivery.model.User;
import net.sparkminds.delivery.repository.DeliveryRepository;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.response.AuthResponse;
import net.sparkminds.delivery.service.dto.Auth.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final DeliveryRepository deliveryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {

        String email = request.getEmail();
        String rawPassword = request.getPassword();

        String encodedPassword;
        String subject; // email sẽ đưa vào JWT

        switch (request.getMode()) {
            case USER -> {
                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> unauthorized());

                encodedPassword = user.getPassword();
                subject = user.getEmail();
            }
            case RESTAURANT -> {
                Restaurant restaurant = restaurantRepository.findByEmail(email)
                        .orElseThrow(() -> unauthorized());
                encodedPassword = restaurant.getPassword();
                subject = restaurant.getEmail();
            }
            case DELIVERY -> {
                Delivery delivery = deliveryRepository.findByEmail(email)
                        .orElseThrow(() -> unauthorized());
                encodedPassword = delivery.getPassword();
                subject = delivery.getEmail();
            }
            default -> throw unauthorized();
        }
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw unauthorized();
        }

        return new AuthResponse(
                jwtUtil.generateToken(subject),
                jwtUtil.generateRefreshToken(subject)
        );
    }

    private BaseException unauthorized() {
        return new BaseException(
                "UNAUTHORIZED",
                "Invalid email or password",
                HttpStatus.UNAUTHORIZED
        );
    }
}