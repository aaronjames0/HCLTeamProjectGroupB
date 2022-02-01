package com.composite.compositeservice;

public class Review {

    private long reviewId;
    private long destId;
    private String author;
    private String subject;
    private String content;

    public Review() {
    }
    public long getReviewId() {
        return reviewId;
    }
    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }
    public long getDestId() {
        return destId;
    }
    public void setDestId(long destId) {
        this.destId = destId;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}