package com.project.ReviewService.mapper;

import com.project.ReviewService.dto.ReviewDto;
import com.project.ReviewService.model.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReviewMapperTests {

    ReviewMapper mapper = ReviewMapper.INSTANCE;

    @Test
    @DisplayName("Model to DTO test")
    void modelToDtoTest() {
        Review model = new Review(9846L, 4123L, "Maxwell", "Silver Hammer Pub", "Excellent drinks and polite staff");
        ReviewDto dto = new ReviewDto();
        dto.setReviewId(model.getReviewId());
        dto.setDestId(model.getDestId());
        dto.setAuthor(model.getAuthor());
        dto.setSubject(model.getSubject());
        dto.setContent(model.getContent());

        Assertions.assertEquals(model, mapper.dtoToModel(dto));
    }

    @Test
    @DisplayName("DTO to Model test")
    void dtoToModelTest() {
        Review model = new Review(9846L, 4123L, "Maxwell", "Silver Hammer Pub", "Excellent drinks and polite staff");
        ReviewDto dto = new ReviewDto();
        dto.setReviewId(model.getReviewId());
        dto.setDestId(model.getDestId());
        dto.setAuthor(model.getAuthor());
        dto.setSubject(model.getSubject());
        dto.setContent(model.getContent());

        Assertions.assertEquals(dto, mapper.modelToDto(model));
    }
    
}
