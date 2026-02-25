package net.sparkminds.delivery.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import net.sparkminds.delivery.enums.EFood;

@Data
public class CreateMenuRequest {
    @NotNull
    private Long restaurantId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private Float price;

    private Float originPrice;

    @NotNull
    private EFood type;

    private String image;
}
