package com.example.eatgo.service;

import com.example.eatgo.domain.Review;
import com.example.eatgo.domain.ReviewRepository;
import com.example.eatgo.service.ReviewService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }
    @Test
    public void addReview() {
        Review review = Review.builder()
                .name("JOKER")
                .score(3)
                .description("Mat-it-da")
                .build();
        reviewService.addReview(1004L, "JOKER", 3, "Mat-it-da");

        verify(reviewRepository).save(any());
    }

}