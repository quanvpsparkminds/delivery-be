package net.sparkminds.delivery.service;

import net.sparkminds.delivery.exception.DeliveryNotFoundException;
import net.sparkminds.delivery.model.Delivery;
import net.sparkminds.delivery.service.dto.CreateDeliveryRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {
    private final List<Delivery> deliveries = new ArrayList<>();
    private Long currentId = 1L;

    // Tạo delivery
    public Delivery createDelivery(CreateDeliveryRequest request) {
        Delivery delivery = new Delivery();
        delivery.setId(currentId++);
        delivery.setCustomerName(request.getCustomerName());
        delivery.setAddress(request.getAddress());
        delivery.setStatus("CREATED");

        deliveries.add(delivery);
        return delivery;
    }

    // Lấy tất cả delivery
    public List<Delivery> getAllDeliveries() {
        return deliveries;
    }

    // Lấy delivery theo id
    public Delivery getDeliveryById(Long id) {
        return deliveries.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DeliveryNotFoundException(id));
    }
}
