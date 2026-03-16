package net.sparkminds.delivery.service;

import net.sparkminds.delivery.response.DistanceResponse;
import net.sparkminds.delivery.service.dto.Router.RouterRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RouterService {
    public DistanceResponse getDistance(RouterRequest request) {
        String url = "http://router.project-osrm.org/route/v1/driving/"
                + request.getStartLng()
                + ","
                + request.getEndLat()
                + ";" + request.getEndLng()
                + "," + request.getEndLat()
                + "?overview=false";

        RestTemplate restTemplate = new RestTemplate();

        Map response = restTemplate.getForObject(url, Map.class);

        JSONObject obj = new JSONObject(response);
        JSONArray routes = obj.getJSONArray("routes");
        JSONObject route0 = routes.getJSONObject(0);

        Float duration = route0.getFloat("duration") / 60;
        Float distance = route0.getFloat("distance") / 1000;


        // calculate
        // baseFee + (distanceKm * pricePerKm)
        // baseFee = 5000;
        // pricePerKm = 5000;

        return new DistanceResponse(distance, duration, 1 + (distance * duration));
    }
}
