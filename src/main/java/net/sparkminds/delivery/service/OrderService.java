package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.Specification.OrderSpecification;
import net.sparkminds.delivery.enums.EOrderStatus;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.model.*;
import net.sparkminds.delivery.repository.MenuRepository;
import net.sparkminds.delivery.repository.OrderRepository;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.service.dto.CreateOrderRequest;
import net.sparkminds.delivery.service.dto.GetOrderRequest;
import net.sparkminds.delivery.service.dto.OrderItemRequest;
import net.sparkminds.delivery.service.dto.UpdateStatusOrderRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;


    public Order createOrder(CreateOrderRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new BaseException(
                        "RESTAURANT_NOT_FOUND",
                        "Restaurant not found",
                        HttpStatus.NOT_FOUND
                ));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(
                        "USER_NOT_FOUND",
                        "User not found",
                        HttpStatus.NOT_FOUND
                ));

        Order order = new Order();
        order.setRestaurant(restaurant);
        order.setUser(user);
        order.setDeliveryFee(request.getDeliveryFee());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setStatus(EOrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItems> orderItems = new ArrayList<>();

        float total = 0f;
        float deliveryFee = request.getDeliveryFee() == null
                ? 0f
                : request.getDeliveryFee();

        for (OrderItemRequest itemReq : request.getItems()) {

            Menu menu = menuRepository.findById(itemReq.getIdMenu()).orElseThrow(() -> new BaseException("MENU_NOT_FOUND",
                    "Menu not found", HttpStatus.NOT_FOUND));


            OrderItems item = new OrderItems();
            item.setName(menu.getName());
            item.setPrice(menu.getPrice());
            item.setOrder(order);
            total += menu.getPrice();
            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total + deliveryFee);

        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    public List<Order> getOrder(GetOrderRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Optional<Restaurant> restaurantOpt = restaurantRepository.findByEmail(email);
        Optional<User> userOpt = userRepository.findByEmail(email);

        Long restaurantId = restaurantOpt.map(Restaurant::getId).orElse(null);
        Long userId = userOpt.map(User::getId).orElse(null);

        Specification<Order> spec = Specification
                .where(OrderSpecification.hasRestaurant(restaurantId))
                .and(OrderSpecification.hasUser(userId))
                .and(OrderSpecification.status(request.getStatus()));

        return orderRepository.findAll(spec);
    }

    @Transactional
    public Order updateStatus(UpdateStatusOrderRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Optional<Restaurant> restaurantOpt = restaurantRepository.findByEmail(email);
        Optional<User> userOpt = userRepository.findByEmail(email);

        Long restaurantId = restaurantOpt.map(Restaurant::getId).orElse(null);
        Long userId = userOpt.map(User::getId).orElse(null);

        if ((restaurantId != null && request.getStatus() == EOrderStatus.CANCELLED)
                || (userId != null && request.getStatus() != EOrderStatus.CANCELLED)) {
            throw new BaseException("NO_PERMISSION", "No permission", HttpStatus.BAD_REQUEST);
        }

        Order order = orderRepository.findById(request
                        .getOrderId())
                .orElseThrow(() -> new BaseException(
                        "ORDER_NOT_FOUND",
                        "Order not found",
                        HttpStatus.BAD_REQUEST
                ));

        order.setStatus(request.getStatus());

        return orderRepository.save(order);
    }

}
