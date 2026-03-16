package net.sparkminds.delivery.response;

import lombok.Data;
import net.sparkminds.delivery.enums.ERestaurant;
import net.sparkminds.delivery.model.Menu;

import java.util.List;

@Data
public class RestaurantResponse {
    private Long id;

    private String email;

    private String fullName;

    private String phoneCode;

    private String phoneNumber;

    private Number countryId;

    private Number cityId;

    private String address;

    private String postCode;

    private ERestaurant type;

    private String image;

    private String lng;

    private String lat;

    private DistanceResponse route;

    private List<Menu> menu;

    public RestaurantResponse(Long id,String email, String fullName, String phoneCode, String phoneNumber, Number countryId, Number cityId, String address, String postCode, ERestaurant type, String image, String lng, String lat, DistanceResponse route, List<Menu> menu) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.address = address;
        this.postCode = postCode;
        this.type = type;
        this.image = image;
        this.lng = lng;
        this.lat = lat;
        this.route = route;
        this.menu = menu;
    }

    public RestaurantResponse() {
    }
}
