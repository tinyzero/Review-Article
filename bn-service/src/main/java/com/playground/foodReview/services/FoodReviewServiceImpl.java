package com.playground.foodReview.services;

import com.playground.foodReview.entities.Keyword;
import com.playground.foodReview.entities.Review;
import com.playground.foodReview.entities.ReviewKeywordMapping;
import com.playground.foodReview.repositories.KeywordRepository;
import com.playground.foodReview.repositories.ReviewKeywordMappingRepository;
import com.playground.foodReview.repositories.ReviewRepository;
import com.playground.foodReview.responses.BadRequest;
import com.playground.foodReview.responses.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager entityManager;

    public ReviewResponse findWithId(Integer id) throws Exception {
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

    public ReviewResponse findWithKeyword(String text) throws Exception {
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

    @Transactional
    public ReviewResponse update(Integer id, Map<String, String> params) throws Exception {
        String new_review = params.get("new_review");
        String original_review = params.get("original_review");

        Review review_obj = reviewRepository.findById(id).orElse(null);
        Review result = new Review();

        if (review_obj==null) {
            Review new_obj = new Review();
            new_obj.setReview(new_review);

            result = reviewRepository.save(new_obj);

            List<Keyword> all_keyword = keywordRepository.findAll();

            reviewKeywordMappingRepository.insertMapping(id, new_review);

        } else {
            if (review_obj.getReview().equals(original_review)) {
                review_obj.setReview(new_review);

                result = reviewRepository.save(review_obj);

                List<ReviewKeywordMapping> old_mapping = reviewKeywordMappingRepository.findByReview_id(id);

                for (int i = 0; i < old_mapping.size(); i++){
                    reviewKeywordMappingRepository.delete(old_mapping.get(i));
                }

                reviewKeywordMappingRepository.insertMapping(id, new_review);

            } else {
                throw new BadRequest("Failed, You are editing an old version of the article.", 412);
            }
        }

        List<Review> review_list = new LinkedList<>();
        review_list.add(result);

        ReviewResponse response = new ReviewResponse();
        response.count = review_list.size();
        response.payload = review_list;

        return response;
    }

//    public void mappingReviewWithKeyword(Integer review_id, String review) {
//        entityManager.createNativeQuery("INSERT INTO review_keyword_mapping (review_id, keyword_id) SELECT ? as review_id, kt.id as keyword_id FROM keyword kt WHERE ? like concat('%',kt.keyword,'%')")
//                .setParameter(1, review_id)
//                .setParameter(2, review)
//                .executeUpdate();
//    }

}
