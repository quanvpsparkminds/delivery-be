package net.sparkminds.delivery.response;

import lombok.Builder;
import lombok.Data;
import net.sparkminds.delivery.enums.EDeliveryStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class DeliveryResponse {

    private Long id;
    private String email;
    private String phoneCode;
    private String phoneNumber;
    private EDeliveryStatus status;
    private String firstName;
    private String lastName;
    private LocalDateTime birthday;
    private int countryId;
    private int cityId;
    private String address;
    private String postCode;
    private String avatar;
    private String currentLng;
    private String currentLat;
    private boolean isOnboarding;
}