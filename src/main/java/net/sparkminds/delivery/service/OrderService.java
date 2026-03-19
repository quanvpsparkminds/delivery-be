package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.Specification.OrderSpecification;
import net.sparkminds.delivery.enums.EOrderStatus;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.mapper.OrderMapper;
import net.sparkminds.delivery.model.*;
import net.sparkminds.delivery.repository.*;
import net.sparkminds.delivery.response.OrderResponse;
import net.sparkminds.delivery.service.dto.Order.CreateOrderRequest;
import net.sparkminds.delivery.service.dto.Order.GetOrderRequest;
import net.sparkminds.delivery.service.dto.Order.OrderItemRequest;
import net.sparkminds.delivery.service.dto.Order.UpdateStatusOrderRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final OrderMapper orderMapper;
    private final DeliveryLocationService deliveryLocationService;
    private final DeliveryService deliveryService;
    private final DeliveryRepository deliveryRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final NotificationService notificationService;

    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId()).orElseThrow(() -> new BaseException("RESTAURANT_NOT_FOUND", "Restaurant not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findByEmail(email).orElseThrow(() -> new BaseException("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND));
        Order order = new Order();
        order.setRestaurant(restaurant);
        order.setUser(user);
        order.setDeliveryFee(request.getDeliveryFee());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setStatus(EOrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setLng(request.getLng().toString());
        order.setLat(request.getLat().toString());
        order.setAddress(request.getAddress());

        List<OrderItems> orderItems = new ArrayList<>();

        float total = 0f;
        float deliveryFee = request.getDeliveryFee() == null ? 0f : request.getDeliveryFee();


        for (OrderItemRequest itemReq : request.getItems()) {
            Menu menu = menuRepository.findById(itemReq.getIdMenu()).orElseThrow(() -> new BaseException("MENU_NOT_FOUND", "Menu not found", HttpStatus.NOT_FOUND));
            OrderItems item = new OrderItems();
            item.setName(menu.getName());
            item.setPrice(menu.getPrice());
            item.setOrder(order);
            total += menu.getPrice();
            orderItems.add(item);
        }


        order.setItems(orderItems);
        order.setTotalAmount(total + deliveryFee);
        orderRepository.save(order);

        List<String> idsDelivery = deliveryLocationService.findNearby(request.getLng(), request.getLat(), 100);

        if (!idsDelivery.isEmpty()) {
            Delivery delivery = deliveryRepository.findById(Long.parseLong(idsDelivery.get(0))).orElse(null);
            if (delivery != null) {
                order.setDelivery(delivery);
                orderRepository.save(order);
                deliveryService.sendOrder(idsDelivery.get(0));
            }
        }

        return order;
    }

    @Transactional
    public List<OrderResponse> getOrder(GetOrderRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Optional<Restaurant> restaurantOpt = restaurantRepository.findByEmail(email);
        Optional<User> userOpt = userRepository.findByEmail(email);

        Long restaurantId = restaurantOpt.map(Restaurant::getId).orElse(null);
        Long userId = userOpt.map(User::getId).orElse(null);

        Specification<Order> spec = Specification.where(OrderSpecification.fetchAll())
                .and(OrderSpecification.hasRestaurant(restaurantId))
                .and(OrderSpecification.hasUser(userId))
                .and(OrderSpecification.statusIn(List.of(EOrderStatus.PENDING, EOrderStatus.CONFIRMED, EOrderStatus.DELIVERING)));

        return orderMapper.toDtoList(orderRepository.findAll(spec));
    }


    @Transactional
    public List<OrderResponse> getOrderRes(GetOrderRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Optional<Restaurant> restaurantOpt = restaurantRepository.findByEmail(email);
        Optional<User> userOpt = userRepository.findByEmail(email);

        Long restaurantId = restaurantOpt.map(Restaurant::getId).orElse(null);
        Long userId = userOpt.map(User::getId).orElse(null);

        Specification<Order> spec = Specification.where(OrderSpecification.fetchAll())
                .and(OrderSpecification.hasRestaurant(restaurantId))
                .and(OrderSpecification.hasUser(userId))
                .and(OrderSpecification.status(request.getStatus()));

        return orderMapper.toDtoList(orderRepository.findAll(spec));
    }

    @Transactional
    public OrderResponse updateStatus(UUID id, UpdateStatusOrderRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Optional<Restaurant> restaurantOpt = restaurantRepository.findByEmail(email);
        Optional<User> userOpt = userRepository.findByEmail(email);

        Long restaurantId = restaurantOpt.map(Restaurant::getId).orElse(null);
        Long userId = userOpt.map(User::getId).orElse(null);

        if ((restaurantId != null && request.getStatus() == EOrderStatus.CANCELLED) || (userId != null && request.getStatus() != EOrderStatus.CANCELLED)) {
            throw new BaseException("NO_PERMISSION", "No permission", HttpStatus.BAD_REQUEST);
        }

        Order order = orderRepository.findByIdWithItems(id).orElseThrow(() -> new BaseException("ORDER_NOT_FOUND", "Order not found", HttpStatus.BAD_REQUEST));

        order.setStatus(request.getStatus());

        if (request.getStatus() == EOrderStatus.CONFIRMED || request.getStatus() == EOrderStatus.COMPLETED) {
            restaurantService.sendOrder(order.getRestaurant().getId());
            userService.sendSocket(order.getUser().getId(), request.getStatus().toString());
            if (request.getStatus() == EOrderStatus.COMPLETED) {
                notificationService.sendNotification(order.getUser().getId(), "Đơn hàng đã giao thành công", "Đơn hàng " + order.getId() + " đã được giao thành công");
            }
        }

        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Transactional
    public OrderResponse findShipperAgain(UUID id) {
        Order order = orderRepository.findByIdWithItems(id).orElse(null);

        if (order != null && order.getDelivery() == null
                && order.getLng() != null && order.getLat() != null) {

            List<String> nearbyList = deliveryLocationService.findNearby(
                    Double.parseDouble(order.getLng()),
                    Double.parseDouble(order.getLat()),
                    100
            );
            if (!nearbyList.isEmpty()) {
                String idDelivery = nearbyList.get(0);

                Delivery delivery = deliveryRepository
                        .findById(Long.parseLong(idDelivery))
                        .orElse(null);

                if (delivery != null) {
                    order.setDelivery(delivery);
                    orderRepository.save(order);
                }
            }
        }
        return orderMapper.toDto(order);
    }

    public OrderResponse byId(UUID id) {
        Order order = orderRepository.findByIdWithItems(id).orElseThrow(() -> new BaseException("ORDER_NOT_FOUND", "Order not found", HttpStatus.BAD_REQUEST));
        return orderMapper.toDto(order);
    }

    @Transactional
    public void rejectOrder(UUID id) {
        Order order = orderRepository.findByIdWithItems(id).orElseThrow(() -> new BaseException("ORDER_NOT_FOUND", "Order not found", HttpStatus.BAD_REQUEST));
        order.setDelivery(null);
        orderRepository.save(order);
    }
}
