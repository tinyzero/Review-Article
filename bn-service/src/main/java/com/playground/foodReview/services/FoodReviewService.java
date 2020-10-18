package com.playground.foodReview.services;

import com.playground.foodReview.entities.Review;
import com.playground.foodReview.responses.ReviewResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FoodReviewService {
    ReviewResponse findWithId(Integer id) throws Exception;
    ReviewResponse findWithKeyword(String text) throws Exception;
    ReviewResponse update(Integer id, Map<String, String> params) throws Exception;
    void mapping();
}
