package com.project.ReviewService.review;

import com.project.ReviewService.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query
    List<Review> findByAuthor(String author);

    @Query
    List<Review> findByDestId(long destId);

    @Query
    Review findByReviewId(long reviewId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO review (destId, author, subject, content) VALUES (:destId, :author, :subject, :content);", nativeQuery = true)
    void insertReview(@Param("destId") long destId, @Param("author") String author, @Param("subject") String subject, @Param("content") String content);

    @Modifying
    @Transactional
    @Query(value = "UPDATE review SET destId=:destId, author=:author, subject=:subject, content=:content WHERE reviewId=:reviewId", nativeQuery = true)
    void updateReview(@Param("reviewId") long reviewId, @Param("destId") long destId, @Param("author") String author, @Param("subject") String subject, @Param("content") String content);

    @Modifying
    @Transactional
    @Query
    void deleteReviewByReviewId(long reviewId);

    @Modifying
    @Transactional
    @Query
    void deleteReviewsByAuthor(String author);
}
