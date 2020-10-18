package com.playground.foodReview.repositories;

import com.playground.foodReview.entities.ReviewKeywordMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewKeywordMappingRepository extends JpaRepository<ReviewKeywordMapping,Integer> {

    @Query(value = "SELECT keyword_id, review_id FROM review_keyword_mapping WHERE review_id = ?1", nativeQuery = true)
    public List<ReviewKeywordMapping> findByReview_id(Integer review_id);

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO review_keyword_mapping (review_id, keyword_id) SELECT ?1 as review_id, kt.id as keyword_id FROM keyword kt WHERE ?2 like concat('%',kt.keyword,'%')",
            nativeQuery = true)
    public void insertMapping(Integer review_id, String review);

}
