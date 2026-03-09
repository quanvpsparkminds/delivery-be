package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.DistanceResponse;
import net.sparkminds.delivery.service.RouterService;
import net.sparkminds.delivery.service.dto.Router.RouterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/router")
@RequiredArgsConstructor
public class RouterController {
    private final RouterService routerService;

    @GetMapping("/distance")
    private ResponseEntity<ApiResponse<DistanceResponse>> getDistance(@Valid @RequestBody RouterRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse
                        .success(routerService
                                .getDistance(request)));
    }
}
