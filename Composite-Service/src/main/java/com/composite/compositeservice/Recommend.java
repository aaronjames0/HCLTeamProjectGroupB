package com.composite.compositeservice;

public class Recommend {
    private long destId;
    private Long recommendationId;
    private String author;
    private String content;
    private int rate;
    
    public long getDestId() {
        return destId;
    }
    public void setDestId(long destId) {
        this.destId = destId;
    }
    public Long getRecommendationId() {
        return recommendationId;
    }
    public void setRecommendationId(Long recommendationId) {
        this.recommendationId = recommendationId;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
}