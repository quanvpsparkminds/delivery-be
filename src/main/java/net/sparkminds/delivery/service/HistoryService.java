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
import net.sparkminds.delivery.response.PageResponse;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public PageResponse<OrderResponse> history(Pageable pageable) {

        String email = SecurityUtil.getCurrentUserEmail();

        Delivery delivery = deliveryRepository.findByEmail(email).orElse(null);
        Restaurant restaurant = restaurantRepository.findByEmail(email).orElse(null);

        Page<Order> orderPage;

        if (delivery != null) {
            orderPage = orderRepository.findByDelivery(delivery.getId(), pageable);
        } else if (restaurant != null) {
            orderPage = orderRepository.findByRestaurant(restaurant.getId(), pageable);
        } else {
            orderPage = Page.empty(pageable);
        }

        // map sang DTO
        Page<OrderResponse> dtoPage = orderPage.map(orderMapper::toDto);

        return PageResponse.<OrderResponse>builder()
                .content(dtoPage.getContent())
                .page(dtoPage.getNumber())
                .size(dtoPage.getSize())
                .totalElements(dtoPage.getTotalElements())
                .totalPages(dtoPage.getTotalPages())
                .build();
    }
}
