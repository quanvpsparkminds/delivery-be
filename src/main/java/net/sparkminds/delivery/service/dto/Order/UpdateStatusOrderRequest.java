package net.sparkminds.delivery.service.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.sparkminds.delivery.enums.EOrderStatus;

import java.util.UUID;

@Data
public class UpdateStatusOrderRequest {
    @NotNull
    private UUID orderId;

    @NotNull
    private EOrderStatus status;
}
