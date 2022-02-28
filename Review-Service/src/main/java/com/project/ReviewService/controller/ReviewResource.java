package com.project.ReviewService.controller;

import com.project.ReviewService.dto.ReviewDto;
import com.project.ReviewService.mapper.ReviewMapper;
import com.project.ReviewService.model.Review;
import com.project.ReviewService.review.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewResource {

    private final ReviewMapper mapper;

    private final ReviewRepository repo;

    public ReviewResource(ReviewMapper mapper, ReviewRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return new ResponseEntity<>(mapper.modelsToDto(repo.findAll()), HttpStatus.OK);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<ReviewDto>> getReviewsByAuthor(@PathVariable String author) {
        List<Review> reviews = repo.findByAuthor(author);
        if(reviews == null || reviews.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mapper.modelsToDto(reviews), HttpStatus.OK);
    }

    @GetMapping("/destination/{destId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByDestination(@PathVariable long destId) {
        List<Review> reviews = repo.findByDestId(destId);
        if(reviews == null || reviews.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mapper.modelsToDto(reviews), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable long reviewId) {
        Review review = repo.findByReviewId(reviewId);
        if(review == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mapper.modelToDto(review), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Void> insertReview(@RequestBody Review review) {
        repo.insertReview(review.getDestId(), review.getAuthor(), review.getSubject(), review.getContent());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateReview(@RequestBody Review review) {
        Review response = repo.findByReviewId(review.getReviewId());
        if(response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.updateReview(review.getReviewId(), review.getDestId(), review.getAuthor(), review.getSubject(), review.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable long reviewId) {
        Review response = repo.findByReviewId(reviewId);
        if(response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.deleteReviewByReviewId(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/author/{author}")
    public ResponseEntity<Void> deleteReviewsByAuthor(@PathVariable String author) {
        List<Review> response = repo.findByAuthor(author);
        if(response == null || response.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.deleteReviewsByAuthor(author);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
