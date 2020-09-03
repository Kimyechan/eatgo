package com.example.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RestaurantTests {


    @Test
    public void create() {
//        Restaurant restaurant = new Restaurant(1004L,"bab zip", "Seoul");
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("bab zip")
                .address("Seoul")
                .build();
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getName(), is("bab zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant(1004L, "bab zip", "Seoul");

        assertThat(restaurant.getInformation(), is("bab zip in Seoul")) ;
    }
}