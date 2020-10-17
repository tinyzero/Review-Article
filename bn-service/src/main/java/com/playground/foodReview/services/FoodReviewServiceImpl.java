package com.playground.foodReview.services;

import com.playground.foodReview.entities.Keyword;
import com.playground.foodReview.entities.Review;
import com.playground.foodReview.entities.ReviewKeywordMapping;
import com.playground.foodReview.repositories.KeywordRepository;
import com.playground.foodReview.repositories.ReviewKeywordMappingRepository;
import com.playground.foodReview.repositories.ReviewRepository;
import com.playground.foodReview.responses.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FoodReviewServiceImpl implements FoodReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private ReviewKeywordMappingRepository reviewKeywordMappingRepository;

    public ReviewResponse findWithId(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);

        List<Review> review_list = new LinkedList<>();
        if (review.isPresent() == true) {
            review_list.add(review.get());
        }

        ReviewResponse response = new ReviewResponse();
        response.count = 1;
        response.payload = review_list;

        return response;
    }

    public ReviewResponse findWithKeyword(String text) {
        Keyword keyword = keywordRepository.findByKeyword(text);

        List<Review> review_list = new LinkedList<>();
        if (keyword != null){
            review_list = keyword.getReviews();
        }

        ReviewResponse response = new ReviewResponse();
        response.count = review_list.size();
        response.payload = review_list;

        return response;
    }

    public ReviewResponse update(Integer id, Map<String, String> params) {
        String new_review = params.get("new_review");

        Review review_obj = reviewRepository.findById(id).orElse(null);
        review_obj.setReview(new_review);

        Review result = reviewRepository.save(review_obj);

        List<Review> review_list = new LinkedList<>();
        review_list.add(result);

        ReviewResponse response = new ReviewResponse();
        response.count = review_list.size();
        response.payload = review_list;

        return response;
    }

}
