package net.sparkminds.delivery.service.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.sparkminds.delivery.enums.EOrderStatus;

@Data
public class UpdateStatusOrderRequest {

    @NotNull
    private EOrderStatus status;
}
