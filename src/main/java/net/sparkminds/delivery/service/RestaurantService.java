package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.Specification.OrderSpecification;
import net.sparkminds.delivery.Specification.RestaurantSpecification;
import net.sparkminds.delivery.component.JwtUtil;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.mapper.RestaurantMapper;
import net.sparkminds.delivery.model.Order;
import net.sparkminds.delivery.model.Restaurant;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.response.AuthResponse;
import net.sparkminds.delivery.response.RestaurantResponse;
import net.sparkminds.delivery.service.dto.Restaurant.GetRestaurantRequest;
import net.sparkminds.delivery.service.dto.Restaurant.RegisterRestaurantRequest;
import net.sparkminds.delivery.service.dto.Restaurant.UpdateLocationRequest;
import net.sparkminds.delivery.service.dto.Restaurant.UpdateRestaurantRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantMapper restaurantMapper;
    private final JwtUtil jwtUtil;


    public AuthResponse registerRestaurant(RegisterRestaurantRequest request) {
        if (restaurantRepository.existsByEmail(request.getEmail()) || userRepository.existsByEmail(request.getEmail())) {
            throw new BaseException("EMAIL_EXISTS", "Email already exists", HttpStatus.BAD_REQUEST);
        }

        Restaurant restaurant = restaurantMapper.toEntity(request, passwordEncoder.encode(request.getPassword()));
        restaurantRepository.save(restaurant);

        return new AuthResponse(jwtUtil.generateToken(request.getEmail()), jwtUtil.generateRefreshToken(request.getEmail()));
    }

    public RestaurantResponse updateRestaurant(UpdateRestaurantRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Restaurant restaurant = restaurantRepository.findByEmail(email).orElseThrow(() -> new BaseException("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND));

        Optional.ofNullable(request.getFullName()).ifPresent(restaurant::setFullName);
        Optional.ofNullable(request.getPhoneCode()).ifPresent(restaurant::setPhoneCode);
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(restaurant::setPhoneNumber);
        Optional.ofNullable(request.getCountryId()).ifPresent(restaurant::setCountryId);
        Optional.ofNullable(request.getCityId()).ifPresent(restaurant::setCityId);
        Optional.ofNullable(request.getAddress()).ifPresent(restaurant::setAddress);
        Optional.ofNullable(request.getPostCode()).ifPresent(restaurant::setPostCode);
        Optional.ofNullable(request.getImage()).ifPresent(restaurant::setImage);

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponse(updatedRestaurant);
    }

    public RestaurantResponse getRestaurantMe() {
        String email = SecurityUtil.getCurrentUserEmail();
        Restaurant restaurant = restaurantRepository
                .findByEmail(email)
                .orElseThrow(() -> new BaseException("USER_NOT_FOUND"
                        , "User not found"
                        , HttpStatus.NOT_FOUND));

        return restaurantMapper.toResponse(restaurant);
    }

    public List<RestaurantResponse> getRestaurant(GetRestaurantRequest request) {
        Specification<Restaurant> spec = Specification.where(RestaurantSpecification.hasCity(request.getCityId())).and(RestaurantSpecification.hasType(request.getType())).and(RestaurantSpecification.hasCountry(request.getCountryId())).and(RestaurantSpecification.fullName(request.getFullName())).and(RestaurantSpecification.address(request.getAddress()));
        List<Restaurant> restaurants = restaurantRepository.findAll(spec);

        return restaurantMapper.toResponseList(restaurants);
    }

    public RestaurantResponse updateLocation(UpdateLocationRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Restaurant restaurant = restaurantRepository
                .findByEmail(email)
                .orElseThrow(() -> new BaseException("RESTAURANT_NOT_FOUND"
                        , "Restaurant not found"
                        , HttpStatus.NOT_FOUND));


        restaurant.setLat(request.getLat());
        restaurant.setLng(request.getLng());

        Restaurant restaurantRp = restaurantRepository.save(restaurant);

        return restaurantMapper.toResponse(restaurantRp);

    }
}