package com.playground.foodReview.controllers;

import com.playground.foodReview.entities.Review;
import com.playground.foodReview.services.FoodReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    FoodReviewService foodReviewService;

    @GetMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<?> findById(@PathVariable Integer id) throws Exception {
        Optional<Review> result = foodReviewService.findWithId(id);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping(value = "/text")
    public @ResponseBody
    ResponseEntity<?> findByKeyword(@RequestParam("text") String text) throws Exception {
        List<Review> result = foodReviewService.findWithKeyword(text);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping(value = "/test/{id}")
    public String search(@PathVariable String id) throws Exception {
        return "ID : " + id;
    }
}
