package net.sparkminds.delivery.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.RestaurantResponse;
import net.sparkminds.delivery.service.RestaurantService;
import net.sparkminds.delivery.service.dto.Restaurant.GetRestaurantRequest;
import net.sparkminds.delivery.service.dto.Restaurant.UpdateRestaurantRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurant API", description = "Manage restaurants")
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PutMapping
    private ResponseEntity<ApiResponse<RestaurantResponse>> updateRestaurant(@Valid @RequestBody UpdateRestaurantRequest request) {
        RestaurantResponse response = restaurantService.updateRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    @GetMapping
    private ResponseEntity<ApiResponse<List<RestaurantResponse>>> getRestaurant(GetRestaurantRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(restaurantService.getRestaurant(request)));
    }

    @GetMapping("/me")
    private ResponseEntity<ApiResponse<RestaurantResponse>> getRestaurantMe(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(restaurantService.getRestaurantMe()));
    }
}
