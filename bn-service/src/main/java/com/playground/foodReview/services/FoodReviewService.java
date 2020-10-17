package com.playground.foodReview.services;

import com.playground.foodReview.entities.Review;

import java.util.List;
import java.util.Optional;

public interface FoodReviewService {
    Optional<Review> findWithId(Integer id) throws Exception;
    List<Review> findWithKeyword(String text) throws Exception;
}
