package net.sparkminds.delivery.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import net.sparkminds.delivery.enums.EOrderStatus;
import net.sparkminds.delivery.model.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    public static Specification<Order> hasRestaurant(Long restaurantId) {
        return (root, query, cb) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("restaurant", JoinType.LEFT);
                root.fetch("items", JoinType.LEFT);
                query.distinct(true);
            }

            if (restaurantId == null) {
                return cb.conjunction(); // always true
            }

            Join<Object, Object> restaurantJoin = root.join("restaurant", JoinType.INNER);

            return cb.equal(restaurantJoin.get("id"), restaurantId);
        };
    }

    public static Specification<Order> hasUser(Long userId) {
        return (root, query, cb) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("user", JoinType.LEFT);
                root.fetch("items", JoinType.LEFT);
                query.distinct(true);
            }

            if (userId == null) {
                return cb.conjunction(); // always true
            }

            Join<Object, Object> userJoin = root.join("user", JoinType.INNER);

            return cb.equal(userJoin.get("id"), userId);
        };
    }

    public static Specification<Order> status(EOrderStatus status) {
        return (root, query, cb) -> {
            if (status == null) return null;
            return cb.equal(root.get("status"), status);
        };
    }
}
