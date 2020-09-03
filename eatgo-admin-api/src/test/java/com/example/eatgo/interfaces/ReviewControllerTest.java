package com.example.eatgo.interfaces;

import com.example.eatgo.domain.Review;
import com.example.eatgo.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService ;

    @Test
    public void createWithValidAttributes() throws Exception {
        given(reviewService.addReview(eq(1L),any())).willReturn(
                Review.builder()
                        .id(1004L)
                        .name("JOKER")
                        .score(3)
                        .description("Mat-it-da")
                        .build()
        );

        mvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"JOKER\",\"score\":3,\"description\":\"mat-it-da\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1/reviews/1004"));

        Mockito.verify(reviewService).addReview(eq(1L), any());
    }

    @Test
    public void createWithInvalidAttributes() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());

        Mockito.verify(reviewService, never()).addReview(eq(1L), any());
    }

}