package com.example.microservices.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId,@RequestBody Review review) {
        boolean response = reviewService.createReview(companyId, review);
        if (response) {
            return new ResponseEntity<>("Review created successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long id, @RequestBody Review updatedReview) {
        boolean updated = reviewService.updateReview(companyId, id, updatedReview);
        if (updated) {
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long id) {
        boolean isDeleted = reviewService.deleteReviewById(companyId,id);
        if (isDeleted) {
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company or Review not found!", HttpStatus.NOT_FOUND);
        }
    }
}
