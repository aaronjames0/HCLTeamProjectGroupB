package com.project.ReviewService.mapper;

import com.project.ReviewService.dto.ReviewDto;
import com.project.ReviewService.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDto modelToDto(Review review);
    Review dtoToModel(ReviewDto dto);
    List<ReviewDto> modelsToDto(List<Review> reviews);
}
