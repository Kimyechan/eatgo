package com.example.eatgo.service;

import com.example.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        restaurantService = new RestaurantService(restaurantRepository );
    }


    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
//        Restaurant restaurant = new Restaurant(1004L, "bab zip", "Seoul");
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("bab zip")
                .address("Seoul")
                .menuItems(new ArrayList<MenuItem>())
                .reviews(new ArrayList<Review>())
                .build();
        restaurants.add(restaurant);
        given(restaurantRepository.findAllByAddressContaining("Seoul")).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));
    }

    @Test
    public void getRestaurantWithExisted(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted(){
        restaurantService.getRestaurant(404L);
    }

    @Test
    public void getRestaurants(){
        String region = "Seoul";
        List<Restaurant> restaurants = restaurantService.getRestaurants(region);

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void addRestaurants(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });
        Restaurant restaurant = Restaurant.builder()
                .name("Beyoung")
                .address("Seoul")
                .build();

        Restaurant saved = Restaurant.builder()
                .id(1234L)
                .name("Beyoung")
                .address("Seoul")
                .build();

//        given(restaurantRepository.save(any())).willReturn(saved);
        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1234L));
    }

    @Test
    public void updateRestaurants() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bab zip")
                .address("Seoul")
                .build();
        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L, "Soul zip", "Busan");

        assertThat(restaurant.getName(), is("Soul zip"));
        assertThat(restaurant.getAddress(), is("Busan"));
    }

}