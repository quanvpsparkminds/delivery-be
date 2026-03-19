package net.sparkminds.delivery.controller;


import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.response.NotificationResponse;
import net.sparkminds.delivery.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getNotification() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(notificationService.getNotification()));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Void>> real(@PathVariable Long id) {
        notificationService.read(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }

    @PutMapping("/read/all")
    public ResponseEntity<ApiResponse<Void>> realAll() {
        notificationService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null));
    }
}
