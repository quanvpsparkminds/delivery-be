package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.component.JwtUtil;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.model.User;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.response.AuthResponse;
import net.sparkminds.delivery.service.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BaseException(
                        "UNAUTHORIZED",
                        "Invalid email or password",
                        HttpStatus.UNAUTHORIZED
                ));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new BaseException(
                    "UNAUTHORIZED",
                    "Invalid email or password",
                    HttpStatus.UNAUTHORIZED
            );
        }
        AuthResponse response = new AuthResponse(jwtUtil.generateToken(user.getEmail()), jwtUtil.generateRefreshToken(user.getEmail()));
        return response;
    }
}
