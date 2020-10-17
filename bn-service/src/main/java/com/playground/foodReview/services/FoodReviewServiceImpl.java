package com.playground.foodReview.services;

import com.playground.foodReview.entities.Keyword;
import com.playground.foodReview.entities.Review;
import com.playground.foodReview.entities.ReviewKeywordMapping;
import com.playground.foodReview.repositories.KeywordRepository;
import com.playground.foodReview.repositories.ReviewKeywordMappingRepository;
import com.playground.foodReview.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Review> findWithId(Integer id) throws Exception {
        Optional<Review> review = reviewRepository.findById(id);
        return review;
    }

    public List<Review> findWithKeyword(String text) throws Exception {
        Keyword keyword = keywordRepository.findByKeyword(text);
        List<Review> reviews = keyword.getReviews();
        return reviews;
    }

    public Review update(Integer id, Map<String, String> params) throws Exception {
        String new_review = params.get("new_review");

        Review review_obj = reviewRepository.findById(id).orElse(null);
        review_obj.setReview(new_review);

        Review result = reviewRepository.save(review_obj);

        return result;
    }

}
