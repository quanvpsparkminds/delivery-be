package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.component.JwtUtil;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.AuthResponse;
import net.sparkminds.delivery.response.RestaurantResponse;
import net.sparkminds.delivery.response.UserResponse;
import net.sparkminds.delivery.service.AuthService;
import net.sparkminds.delivery.service.DeliveryService;
import net.sparkminds.delivery.service.RestaurantService;
import net.sparkminds.delivery.service.UserService;
import net.sparkminds.delivery.service.dto.Auth.LoginRequest;
import net.sparkminds.delivery.service.dto.Auth.RefreshTokenRequest;
import net.sparkminds.delivery.service.dto.Delivery.RegisterDeliveryRequest;
import net.sparkminds.delivery.service.dto.User.RegisterRequest;
import net.sparkminds.delivery.service.dto.Restaurant.RegisterRestaurantRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthService authService;
    private final RestaurantService restaurantService;
    private final DeliveryService deliveryService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(@RequestBody RefreshTokenRequest request) {
        String email = jwtUtil.extractEmail(request.getRefreshToken());
        String accessToken = jwtUtil.generateToken(email);
        String refreshToken = jwtUtil.generateRefreshToken(email);
        AuthResponse response = new AuthResponse(accessToken, refreshToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(
            @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(userService.register(request)));
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<ApiResponse<AuthResponse>> registerRestaurant(@Valid @RequestBody RegisterRestaurantRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(restaurantService.registerRestaurant(request)));
    }

    @PostMapping("/register/delivery")
    public ResponseEntity<ApiResponse<AuthResponse>> registerDelivery(@Valid @RequestBody RegisterDeliveryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(deliveryService.registerDelivery(request)));
    }
}
