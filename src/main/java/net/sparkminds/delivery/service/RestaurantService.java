package net.sparkminds.delivery.service;

import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.mapper.RestaurantMapper;
import net.sparkminds.delivery.model.Restaurant;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.response.RestaurantResponse;
import net.sparkminds.delivery.service.dto.RegisterRestaurantRequest;
import net.sparkminds.delivery.service.dto.UpdateRestaurantRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantResponse registerRestaurant(RegisterRestaurantRequest request) {
        if (restaurantRepository.existsByEmail(request.getEmail())
                || userRepository.existsByEmail(request.getEmail())) {
            throw new BaseException("EMAIL_EXISTS", "Email already exists", HttpStatus.BAD_REQUEST);
        }

        Restaurant restaurant = restaurantMapper.toEntity(request, passwordEncoder.encode(request.getPassword()));
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return restaurantMapper.toResponse(savedRestaurant);
    }

    public RestaurantResponse updateRestaurant(UpdateRestaurantRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Restaurant restaurant = restaurantRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND));

        Optional.ofNullable(request.getFullName()).ifPresent(restaurant::setFullName);
        Optional.ofNullable(request.getPhoneCode()).ifPresent(restaurant::setPhoneCode);
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(restaurant::setPhoneNumber);
        Optional.ofNullable(request.getCountryId()).ifPresent(restaurant::setCountryId);
        Optional.ofNullable(request.getCityId()).ifPresent(restaurant::setCityId);
        Optional.ofNullable(request.getAddress()).ifPresent(restaurant::setAddress);
        Optional.ofNullable(request.getPostCode()).ifPresent(restaurant::setPostCode);

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponse(updatedRestaurant);
    }
}