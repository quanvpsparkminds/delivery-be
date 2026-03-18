package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.model.Delivery;
import net.sparkminds.delivery.model.Order;
import net.sparkminds.delivery.model.Rating;
import net.sparkminds.delivery.repository.DeliveryRepository;
import net.sparkminds.delivery.repository.OrderRepository;
import net.sparkminds.delivery.repository.RatingRepository;
import net.sparkminds.delivery.service.dto.Rating.RatingShipperRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository repository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    public void ratingShipper(RatingShipperRequest request) {
        Order order = orderRepository.findByIdWithItems(request.getIdOrder()).orElseThrow(() -> new BaseException("ORDER_NOT_FOUND", "Order not found", HttpStatus.NOT_FOUND));
        Delivery delivery = deliveryRepository.findById(request.getIdShipper()).orElseThrow(() -> new BaseException("DELIVERY_NOT_FOUND", "Delivery not found", HttpStatus.NOT_FOUND));

        Rating rating = new Rating();
        rating.setRating(request.getRating());
        rating.setOrder(order);
    }

}
