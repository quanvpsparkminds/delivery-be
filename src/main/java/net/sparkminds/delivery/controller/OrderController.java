package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.model.Menu;
import net.sparkminds.delivery.model.Order;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.service.OrderService;
import net.sparkminds.delivery.service.dto.CreateOrderRequest;
import net.sparkminds.delivery.service.dto.GetOrderRequest;
import net.sparkminds.delivery.service.dto.UpdateStatusOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponse<List<Order>>> getOrder(GetOrderRequest request) {
        List<Order> orders = orderService.getOrder(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(orders));
    }

    @PostMapping("/status")
    public ResponseEntity<ApiResponse<Order>> updateStatus(@Valid @RequestBody UpdateStatusOrderRequest request) {
        Order order = orderService.updateStatus(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(order));
    }
}