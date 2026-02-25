package net.sparkminds.delivery.controller;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.UserResponse;
import net.sparkminds.delivery.service.UserService;
import net.sparkminds.delivery.service.dto.UpdateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PutMapping("")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody UpdateUserRequest request) {
        UserResponse response = userService.updateUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }
}
