package com.project.ReviewService.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="review")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Review {

    @GeneratedValue
    @Id
    private long reviewId;
    private long destId;
    private String author;
    private String subject;
    private String content;
}
