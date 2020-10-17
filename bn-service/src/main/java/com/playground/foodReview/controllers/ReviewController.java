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
public class ReviewController {

    @Autowired
    FoodReviewService foodReviewService;

    @RequestMapping(value = "/reviews/{id}",  method = { RequestMethod.GET})
    public @ResponseBody
    ResponseEntity<?> findById(@PathVariable Integer id) throws Exception {
        Optional<Review> result = foodReviewService.findWithId(id);
        return ResponseEntity.status(200).body(result);
    }

    @RequestMapping(value = "/reviews",  method = { RequestMethod.GET})
    public @ResponseBody

    ResponseEntity<?> findByKeyword(@RequestParam("query") String text) throws Exception {
        List<Review> result = foodReviewService.findWithKeyword(text);
        return ResponseEntity.status(200).body(result);
    }

    @RequestMapping(value = "/reviews/{id}",  method = { RequestMethod.PUT})
    public @ResponseBody
    ResponseEntity<?> findByKeyword(@PathVariable Integer id, @RequestParam Map<String,String> params) throws Exception {
        Review result = foodReviewService.update(id, params);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping(value = "/test/{id}")
    public String search(@PathVariable String id) throws Exception {
        return "ID : " + id;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex)
    {
        return ResponseEntity.status(400).body("Something went wrong, Please check and try again");
    }
}
