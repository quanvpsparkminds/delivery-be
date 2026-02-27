package net.sparkminds.delivery.service.dto.Menu;

import lombok.*;
import net.sparkminds.delivery.enums.EFood;

@Data
public class GetMenuRequest {
    private Long restaurantId;
    private EFood type;
}
