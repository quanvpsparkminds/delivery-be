package net.sparkminds.delivery.controller;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.DeliveryResponse;
import net.sparkminds.delivery.response.OrderResponse;
import net.sparkminds.delivery.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> history() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(historyService.history()));
    }
}
