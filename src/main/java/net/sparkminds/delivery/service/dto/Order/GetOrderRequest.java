package net.sparkminds.delivery.service.dto.Order;

import lombok.Data;
import net.sparkminds.delivery.enums.EOrderStatus;

@Data
public class GetOrderRequest {
    private EOrderStatus status;
}
