package net.sparkminds.delivery.mapper;

import net.sparkminds.delivery.model.Delivery;
import net.sparkminds.delivery.response.DeliveryResponse;
import net.sparkminds.delivery.service.dto.Delivery.OnboardingDeliveryRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryMapper {
    public static DeliveryResponse toResponse(Delivery delivery) {
        if (delivery == null) return null;

        return DeliveryResponse.builder()
                .id(delivery.getId())
                .email(delivery.getEmail())
                .phoneCode(delivery.getPhoneCode())
                .phoneNumber(delivery.getPhoneNumber())
                .status(delivery.getStatus())
                .firstName(delivery.getFirstName())
                .lastName(delivery.getLastName())
                .birthday(delivery.getBirthday())
                .countryId(delivery.getCountryId())
                .cityId(delivery.getCityId())
                .address(delivery.getAddress())
                .postCode(delivery.getPostCode())
                .avatar(delivery.getAvatar())
                .currentLng(delivery.getCurrentLng())
                .currentLat(delivery.getCurrent_Lat())
                .isOnboarding(delivery.isOnboarding())
                .build();
    }

    public static List<DeliveryResponse> toResponseList(List<Delivery> deliveries) {
        return deliveries.stream()
                .map(DeliveryMapper::toResponse)
                .toList();
    }

    public static Delivery toEntity(OnboardingDeliveryRequest request, Delivery delivery) {
        delivery.setFirstName(request.getFirstName());
        delivery.setLastName(request.getLastName());
        delivery.setBirthday(request.getBirthday());
        delivery.setCountryId(request.getCountryId());
        delivery.setCityId(request.getCityId());
        delivery.setAddress(request.getAddress());
        delivery.setPostCode(request.getPostCode());
        delivery.setAvatar(request.getAvatar());

        delivery.setOnboarding(true);

        return delivery;
    }
}
