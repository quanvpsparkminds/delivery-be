package net.sparkminds.delivery.mapper;

import net.sparkminds.delivery.model.Notification;
import net.sparkminds.delivery.response.NotificationResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    public NotificationResponse toDto(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .description(notification.getDescription())
                .title(notification.getTitle())
                .createDate(notification.getCreateDate())
                .isSeen(notification.getIsSeen())
                .build();
    }

    public List<NotificationResponse> toDtoList(List<Notification> notifications) {
        return notifications.stream().map(this::toDto).collect(Collectors.toList());
    }
}
