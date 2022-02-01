package com.project.ReviewService.controller;

import com.project.ReviewService.model.Review;
import com.project.ReviewService.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repo;

    @RequestMapping("/author/{author}")
    public List<Review> getReviewsByAuthor(@PathVariable String author) {
        return repo.findByAuthor(author);
    }

    @RequestMapping("/destination/all/{destId}")
    public List<Review> getReviewsByDestination(@PathVariable long destId) {
        return repo.findByDestId(destId);
    }

    @RequestMapping("/destination/{destId}")
    public long[] getReviewIds(@PathVariable long destId) {
        List<Review> reviewList = repo.findByDestId(destId);
        long[] reviews = new long[reviewList.size()];
        for(int i = 0; i < reviewList.size(); i++) {
            reviews[i] = reviewList.get(i).getReviewId();
        }
        return reviews;
    }

    @RequestMapping("/{reviewId}")
    public Review getReviewById(@PathVariable long reviewId) {
        return repo.findByReviewId(reviewId);
    }

    @RequestMapping("/insert")
    public void insertReview(@RequestBody Review review) {
        repo.insertReview(review.getDestId(), review.getAuthor(), review.getSubject(), review.getContent());
    }

    @RequestMapping("/update")
    public void updateReview(@RequestBody Review review) {
        repo.updateReview(review.getReviewId(), review.getDestId(), review.getAuthor(), review.getSubject(), review.getContent());
    }

    @RequestMapping("/delete/{reviewId}")
    public void deleteReview(@PathVariable long reviewId) { repo.deleteReviewByReviewId(reviewId); }

    @RequestMapping("/delete/author/{author}")
    public void deleteReviewsByAuthor(@PathVariable String author) { repo.deleteReviewsByAuthor(author); }
}
