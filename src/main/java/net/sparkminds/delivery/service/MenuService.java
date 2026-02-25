package net.sparkminds.delivery.service;

import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.model.Menu;
import net.sparkminds.delivery.model.Restaurant;
import net.sparkminds.delivery.repository.MenuRepository;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.service.dto.CreateMenuRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public MenuService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    public void createMenu(CreateMenuRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Restaurant restaurant = restaurantRepository
                .findByEmail(email)
                .orElseThrow(() -> new BaseException("RESTAURANT_NOT_EXISTS",
                        "Restaurant not exists",
                        HttpStatus.BAD_REQUEST));

        Menu menu = new Menu(request.getImage(),
                request.getType(),
                request.getOriginPrice(),
                request.getPrice(),
                request.getName(),
                restaurant
        );
        menuRepository.save(menu);
    }
}
