package com.playground.foodReview.serviceTest;

import com.playground.foodReview.repositories.KeywordRepository;
import com.playground.foodReview.repositories.ReviewKeywordMappingRepository;
import com.playground.foodReview.repositories.ReviewRepository;
import com.playground.foodReview.services.FoodReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FoodReviewServiceTest {
    @Mock
    private KeywordRepository keywordRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewKeywordMappingRepository reviewKeywordMappingRepository;

    @InjectMocks
    private FoodReviewService foodReviewService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void runTestCases() {
//        assertEquals("Mocked", foodReviewService.findWithId());
//        assertEquals("Mocked", foodReviewService.findWithKeyword());
//        assertEquals("Mocked", foodReviewService.update());
    }
}
