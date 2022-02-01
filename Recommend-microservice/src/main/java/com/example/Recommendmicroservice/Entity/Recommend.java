package com.example.Recommendmicroservice.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recommendations")
public class Recommend {
    private long destId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recommendationId;
    private String author;
    private String content;
    private int rate;
    
    public long getDestId() {
        return destId;
    }
    public void setDestId(long destId) {
        this.destId = destId;
    }
    public long getRecommendationId() {
        return recommendationId;
    }
    public void setRecommendationId(long recommendationId) {
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
