package net.sparkminds.delivery.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.RestaurantResponse;
import net.sparkminds.delivery.response.UserResponse;
import net.sparkminds.delivery.service.RestaurantService;
import net.sparkminds.delivery.service.dto.RegisterRestaurantRequest;
import net.sparkminds.delivery.service.dto.UpdateRestaurantRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PutMapping()
    private ResponseEntity<ApiResponse<RestaurantResponse>> updateRestaurant(@Valid @RequestBody UpdateRestaurantRequest request) {
        RestaurantResponse response = restaurantService.updateRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }
}
