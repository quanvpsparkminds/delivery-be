package net.sparkminds.delivery.service;

import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.mapper.UserMapper;
import net.sparkminds.delivery.model.User;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.response.UserResponse;
import net.sparkminds.delivery.service.dto.RegisterRequest;
import net.sparkminds.delivery.service.dto.UpdateUserRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RestaurantRepository restaurantRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.restaurantRepository = restaurantRepository;
        this.userMapper = userMapper;
    }

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())
                || restaurantRepository.existsByEmail(request.getEmail())) {
            throw new BaseException("EMAIL_EXISTS", "Email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.toEntity(request, passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return userMapper.toResponse(user);
    }

    public UserResponse updateUser(UpdateUserRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND));


        Optional.ofNullable(request.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(request.getBirthday()).ifPresent(user::setBirthday);
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(user::setPhoneNumber);

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }
}