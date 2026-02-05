package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.component.JwtUtil;
import net.sparkminds.delivery.model.Delivery;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.service.UserService;
import net.sparkminds.delivery.service.dto.AuthResponse;
import net.sparkminds.delivery.service.dto.LoginRequest;
import net.sparkminds.delivery.service.dto.RegisterRequest;
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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        String accessToken = jwtUtil.generateToken(request.getUserName());
        String refreshToken = jwtUtil.generateRefreshToken(request.getUserName());
        AuthResponse response = new AuthResponse(accessToken, refreshToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(@RequestBody String request) {
        System.out.println(request);
        String userName = jwtUtil.extractUsername(request);
        String accessToken = jwtUtil.generateToken(userName);
        String refreshToken = jwtUtil.generateRefreshToken(userName);
        AuthResponse response = new AuthResponse(accessToken, refreshToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @Valid @RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(null));
    }
}
