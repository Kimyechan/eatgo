package com.example.eatgo.interfaces;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantNotFoundException;
import com.example.eatgo.domain.Review;
import com.example.eatgo.interfaces.RestaurantController;
import com.example.eatgo.service.RestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest  {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

//    @SpyBean(RestaurantService.class)
//    private RestaurantService restaurantService;
//
//    @SpyBean(RestaurantRepositoryImpl.class)
//    private RestaurantRepository restaurantRepositoryImpl;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;
    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants1 = new ArrayList<>();
        restaurants1.add(Restaurant.builder()
                .id(1004L)
                .name("Joker House")
                .address("Seoul")
                .build()
        );
        given(restaurantService.getRestaurants()).willReturn(restaurants1);
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Joker House\"")))
                .andExpect(content().string(containsString("\"id\":1004")));

    }

    @Test
    public void detailWithExisted() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Joker House")
                .address("Seoul")
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();
        restaurant.setMenuItems(Arrays.asList(menuItem ));
        Review review = Review.builder()
                .name("JOKER")
                .score(5)
                .description("Great")
                .build();

        restaurant.setReviews(Arrays.asList(review));

//        Restaurant restaurant2 = Restaurant.builder()
//                .id(2020L)
//                .name("Cyber Food")
//                .address("Seoul")
//                .build();
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
//        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);
        mvc.perform(get("/restaurant/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Joker House\"")))
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("Kimchi")))
                .andExpect(content().string(containsString("Great")));

//        mvc.perform(get("/restaurant/2020"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("\"name\":\"Cyber Food\"")))
//                .andExpect(content().string(containsString("\"id\":2020")));
    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurant/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }
}