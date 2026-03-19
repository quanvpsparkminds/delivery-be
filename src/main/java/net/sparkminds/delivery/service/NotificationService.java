package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.mapper.NotificationMapper;
import net.sparkminds.delivery.model.Notification;
import net.sparkminds.delivery.model.User;
import net.sparkminds.delivery.repository.NotificationRepository;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.response.NotificationResponse;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final NotificationMapper notificationMapper;

    public List<NotificationResponse> getNotification() {
        List<NotificationResponse> result = new ArrayList<>();
        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            List<Notification> notifications = notificationRepository.findByUser(user.getId());
            result.addAll(notificationMapper.toDtoList(notifications));
        }

        return result;
    }

    public void sendNotification(Long userId, String title, String description) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Notification notification = new Notification();
            notification.setUser(user);
            notification.setTitle(title);
            notification.setDescription(description);
            notification.setCreateDate(LocalDateTime.now());

            notificationRepository.save(notification);

            userService.sendSocket(userId, "NOTIFICATION");
        }
    }

    public void read(Long id) {
        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElse(null);
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new BaseException("NOT_FOUND", "Notification not found", HttpStatus.NOT_FOUND));
        if (user != null) {
            notification.setIsSeen(true);
            notificationRepository.save(notification);
        }
    }

    @Transactional
    public void readAll() {
        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            notificationRepository.markAllAsSeen(user.getId());
        }
    }
}
