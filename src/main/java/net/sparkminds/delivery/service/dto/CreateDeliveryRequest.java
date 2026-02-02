package net.sparkminds.delivery.service.dto;

import jakarta.validation.constraints.*;

public class CreateDeliveryRequest {
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotBlank(message = "Customer name is Required")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 character")
    private String customerName;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;
}
