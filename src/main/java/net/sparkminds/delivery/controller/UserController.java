package net.sparkminds.delivery.controller;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.UserResponse;
import net.sparkminds.delivery.service.UserService;
import net.sparkminds.delivery.service.dto.User.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody RegisterRequest.UpdateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(userService.updateUser(request)));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> me(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(userService.getUserMe()));
    }
}
