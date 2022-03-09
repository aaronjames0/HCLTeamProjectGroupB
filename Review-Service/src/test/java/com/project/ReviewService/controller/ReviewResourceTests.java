package com.project.ReviewService.controller;

import com.project.ReviewService.dto.ReviewDto;
import com.project.ReviewService.mapper.ReviewMapper;
import com.project.ReviewService.model.Review;
import com.project.ReviewService.review.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewResourceTests {

    @Autowired
    ReviewRepository repo;

    @Autowired
    ReviewMapper mapper;

    Review realReview;
    Review fakeReview;
    ReviewDto realDto = new ReviewDto();
    ReviewDto fakeDto = new ReviewDto();

    ReviewResourceTests() {

        realReview = new Review(1, 1, "Timojim", "The place", "There's things");
        fakeReview = new Review(8947984, 49864654, "Jimothy", "The place", "There's things");

        realDto.setReviewId(realReview.getReviewId());
        realDto.setDestId(realReview.getDestId());
        realDto.setAuthor(realReview.getAuthor());
        realDto.setSubject(realReview.getSubject());
        realDto.setContent(realReview.getContent());

        fakeDto.setReviewId(fakeReview.getReviewId());
        fakeDto.setDestId(fakeReview.getDestId());
        fakeDto.setAuthor(fakeReview.getAuthor());
        fakeDto.setSubject(fakeReview.getSubject());
        fakeDto.setContent(fakeReview.getContent());
    }

    @Test
    @DisplayName("Get All Test")
    @Order(1)
    void getAllTest() {
        ReviewResource resource = new ReviewResource(mapper, repo);
        ResponseEntity<List<ReviewDto>> list = resource.getAllReviews();
        Assertions.assertEquals(HttpStatus.OK, list.getStatusCode());
    }

    @Test
    @DisplayName("Post Test")
    @Order(2)
    void postTest() {
        ReviewResource resource = new ReviewResource(mapper, repo);
        ResponseEntity<Void> firstResponse = resource.insertReview(realReview);
        Assertions.assertEquals(HttpStatus.CREATED, firstResponse.getStatusCode());
    }

    @Test
    @DisplayName("Get by Author Test")
    @Order(3)
    void getByAuthor() {
        ReviewResource resource = new ReviewResource(mapper, repo);
        ResponseEntity<List<ReviewDto>> trueResponse = resource.getReviewsByAuthor(realReview.getAuthor());
        ResponseEntity<List<ReviewDto>> falseResponse = resource.getReviewsByAuthor(fakeReview.getAuthor());

        Assertions.assertEquals(HttpStatus.OK, trueResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, falseResponse.getStatusCode());
    }

    @Test
    @DisplayName("Update Review Test")
    @Order(4)
    void updateReviewTest() {
        ReviewResource resource = new ReviewResource(mapper, repo);

        ResponseEntity<List<ReviewDto>> hold = resource.getReviewsByAuthor(realReview.getAuthor());

        if(hold.hasBody() && !hold.getBody().isEmpty()) {
            realReview.setReviewId(hold.getBody().get(0).getReviewId());
            realReview.setSubject("The OTHER Place");
        }
        else
            Assertions.fail();

        ResponseEntity<Void> trueResponse = resource.updateReview(realReview);
        ResponseEntity<Void> falseResponse = resource.updateReview(fakeReview);

        Assertions.assertEquals(HttpStatus.OK, trueResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, falseResponse.getStatusCode());
    }

    @Test
    @DisplayName("Get by Destination Test")
    @Order(5)
    void getByDestination() {
        ReviewResource resource = new ReviewResource(mapper, repo);
        ResponseEntity<List<ReviewDto>> trueResponse = resource.getReviewsByDestination(realReview.getDestId());
        ResponseEntity<List<ReviewDto>> falseResponse = resource.getReviewsByDestination(fakeReview.getDestId());

        Assertions.assertEquals(HttpStatus.OK, trueResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, falseResponse.getStatusCode());
    }

    @Test
    @DisplayName("Delete By Author Test")
    @Order(6)
    void deleteByAuthorTest() {
        ReviewResource resource = new ReviewResource(mapper, repo);
        ResponseEntity<Void> trueResponse = resource.deleteReviewsByAuthor(realReview.getAuthor());
        ResponseEntity<Void> falseResponse = resource.deleteReviewsByAuthor(fakeReview.getAuthor());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, trueResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, falseResponse.getStatusCode());
    }
}
