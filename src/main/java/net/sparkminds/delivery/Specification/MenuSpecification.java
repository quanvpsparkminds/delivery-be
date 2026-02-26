package net.sparkminds.delivery.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import net.sparkminds.delivery.enums.EFood;
import net.sparkminds.delivery.model.Menu;
import org.springframework.data.jpa.domain.Specification;

public class MenuSpecification {
    public static Specification<Menu> hasRestaurant(Long restaurantId) {
        return (root, query, cb) -> {
            // fetch restaurant để tránh lazy lỗi
            if (Menu.class.equals(query.getResultType())) {
                root.fetch("restaurant", JoinType.LEFT);
                query.distinct(true);
            }

            if (restaurantId == null) {
                return cb.conjunction(); // always true
            }

            Join<Object, Object> restaurantJoin = root.join("restaurant", JoinType.INNER);

            return cb.equal(restaurantJoin.get("id"), restaurantId);
        };
    }

    public static Specification<Menu> hasType(EFood type) {
        return ((root, query, criteriaBuilder) -> {
            if (type == null) return null;
            return criteriaBuilder
                    .equal(
                            root.get("type"), type
                    );
        });
    }
}