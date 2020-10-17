package com.playground.foodReview.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.playground.foodReview.entities.Review;

import java.util.List;

public class ReviewResponse {

    @JsonProperty("count")
    public Integer count;

    @JsonProperty("payload")
    public List<Review> payload = null;

}
