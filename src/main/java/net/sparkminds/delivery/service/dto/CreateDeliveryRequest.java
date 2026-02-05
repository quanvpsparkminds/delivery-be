package net.sparkminds.delivery.service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeliveryRequest {
    @NotBlank(message = "Customer name is Required")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 character")
    private String customerName;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;
}
