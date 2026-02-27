package net.sparkminds.delivery.mapper;

import net.sparkminds.delivery.model.Order;
import net.sparkminds.delivery.response.OrderItemResponse;
import net.sparkminds.delivery.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderResponse toDto(Order order) {
        List<OrderItemResponse> itemDtos = order.getItems().stream()
                .map(item -> OrderItemResponse.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .price(item.getPrice())
                        .build())
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .restaurantName(order.getRestaurant().getFullName())
                .userName(order.getUser().getEmail())
                .items(itemDtos)
                .deliveryFee(order.getDeliveryFee())
                .totalAmount(order.getTotalAmount())
                .deliveryAddress(order.getDeliveryAddress())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public List<OrderResponse> toDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
