package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.AuthResponse;
import net.sparkminds.delivery.response.DeliveryResponse;
import net.sparkminds.delivery.service.DeliveryService;
import net.sparkminds.delivery.service.dto.Delivery.OnboardingDeliveryRequest;
import net.sparkminds.delivery.service.dto.Delivery.RegisterDeliveryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<DeliveryResponse>> me() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(deliveryService.me()));
    }


    @PutMapping("/onBoarding")
    public ResponseEntity<ApiResponse<DeliveryResponse>> onBoarding(@Valid @RequestBody OnboardingDeliveryRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(deliveryService.onBoarding(request)));
    }
}
