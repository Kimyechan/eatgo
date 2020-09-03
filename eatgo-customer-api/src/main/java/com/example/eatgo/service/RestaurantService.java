package com.example.eatgo.service;

import com.example.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository,
                             ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant  addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        restaurant.updateInformation(name, address);
        return restaurant;
    }
}