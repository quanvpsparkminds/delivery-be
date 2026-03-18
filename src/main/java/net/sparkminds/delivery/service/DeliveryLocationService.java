package net.sparkminds.delivery.service;

import net.sparkminds.delivery.service.dto.Delivery.LocationDeliveryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryLocationService {
    private static final String SHIPPER_LOCATION_KEY = "shipper:locations";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void updateLocation(LocationDeliveryRequest request) {
        System.out.println(request);
        redisTemplate.opsForGeo().add(
                SHIPPER_LOCATION_KEY,
                new Point(request.getLng(), request.getLat()),
                request.getId()
        );
    }

    public List<String> findNearby(double lat, double lng, double radiusKm) {
        GeoResults<RedisGeoCommands.GeoLocation<String>> results =
                redisTemplate.opsForGeo().search(
                        SHIPPER_LOCATION_KEY,
                        GeoReference.fromCoordinate(lat, lng),
                        new Distance(radiusKm, Metrics.KILOMETERS)
                );

        List<String> shippers = new ArrayList<>();
        if (results != null) {
            for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
                String shipperId = result.getContent().getName();
                shippers.add(shipperId);
            }
        }
        return shippers;
    }


    public void removeShipper(Long shipperId) {
        System.out.println(shipperId);
        redisTemplate.opsForZSet()
                .remove(SHIPPER_LOCATION_KEY, shipperId.toString());
    }
}