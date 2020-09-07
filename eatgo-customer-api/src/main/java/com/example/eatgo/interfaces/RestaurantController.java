package com.example.eatgo.interfaces;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(
            @RequestParam("region") String region,
            @RequestParam("category") Long categoryId
    ) {
        List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);

        return restaurants ;
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant detail(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }
}

