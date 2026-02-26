package net.sparkminds.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sparkminds.delivery.model.Menu;
import net.sparkminds.delivery.response.ApiResponse;
import net.sparkminds.delivery.service.MenuService;
import net.sparkminds.delivery.service.dto.CreateMenuRequest;
import net.sparkminds.delivery.service.dto.GetMenuRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;


    @PostMapping("/menu")
    public ResponseEntity<ApiResponse<Void>> createMenu(@Valid @RequestBody CreateMenuRequest request) {
        menuService.createMenu(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(null));
    }

    @GetMapping("/menu")
    public ResponseEntity<ApiResponse<List<Menu>>> getMenu(GetMenuRequest request) {
        List<Menu> response = menuService.getMenus(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
    }
}
