package net.sparkminds.delivery.service.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {
    @NotNull
    private Long idMenu;
}
