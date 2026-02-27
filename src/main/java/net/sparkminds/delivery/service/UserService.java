package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.component.JwtUtil;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.mapper.UserMapper;
import net.sparkminds.delivery.model.User;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.response.AuthResponse;
import net.sparkminds.delivery.response.UserResponse;
import net.sparkminds.delivery.service.dto.User.RegisterRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;


    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())
                || restaurantRepository.existsByEmail(request.getEmail())) {
            throw new BaseException("EMAIL_EXISTS", "Email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.toEntity(request, passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return new AuthResponse(jwtUtil.generateToken(request.getEmail()), jwtUtil.generateRefreshToken(request.getEmail()));
    }

    public UserResponse getUserMe() {
        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND));

        return userMapper.toResponse(user);
    }

    public UserResponse updateUser(RegisterRequest.UpdateUserRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND));


        Optional.ofNullable(request.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(request.getBirthday()).ifPresent(user::setBirthday);
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(user::setPhoneNumber);
        Optional.ofNullable(request.getImage()).ifPresent(user::setImage);

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }
}