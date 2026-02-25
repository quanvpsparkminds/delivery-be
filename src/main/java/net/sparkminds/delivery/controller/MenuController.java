package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.RestaurantResponse;
import net.sparkminds.delivery.service.MenuService;
import net.sparkminds.delivery.service.dto.CreateMenuRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;


    @PostMapping("/menu")
    public ResponseEntity<ApiResponse<Void>> createMenu(@Valid @RequestBody CreateMenuRequest request) {
        menuService.createMenu(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(null));
    }

}
