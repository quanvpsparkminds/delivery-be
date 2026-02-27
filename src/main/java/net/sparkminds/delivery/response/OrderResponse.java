package net.sparkminds.delivery.response;

import lombok.Builder;
import lombok.Data;
import net.sparkminds.delivery.enums.EOrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderResponse {
    private UUID id;
    private String restaurantName;
    private String userName;
    private List<OrderItemResponse> items;
    private Float deliveryFee;
    private Float totalAmount;
    private String deliveryAddress;
    private EOrderStatus status;
    private LocalDateTime createdAt;
}
