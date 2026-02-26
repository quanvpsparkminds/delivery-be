package net.sparkminds.delivery.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull
    private Long restaurantId;

    private Float deliveryFee;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private List<OrderItemRequest> items;
}