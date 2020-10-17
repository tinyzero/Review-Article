package com.playground.foodReview.repositories;

import com.playground.foodReview.entities.ReviewKeywordMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewKeywordMappingRepository extends JpaRepository<ReviewKeywordMapping,Integer> {
}
