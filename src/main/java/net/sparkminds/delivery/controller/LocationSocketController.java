package net.sparkminds.delivery.controller;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.service.DeliveryLocationService;
import net.sparkminds.delivery.service.dto.Delivery.LocationDeliveryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LocationSocketController {

    private final DeliveryLocationService deliveryLocationService;

    @MessageMapping("/shipper/location")
    public void updateLocation(LocationDeliveryRequest request) {
        deliveryLocationService.updateLocation(request);
    }

    @MessageMapping("/shipper/location/remove")
    public void removeLocation(Long id) {
        deliveryLocationService.removeShipper(id);
    }
}
