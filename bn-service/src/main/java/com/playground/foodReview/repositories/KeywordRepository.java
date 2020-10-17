package com.playground.foodReview.repositories;

import com.playground.foodReview.entities.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword,Integer> {
    public Keyword findByKeyword(String text);
}
