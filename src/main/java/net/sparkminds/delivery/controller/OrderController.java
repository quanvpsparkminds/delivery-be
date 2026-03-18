package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.model.Order;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.OrderResponse;
import net.sparkminds.delivery.service.OrderService;
import net.sparkminds.delivery.service.dto.Order.CreateOrderRequest;
import net.sparkminds.delivery.service.dto.Order.GetOrderRequest;
import net.sparkminds.delivery.service.dto.Order.UpdateStatusOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> create(@Valid @RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(order));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrder(GetOrderRequest request) {
        List<OrderResponse> orders = orderService.getOrder(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(orders));
    }

    @PostMapping("/status/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateStatus(@PathVariable UUID id, @Valid @RequestBody UpdateStatusOrderRequest request) {
        OrderResponse order = orderService.updateStatus(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(order));
    }

    @PostMapping("/{id}/shipper")
    public ResponseEntity<ApiResponse<Order>> findShipper(@PathVariable UUID id) {
        Order order = orderService.findShipperAgain(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> byId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(orderService.byId(id)));
    }
}