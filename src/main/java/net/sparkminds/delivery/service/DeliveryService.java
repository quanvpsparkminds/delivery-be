package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.component.JwtUtil;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.mapper.DeliveryMapper;
import net.sparkminds.delivery.model.Delivery;
import net.sparkminds.delivery.repository.DeliveryRepository;
import net.sparkminds.delivery.response.AuthResponse;
import net.sparkminds.delivery.response.DeliveryResponse;
import net.sparkminds.delivery.service.dto.Delivery.OnboardingDeliveryRequest;
import net.sparkminds.delivery.service.dto.Delivery.RegisterDeliveryRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final DeliveryMapper deliveryMapper;

    public AuthResponse registerDelivery(RegisterDeliveryRequest request) {
        if (deliveryRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new BaseException("DELIVERY_EXITS", "Delivery already exists", HttpStatus.BAD_REQUEST);
        }

        Delivery delivery = new Delivery();
        delivery.setEmail(request.getEmail());
        delivery.setPhoneCode(request.getPhoneCode());
        delivery.setPhoneNumber(request.getPhoneNumber());
        delivery.setPassword(passwordEncoder.encode(request.getPassword()));

        deliveryRepository.save(delivery);

        return new AuthResponse(
                jwtUtil.generateToken(request.getEmail()),
                jwtUtil.generateRefreshToken(request.getEmail())
        );
    }

    public DeliveryResponse me() {
        String email = SecurityUtil.getCurrentUserEmail();

        Delivery delivery = deliveryRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException("DELIVERY_NOT_FOUND", "Delivery not found", HttpStatus.BAD_REQUEST));

        return deliveryMapper.toResponse(delivery);
    }

    public DeliveryResponse onBoarding(OnboardingDeliveryRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();

        Delivery delivery = deliveryRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException("DELIVERY_NOT_FOUND", "Delivery not found", HttpStatus.BAD_REQUEST));

        Delivery delivery1 = deliveryRepository.save(deliveryMapper.toEntity(request, delivery));

        return deliveryMapper.toResponse(delivery1);
    }
}
