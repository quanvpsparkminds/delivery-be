package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.mapper.OrderMapper;
import net.sparkminds.delivery.model.Delivery;
import net.sparkminds.delivery.model.Order;
import net.sparkminds.delivery.model.Restaurant;
import net.sparkminds.delivery.repository.DeliveryRepository;
import net.sparkminds.delivery.repository.OrderRepository;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.response.OrderResponse;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final DeliveryRepository deliveryRepository;
    private final RestaurantRepository restaurantRepository;

    public List<OrderResponse> history() {
        List<OrderResponse> result = new ArrayList<>();

        String email = SecurityUtil.getCurrentUserEmail();
        Delivery delivery = deliveryRepository.findByEmail(email).orElse(null);
        Restaurant restaurant = restaurantRepository.findByEmail(email).orElse(null);
        if (delivery != null) {
            List<Order> orders = orderRepository.findByDelivery(delivery.getId()).orElse(List.of());
            if (!orders.isEmpty()) {
                result.addAll(orderMapper.toDtoList(orders));
            }
        } else if (restaurant != null) {
            List<Order> orders = orderRepository.findByRestaurant(restaurant.getId()).orElse(List.of());
            if (!orders.isEmpty()) {
                result.addAll(orderMapper.toDtoList(orders));
            }
        }

        return result;
    }
}
