package net.sparkminds.delivery.service;

import net.sparkminds.delivery.Specification.MenuSpecification;
import net.sparkminds.delivery.exception.BaseException;
import net.sparkminds.delivery.model.Menu;
import net.sparkminds.delivery.model.Restaurant;
import net.sparkminds.delivery.repository.MenuRepository;
import net.sparkminds.delivery.repository.RestaurantRepository;
import net.sparkminds.delivery.service.dto.Menu.CreateMenuRequest;
import net.sparkminds.delivery.service.dto.Menu.GetMenuRequest;
import net.sparkminds.delivery.ultils.SecurityUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
        menu.setDelete(false);
        menuRepository.save(menu);
    }

    public List<Menu> getMenus(GetMenuRequest request) {
        Specification<Menu> spec = Specification
                .where(MenuSpecification.hasRestaurant(request.getRestaurantId()))
                .and(MenuSpecification.hasType(request.getType()))
                .and(MenuSpecification.hasDelete(false));
        return menuRepository.findAll(spec);
    }

    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new BaseException("MENU_NOT_FOUND", "Menu not found", HttpStatus.NOT_FOUND));
        menu.setDelete(true);

        menuRepository.save(menu);
    }
}
