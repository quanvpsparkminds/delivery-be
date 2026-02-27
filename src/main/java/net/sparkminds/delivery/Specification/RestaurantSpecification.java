package net.sparkminds.delivery.Specification;

import net.sparkminds.delivery.enums.ERestaurant;
import net.sparkminds.delivery.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

public class RestaurantSpecification {
    public static Specification<Restaurant> hasType(ERestaurant type) {
        return (root, query, cb) -> {
            if (type == null) return null;
            return cb.equal(root.get("type"), type);
        };
    }

    public static Specification<Restaurant> hasCity(int cityId) {
        return (root, query, cb) -> {
            if (cityId == 0) return null;
            return cb.equal(root.get("cityId"), cityId);
        };
    }

    public static Specification<Restaurant> hasCountry(int countryId) {
        return (root, query, cb) -> {
            if (countryId == 0) return null;
            return cb.equal(root.get("countryId"), countryId);
        };
    }

    public static Specification<Restaurant> fullName(String fullName) {
        return (root, query, cb) -> {
            if (fullName == null) return null;
            return cb.like(cb.lower(root.get("fullName")),
                    "%" + fullName.toLowerCase() + "%");
        };
    }

    public static Specification<Restaurant> address(String address) {
        return (root, query, cb) -> {
            if (address == null) return null;
            return cb.like(cb.lower(root.get("address")),
                    "%" + address.toLowerCase() + "%");
        };
    }
}
