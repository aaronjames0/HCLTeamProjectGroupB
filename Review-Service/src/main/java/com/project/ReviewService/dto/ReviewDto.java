package com.project.ReviewService.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
public class ReviewDto {

    @Id
    private long reviewId;
    private long destId;
    private String author;
    private String subject;
    private String content;

}
