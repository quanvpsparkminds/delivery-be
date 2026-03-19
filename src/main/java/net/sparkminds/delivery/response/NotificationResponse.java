package net.sparkminds.delivery.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createDate;
    private Boolean isSeen;
}
