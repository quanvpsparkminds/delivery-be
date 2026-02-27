package net.sparkminds.delivery.mapper;

import net.sparkminds.delivery.model.User;
import net.sparkminds.delivery.response.UserResponse;
import net.sparkminds.delivery.service.dto.User.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(RegisterRequest request, String encodedPassword) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setPhoneCode(request.getPhoneCode());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(encodedPassword);
        user.setImage(request.getImage());
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneCode(user.getPhoneCode());
        response.setBirthday(user.getBirthday());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setImage(user.getImage());
        return response;
    }
}
