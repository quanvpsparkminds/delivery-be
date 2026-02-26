package net.sparkminds.delivery.controller;

import net.sparkminds.delivery.enums.EFood;
import net.sparkminds.delivery.enums.EOrderStatus;
import net.sparkminds.delivery.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class MasterDataController {

    @GetMapping("/foods")
    public ResponseEntity<ApiResponse<?>> getFoodTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(EFood.values()));
    }

    @GetMapping("/order/status")
    public ResponseEntity<ApiResponse<?>> getOrderStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(EOrderStatus.values()));
    }
}
