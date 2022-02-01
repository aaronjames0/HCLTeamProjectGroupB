package com.composite.compositeservice;

import java.util.ArrayList;
import java.util.List;

public class Composite {
    private Destination destination;
    private List<Review> reviews  = new ArrayList<Review>();
    private List<Recommend> recommendations = new ArrayList<Recommend>();

    public Destination getDestination() {
        return destination;
    }
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void addReview(Review review) {
        reviews.add(review);
    }
    public List<Recommend> getRecommendations() {
        return recommendations;
    }
    public void addRec(Recommend recommendation) {
        recommendations.add(recommendation);
    }
}