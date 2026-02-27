package net.sparkminds.delivery.service.dto.Menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import net.sparkminds.delivery.enums.EFood;

@Data
public class CreateMenuRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private Float price;

    private Float originPrice;

    @NotNull
    private EFood type;

    private String image;
}
