package com.playground.foodReview.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "review_keyword_mapping")
@IdClass(ReviewKeywordMappinngId.class)
@Data
public class ReviewKeywordMapping {
    @Id
    @Column(name = "keyword_id")
    @JsonProperty("keyword_id")
    private Integer keyword_id;

    @Id
    @Column(name = "review_id")
    @JsonProperty("review_id")
    private Integer review_id;
}
