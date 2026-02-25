package net.sparkminds.delivery.mapper;

import net.sparkminds.delivery.model.Restaurant;
import net.sparkminds.delivery.response.RestaurantResponse;
import net.sparkminds.delivery.service.dto.RegisterRestaurantRequest;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public Restaurant toEntity(RegisterRestaurantRequest request, String encodedPassword) {
        Restaurant restaurant = new Restaurant();
        restaurant.setEmail(request.getEmail());
        restaurant.setPassword(encodedPassword);
        restaurant.setFullName(request.getFullName());
        restaurant.setPhoneCode(request.getPhoneCode());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setCountryId(request.getCountryId());
        restaurant.setCityId(request.getCityId());
        restaurant.setAddress(request.getAddress());
        restaurant.setPostCode(request.getPostCode());
        return restaurant;
    }

    public RestaurantResponse toResponse(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();
        response.setEmail(restaurant.getEmail());
        response.setFullName(restaurant.getFullName());
        response.setPhoneCode(restaurant.getPhoneCode());
        response.setPhoneNumber(restaurant.getPhoneNumber());
        response.setCountryId(restaurant.getCountryId());
        response.setCityId(restaurant.getCityId());
        response.setAddress(restaurant.getAddress());
        response.setPostCode(restaurant.getPostCode());
        return response;
    }
}
