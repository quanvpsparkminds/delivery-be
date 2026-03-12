package net.sparkminds.delivery.service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.model.NotificationToken;
import net.sparkminds.delivery.model.User;
import net.sparkminds.delivery.repository.NotificationRepository;
import net.sparkminds.delivery.repository.UserRepository;
import net.sparkminds.delivery.service.dto.Notification.TokenRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void addToken(TokenRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new BaseException(
                        "USER_NOT_FOUND",
                        "user not found",
                        HttpStatus.NOT_FOUND
                ));


        NotificationToken notificationToken = new NotificationToken();

        notificationToken.setUser(user);
        notificationToken.setToken(request.getToken());

        notificationRepository.save(notificationToken);
    }

    public void deleteToken(TokenRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new BaseException(
                        "USER_NOT_FOUND",
                        "user not found",
                        HttpStatus.NOT_FOUND
                ));

    }

}
