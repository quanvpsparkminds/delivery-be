package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import net.sparkminds.delivery.model.Delivery;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.service.DeliveryService;
import net.sparkminds.delivery.service.dto.CreateDeliveryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // POST /api/deliveries
    @PostMapping
    public ResponseEntity<ApiResponse<Delivery>> createDelivery(@Valid @RequestBody CreateDeliveryRequest request) {
        Delivery delivery = deliveryService.createDelivery(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(delivery));
    }

    // GET /api/deliveries
    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    // GET /api/deliveries/{id}
    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }
}
