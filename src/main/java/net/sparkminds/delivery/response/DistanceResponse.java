package net.sparkminds.delivery.response;

import lombok.Data;

@Data
public class DistanceResponse {
    private Float distance;
    private Float duration;
    private Float fee;

    public DistanceResponse(Float distance, Float duration, Float fee) {
        this.distance = distance;
        this.duration = duration;
        this.fee = fee;
    }
}
